package com.playercoder1;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.util.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

@Slf4j
public final class TranslationFileLoader
{
    private TranslationFileLoader() {}

    public static int loadPipeSeparated(String resourceName, Map<String, String> out)
    {

        InputStream in = TranslationFileLoader.class.getClassLoader().getResourceAsStream(resourceName);
        if (in == null)
        {
            log.error("Resource not found on classpath: {}", resourceName);
            return -1;
        }

        int loaded = 0;
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

                if (firstLine && line.toLowerCase(Locale.ROOT).equals("english|french"))
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

                String key = normKey(line.substring(0, sep));
                String val = line.substring(sep + 1).trim();

                if (!key.isEmpty() && !val.isEmpty())
                {
                    out.put(key, val);
                    loaded++;
                }
            }
        }
        catch (Exception e)
        {
            log.error("Failed reading resource {}", resourceName, e);
            return -1;
        }

        return loaded;
    }

    public static String normKey(String s)
    {

        String clean = Text.removeTags(s == null ? "" : s);
        return clean.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    private static String stripBom(String s)
    {
        if (s != null && !s.isEmpty() && s.charAt(0) == '\uFEFF')
        {
            return s.substring(1);
        }
        return s;
    }
}

