package com.playercoder1;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public final class FrenchDialogTranslations
{
    private FrenchDialogTranslations() {}

    private static final String RESOURCE = "dialog_french.txt";

    private static final Map<String, String> EXACT = new HashMap<>();
    private static final Map<String, List<Rule>> RULES_BY_PREFIX = new HashMap<>();

    private static volatile boolean loaded = false;

    public static void init()
    {
        ensureLoaded();
    }

    public static String translateDialog(String englishText)
    {
        ensureLoaded();
        if (!loaded || englishText == null || englishText.isEmpty())
        {
            return null;
        }

        final String clean = TranslationFileLoader.visibleText(englishText).trim();
        if (clean.isEmpty())
        {
            return null;
        }

        final String norm = TranslationFileLoader.normKey(clean);

        String exact = EXACT.get(norm);
        if (exact != null)
        {
            return exact;
        }

        String prefix = firstWord(norm);
        List<Rule> rules = RULES_BY_PREFIX.get(prefix);
        if (rules == null)
        {
            rules = RULES_BY_PREFIX.get("");
            if (rules == null)
            {
                return null;
            }
        }

        for (Rule r : rules)
        {
            Matcher m = r.pattern.matcher(clean);
            if (m.matches())
            {
                return r.render(m);
            }
        }

        return null;
    }

    private static void ensureLoaded()
    {
        if (loaded)
        {
            return;
        }

        synchronized (FrenchDialogTranslations.class)
        {
            if (loaded)
            {
                return;
            }

            int count = loadRulesFromResource();
            if (count >= 0)
            {
                loaded = true;
                log.info("Loaded {} dialog translations from {}", count, RESOURCE);
            }
            else
            {
                log.error("Dialog translations failed to load. Dialogs will not be translated.");
            }
        }
    }

    private static int loadRulesFromResource()
    {
        InputStream in = FrenchDialogTranslations.class.getClassLoader().getResourceAsStream(RESOURCE);
        if (in == null)
        {
            log.error("Resource not found on classpath: {}", RESOURCE);
            return -1;
        }

        int loadedCount = 0;
        boolean firstLine = true;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                line = stripBom(line).trim();
                if (line.isEmpty() || line.startsWith("#"))
                {
                    continue;
                }

                if (firstLine && line.equalsIgnoreCase("english|french"))
                {
                    firstLine = false;
                    continue;
                }
                firstLine = false;

                int sep = line.indexOf('|');
                if (sep <= 0 || sep >= line.length() - 1)
                {
                    continue;
                }

                String en = line.substring(0, sep).trim();
                String fr = line.substring(sep + 1).trim();
                if (en.isEmpty() || fr.isEmpty())
                {
                    continue;
                }

                if (!looksTemplated(en))
                {
                    EXACT.put(TranslationFileLoader.normKey(en), fr);
                    loadedCount++;
                    continue;
                }

                Rule rule = Rule.compile(en, fr);
                if (rule != null)
                {
                    String prefix = firstWord(rule.prefixKey);
                    RULES_BY_PREFIX.computeIfAbsent(prefix, k -> new ArrayList<>()).add(rule);
                    loadedCount++;
                }
            }
        }
        catch (Exception e)
        {
            log.error("Failed reading resource {}", RESOURCE, e);
            return -1;
        }

        return loadedCount;
    }

    private static boolean looksTemplated(String s)
    {
        if (s == null) return false;

        if (s.indexOf('[') != -1 && s.indexOf(']') != -1)
        {
            return true;
        }

        int i = 0;
        while (true)
        {
            int open = s.indexOf('(', i);
            if (open == -1) break;
            int close = s.indexOf(')', open + 1);
            if (close == -1) break;

            String token = s.substring(open + 1, close).trim();
            if (!token.isEmpty() && !"s".equalsIgnoreCase(token))
            {
                boolean hasLetter = false;
                boolean allDigits = true;

                for (int k = 0; k < token.length(); k++)
                {
                    char c = token.charAt(k);
                    if (Character.isLetter(c)) hasLetter = true;
                    if (!Character.isDigit(c)) allDigits = false;
                }

                if (hasLetter && !allDigits)
                {
                    return true;
                }
            }

            i = close + 1;
        }

        return false;
    }

    private static String firstWord(String s)
    {
        if (s == null) return "";
        int sp = s.indexOf(' ');
        return sp > 0 ? s.substring(0, sp) : s;
    }

    private static String stripBom(String s)
    {
        if (s != null && !s.isEmpty() && s.charAt(0) == '\uFEFF')
        {
            return s.substring(1);
        }
        return s;
    }

    private static final class Rule
    {
        final String prefixKey;
        final Pattern pattern;
        final List<EnPlaceholder> enPlaceholders;
        final List<FrPart> frParts;

        private Rule(String prefixKey, Pattern pattern, List<EnPlaceholder> enPlaceholders, List<FrPart> frParts)
        {
            this.prefixKey = prefixKey;
            this.pattern = pattern;
            this.enPlaceholders = enPlaceholders;
            this.frParts = frParts;
        }

        static Rule compile(String enTemplate, String frTemplate)
        {
            ParseResult en = parseTemplate(enTemplate);
            ParseResult fr = parseTemplate(frTemplate);
            if (en == null || fr == null)
            {
                return null;
            }

            StringBuilder re = new StringBuilder("^");
            int groupIndex = 1;

            List<EnPlaceholder> placeholders = new ArrayList<>();

            for (TemplatePart p : en.parts)
            {
                if (p.isLiteral)
                {
                    re.append(literalToRegex(p.text));
                }
                else
                {
                    String token = p.text;

                    if (token.contains("/"))
                    {
                        String[] opts = token.split("/");
                        List<String> options = new ArrayList<>();
                        StringBuilder group = new StringBuilder("(");

                        for (String o : opts)
                        {
                            String opt = o.trim();
                            if (opt.isEmpty()) continue;
                            options.add(opt);
                            if (group.length() > 1) group.append("|");
                            group.append(Pattern.quote(opt));
                        }
                        group.append(")");

                        if (options.isEmpty())
                        {
                            re.append("(.+?)");
                            placeholders.add(EnPlaceholder.var(groupIndex++));
                        }
                        else
                        {
                            re.append(group);
                            placeholders.add(EnPlaceholder.option(groupIndex++, options));
                        }
                    }
                    else
                    {

                        re.append("(.+?)");
                        placeholders.add(EnPlaceholder.var(groupIndex++));
                    }
                }
            }

            re.append("$");

            Pattern pattern = Pattern.compile(re.toString(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

            List<FrPart> frParts = new ArrayList<>();
            int placeholderCursor = 0;

            for (TemplatePart p : fr.parts)
            {
                if (p.isLiteral)
                {
                    frParts.add(FrPart.literal(p.text));
                }
                else
                {
                    List<String> frOptions = null;
                    String token = p.text;

                    if (token.contains("/"))
                    {
                        frOptions = new ArrayList<>();
                        for (String o : token.split("/"))
                        {
                            String t = o.trim();
                            if (!t.isEmpty()) frOptions.add(t);
                        }
                    }

                    frParts.add(FrPart.placeholder(placeholderCursor++, frOptions));
                }
            }

            String prefixKey = TranslationFileLoader.normKey(enTemplate.replaceAll("\\[[^\\]]*\\]", " ")
                    .replaceAll("\\([^)]*\\)", " ")).trim();

            return new Rule(prefixKey, pattern, placeholders, frParts);
        }

        String render(Matcher m)
        {
            StringBuilder out = new StringBuilder();

            for (FrPart part : frParts)
            {
                if (part.isLiteral)
                {
                    out.append(part.text);
                    continue;
                }

                int idx = part.placeholderIndex;
                if (idx < 0 || idx >= enPlaceholders.size())
                {
                    continue;
                }

                EnPlaceholder ph = enPlaceholders.get(idx);
                String matched = m.group(ph.groupIndex);
                if (matched == null) matched = "";

                if (ph.type == EnPlaceholderType.OPTION)
                {
                    int optIndex = ph.optionIndexOf(matched);

                    if (part.options != null && optIndex >= 0 && optIndex < part.options.size())
                    {
                        out.append(part.options.get(optIndex));
                    }
                    else
                    {
                        out.append(matched);
                    }
                }
                else
                {
                    String v = matched.trim();

                    String frItem = FrenchItemTranslations.translateItemName(v);
                    if (frItem != null)
                    {
                        out.append(frItem);
                        continue;
                    }

                    String frNpc = FrenchNpcTranslations.translateNpcName(v);
                    if (frNpc != null)
                    {
                        out.append(frNpc);
                        continue;
                    }

                    out.append(v);
                }
            }

            return out.toString();
        }
    }

    private enum EnPlaceholderType { OPTION, VAR }

    private static final class EnPlaceholder
    {
        final EnPlaceholderType type;
        final int groupIndex;
        final List<String> optionsLower;

        private EnPlaceholder(EnPlaceholderType type, int groupIndex, List<String> optionsLower)
        {
            this.type = type;
            this.groupIndex = groupIndex;
            this.optionsLower = optionsLower;
        }

        static EnPlaceholder option(int groupIndex, List<String> options)
        {
            List<String> lower = new ArrayList<>(options.size());
            for (String o : options) lower.add(o.toLowerCase(Locale.ROOT));
            return new EnPlaceholder(EnPlaceholderType.OPTION, groupIndex, lower);
        }

        static EnPlaceholder var(int groupIndex)
        {
            return new EnPlaceholder(EnPlaceholderType.VAR, groupIndex, null);
        }

        int optionIndexOf(String matched)
        {
            if (optionsLower == null || matched == null) return -1;
            String m = matched.toLowerCase(Locale.ROOT);
            for (int i = 0; i < optionsLower.size(); i++)
            {
                if (optionsLower.get(i).equals(m)) return i;
            }
            return -1;
        }
    }

    private static final class FrPart
    {
        final boolean isLiteral;
        final String text;
        final int placeholderIndex;
        final List<String> options;

        private FrPart(boolean isLiteral, String text, int placeholderIndex, List<String> options)
        {
            this.isLiteral = isLiteral;
            this.text = text;
            this.placeholderIndex = placeholderIndex;
            this.options = options;
        }

        static FrPart literal(String text) { return new FrPart(true, text, -1, null); }
        static FrPart placeholder(int idx, List<String> options) { return new FrPart(false, null, idx, options); }
    }

    private static final class TemplatePart
    {
        final boolean isLiteral;
        final String text;

        TemplatePart(boolean isLiteral, String text)
        {
            this.isLiteral = isLiteral;
            this.text = text;
        }
    }

    private static final class ParseResult
    {
        final List<TemplatePart> parts;
        ParseResult(List<TemplatePart> parts) { this.parts = parts; }
    }

    private static ParseResult parseTemplate(String template)
    {
        if (template == null) return null;

        List<TemplatePart> parts = new ArrayList<>();
        int i = 0;

        while (i < template.length())
        {
            int nextSq = template.indexOf('[', i);
            int nextPar = template.indexOf('(', i);

            int open;
            char openCh;

            if (nextSq == -1 && nextPar == -1)
            {
                parts.add(new TemplatePart(true, template.substring(i)));
                break;
            }
            else if (nextSq == -1)
            {
                open = nextPar; openCh = '(';
            }
            else if (nextPar == -1)
            {
                open = nextSq; openCh = '[';
            }
            else if (nextPar < nextSq)
            {
                open = nextPar; openCh = '(';
            }
            else
            {
                open = nextSq; openCh = '[';
            }

            char closeCh = (openCh == '[') ? ']' : ')';
            int close = template.indexOf(closeCh, open + 1);
            if (close == -1)
            {
                parts.add(new TemplatePart(true, template.substring(i)));
                break;
            }

            if (open > i)
            {
                parts.add(new TemplatePart(true, template.substring(i, open)));
            }

            String token = template.substring(open + 1, close).trim();

            if (openCh == '(' && "s".equalsIgnoreCase(token))
            {
                parts.add(new TemplatePart(true, "(s)"));
            }
            else
            {
                parts.add(new TemplatePart(false, token));
            }

            i = close + 1;
        }

        return new ParseResult(parts);
    }

    private static String literalToRegex(String literal)
    {
        if (literal == null || literal.isEmpty()) return "";

        String s = literal;
        StringBuilder re = new StringBuilder();
        StringBuilder chunk = new StringBuilder();
        int i = 0;

        while (i < s.length())
        {
            if (s.startsWith("(s)", i))
            {
                if (chunk.length() > 0)
                {
                    re.append(Pattern.quote(chunk.toString()));
                    chunk.setLength(0);
                }
                re.append("(?:s)?");
                i += 3;
                continue;
            }

            char ch = s.charAt(i);

            if (Character.isWhitespace(ch))
            {
                if (chunk.length() > 0)
                {
                    re.append(Pattern.quote(chunk.toString()));
                    chunk.setLength(0);
                }

                while (i < s.length() && Character.isWhitespace(s.charAt(i))) i++;
                re.append("\\s+");
                continue;
            }

            chunk.append(ch);
            i++;
        }

        if (chunk.length() > 0)
        {
            re.append(Pattern.quote(chunk.toString()));
        }

        return re.toString();
    }
}

