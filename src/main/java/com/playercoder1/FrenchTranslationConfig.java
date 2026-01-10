package com.playercoder1;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(FrenchTranslationConfig.GROUP)
public interface FrenchTranslationConfig extends Config
{
    String GROUP = "osrsinfrench";

    @ConfigItem(
            keyName = "translateMenu",
            name = "Translate menu options",
            description = "Translate right-click menu options (Walk here, Take, etc.)"
    )
    default boolean translateMenu()
    {
        return true;
    }

    @ConfigItem(
            keyName = "translateNpcs",
            name = "Translate NPC names",
            description = "Translate NPC names"
    )
    default boolean translateNpcs()
    {
        return true;
    }

    @ConfigItem(
            keyName = "translateItems",
            name = "Translate item names",
            description = "Translate item names"
    )
    default boolean translateItems()
    {
        return true;
    }

    @ConfigItem(
            keyName = "translateDialogs",
            name = "Translate dialogs",
            description = "Translate NPC dialog text, dialog options, and \"Click here to continue\""
    )
    default boolean translateDialogs()
    {
        return true;
    }

    @ConfigItem(
            keyName = "translateQuests",
            name = "Translate quest list",
            description = "Translate quest list title, quest names, Completed, and Quest Points"
    )
    default boolean translateQuests()
    {
        return true;
    }
}
