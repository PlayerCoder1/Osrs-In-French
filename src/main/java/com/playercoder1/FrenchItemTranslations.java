package com.playercoder1;

import java.util.Locale;
import java.util.Map;

public final class FrenchItemTranslations
{
    private FrenchItemTranslations() {}

    private static final Map<String, String> ITEMS_BY_NAME = Map.ofEntries(
            //
    );

    public static String translateItemName(String englishName)
    {
        if (englishName == null || englishName.isEmpty())
        {
            return null;
        }

        return ITEMS_BY_NAME.get(englishName.toLowerCase(Locale.ROOT));
    }
}

