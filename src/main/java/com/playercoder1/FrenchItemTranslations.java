package com.playercoder1;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.util.Text;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public final class FrenchItemTranslations
{
    private FrenchItemTranslations() {}

    private static final String RESOURCE = "item_french.txt";
    private static final Map<String, String> MAP = new HashMap<>();
    private static volatile boolean loaded = false;

    private static final Pattern DOSE_SUFFIX = Pattern.compile("^(.*?)(\\s*)\\((\\d+)\\)$");

    public static void init()
    {
        ensureLoaded();
    }

    public static String translateItemName(String englishName)
    {
        ensureLoaded();
        if (!loaded || Strings.isNullOrEmpty(englishName))
        {
            return null;
        }

        final String original = Text.removeTags(englishName).trim();
        if (original.isEmpty())
        {
            return null;
        }

        String base = original;
        String suffix = "";

        Matcher m = DOSE_SUFFIX.matcher(original);
        if (m.matches())
        {
            base = m.group(1).trim();

            suffix = m.group(2) + "(" + m.group(3) + ")";
        }

        String frBase = MAP.get(normKey(base));
        if (frBase != null)
        {
            return frBase + suffix;
        }

        String frExact = MAP.get(normKey(original));
        if (frExact != null)
        {
            return frExact;
        }

        return null;
    }

    private static void ensureLoaded()
    {
        if (loaded)
        {
            return;
        }

        synchronized (FrenchItemTranslations.class)
        {
            if (loaded)
            {
                return;
            }

            int count = TranslationFileLoader.loadPipeSeparated(RESOURCE, MAP);
            if (count >= 0)
            {
                loaded = true;
                log.info("Loaded {} item translations from {}", count, RESOURCE);
            }
            else
            {
                log.error("Item translations failed to load. Items will not be translated.");
            }
        }
    }

    private static String normKey(String s)
    {
        if (s == null)
        {
            return "";
        }

        String clean = Text.removeTags(s);
        return clean.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }
}

