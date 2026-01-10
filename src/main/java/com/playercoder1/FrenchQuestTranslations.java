package com.playercoder1;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public final class FrenchQuestTranslations
{
    private FrenchQuestTranslations() {}

    public static final int IFACE_QUESTLIST = 399;

    private static final String RESOURCE = "quest_french.txt";
    private static final Map<String, String> MAP = new HashMap<>();
    private static volatile boolean loaded = false;

    private static final String QUEST_LIST_EN = "quest list";
    private static final String QUEST_LIST_FR = "Liste des quêtes";

    private static final String COMPLETED_EN = "completed";
    private static final String COMPLETED_FR = "Terminées";

    private static final String QUEST_POINTS_EN = "quest points";
    private static final String QUEST_POINTS_FR = "Points de quête";

    public static void init()
    {
        ensureLoaded();
    }

    public static String translateQuestName(String englishQuestName)
    {
        ensureLoaded();
        if (!loaded || Strings.isNullOrEmpty(englishQuestName))
        {
            return null;
        }

        String normalized = normalizeApostrophes(englishQuestName);
        return MAP.get(TranslationFileLoader.normKey(normalized));
    }

    public static String translateQuestPanelLabelsRaw(String rawText)
    {
        if (rawText == null || rawText.isEmpty())
        {
            return null;
        }

        String visible = TranslationFileLoader.visibleText(rawText).trim();
        if (visible.isEmpty())
        {
            return null;
        }

        String norm = TranslationFileLoader.normKey(visible);
        String lower = visible.toLowerCase(Locale.ROOT);

        if (QUEST_LIST_EN.equals(norm))
        {
            String out = rawText.replaceAll("(?i)\\bQuest\\s+list\\b", QUEST_LIST_FR);
            return out.equals(rawText) ? null : out;
        }

        if (lower.startsWith(COMPLETED_EN))
        {
            String out = rawText.replaceFirst("(?i)\\bCompleted\\b", COMPLETED_FR);
            return out.equals(rawText) ? null : out;
        }

        if (lower.startsWith(QUEST_POINTS_EN))
        {
            String out = rawText.replaceFirst("(?i)\\bQuest\\s+Points\\b", QUEST_POINTS_FR);
            return out.equals(rawText) ? null : out;
        }

        return null;
    }

    private static void ensureLoaded()
    {
        if (loaded)
        {
            return;
        }

        synchronized (FrenchQuestTranslations.class)
        {
            if (loaded)
            {
                return;
            }

            int count = TranslationFileLoader.loadPipeSeparated(RESOURCE, MAP);
            if (count >= 0)
            {
                loaded = true;
                log.info("Loaded {} quest translations from {}", count, RESOURCE);
            }
            else
            {
                log.error("Quest translations failed to load. Quests will not be translated.");
            }
        }
    }

    private static String normalizeApostrophes(String s)
    {

        return s.replace('\u2019', '\'')
                .replace('\u2018', '\'')
                .replace('\u0060', '\'');
    }
}

