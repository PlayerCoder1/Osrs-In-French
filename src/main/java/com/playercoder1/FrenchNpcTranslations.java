package com.playercoder1;

import java.util.Locale;
import java.util.Map;

public final class FrenchNpcTranslations
{
    private FrenchNpcTranslations() {}

    private static final Map<String, String> NPCS_BY_NAME = Map.ofEntries(
            Map.entry("banker", "Banquier")
    );

    public static String translateNpcName(String englishName)
    {
        if (englishName == null || englishName.isEmpty())
        {
            return null;
        }

        return NPCS_BY_NAME.get(englishName.toLowerCase(Locale.ROOT));
    }
}

