package com.playercoder1;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public final class FrenchNpcTranslations
{
    private FrenchNpcTranslations() {}

    private static final String RESOURCE = "npc_french.txt";
    private static final Map<String, String> MAP = new HashMap<>();

    private static volatile boolean loaded = false;

    public static void init()
    {
        ensureLoaded();
    }

    public static String translateNpcName(String englishName)
    {
        ensureLoaded();
        if (!loaded || englishName == null || englishName.isEmpty())
        {
            return null;
        }

        return MAP.get(TranslationFileLoader.normKey(englishName));
    }

    private static void ensureLoaded()
    {
        if (loaded)
        {
            return;
        }

        synchronized (FrenchNpcTranslations.class)
        {
            if (loaded)
            {
                return;
            }

            int count = TranslationFileLoader.loadPipeSeparated(RESOURCE, MAP);
            if (count >= 0)
            {
                loaded = true;
                log.info("Loaded {} NPC translations from {}", count, RESOURCE);
            }
            else
            {
                log.error("NPC translations failed to load. NPCs will not be translated.");
            }
        }
    }
}
