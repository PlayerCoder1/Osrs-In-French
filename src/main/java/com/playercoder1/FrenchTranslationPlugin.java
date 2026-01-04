package com.playercoder1;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.PostItemComposition;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@PluginDescriptor(
        name = "Osrs In French",
        description = "Translates partially the game in French"
)
public class FrenchTranslationPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private FrenchTranslationConfig config;

    @Inject
    private ConfigManager configManager;

    @Provides
    FrenchTranslationConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(FrenchTranslationConfig.class);
    }

    private final Map<Integer, String> originalItemNames = new HashMap<>();

    @Override
    protected void startUp()
    {
        FrenchItemTranslations.init();
        FrenchNpcTranslations.init();
        log.info("French Translation started");
    }

    @Override
    protected void shutDown()
    {
        // Only revert if item translation was active (still safe either way)
        try
        {
            for (Map.Entry<Integer, String> e : originalItemNames.entrySet())
            {
                client.getItemDefinition(e.getKey()).setName(e.getValue());
            }
            originalItemNames.clear();
        }
        catch (Exception ex)
        {
            log.debug("Could not fully revert item names: {}", ex.getMessage());
        }

        log.info("French Translation stopped");
    }

    @Subscribe
    public void onPostItemComposition(PostItemComposition event)
    {
        if (!config.translateItems())
        {
            return;
        }

        final int itemId = event.getItemComposition().getId();

        String fr = FrenchItemTranslations.translateItemName(event.getItemComposition().getName());
        if (fr == null)
        {
            return;
        }

        originalItemNames.putIfAbsent(itemId, event.getItemComposition().getName());
        event.getItemComposition().setName(fr);
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event)
    {
        MenuEntry entry = event.getMenuEntry();

        // 1) Menu option (verb)
        if (config.translateMenu())
        {
            String optFr = FrenchMenuTranslations.translateOption(entry.getOption());
            if (optFr != null)
            {
                entry.setOption(optFr);
            }
        }

        // 2) Target (NPC/item/object name area)
        if (!config.translateNpcs() && !config.translateItems())
        {
            return;
        }

        String target = entry.getTarget();
        if (target == null || target.isEmpty())
        {
            return;
        }

        String newTarget = translateMenuTarget(entry, target);
        if (newTarget != null && !newTarget.equals(target))
        {
            entry.setTarget(newTarget);
        }
    }

    private String translateMenuTarget(MenuEntry entry, String targetWithTags)
    {
        String clean = Text.removeTags(targetWithTags);
        String base = stripCombatLevelSuffix(clean);

        // --- NPC target ---
        NPC npc = entry.getNpc();
        if (npc != null)
        {
            if (!config.translateNpcs())
            {
                return null;
            }

            String frNpc = FrenchNpcTranslations.translateNpcName(npc.getName());
            if (frNpc == null)
            {
                frNpc = FrenchNpcTranslations.translateNpcName(base);
            }
            if (frNpc == null)
            {
                return null;
            }

            return targetWithTags.replace(base, frNpc);
        }

        // --- Item target ---
        int itemId = entry.getItemId();
        if (itemId > 0)
        {
            if (!config.translateItems())
            {
                return null;
            }

            String frItem = FrenchItemTranslations.translateItemName(base);
            if (frItem == null)
            {
                return null;
            }

            return targetWithTags.replace(base, frItem);
        }

        // --- Fallback: plain target string (sometimes just a name) ---
        if (config.translateNpcs())
        {
            String fr = FrenchNpcTranslations.translateNpcName(base);
            if (fr != null)
            {
                return targetWithTags.replace(base, fr);
            }
        }

        if (config.translateItems())
        {
            String fr = FrenchItemTranslations.translateItemName(base);
            if (fr != null)
            {
                return targetWithTags.replace(base, fr);
            }
        }

        return null;
    }

    private static String stripCombatLevelSuffix(String s)
    {
        if (s == null) return null;
        return s.replaceAll("\\s*\\(level-\\d+\\)$", "");
    }

    @Subscribe
    public void onBeforeRender(BeforeRender event)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        // If both are off, no point scanning widgets every frame
        if (!config.translateNpcs() && !config.translateItems())
        {
            return;
        }

        for (Widget root : client.getWidgetRoots())
        {
            translateWidgetTree(root);
        }
    }

    private void translateWidgetTree(Widget widget)
    {
        if (widget == null)
        {
            return;
        }

        final int interfaceId = WidgetUtil.componentToInterface(widget.getId());

        // Skip chat/friends UI to avoid translating player-written text
        final int CHATBOX = 162, PRIVATE_CHAT = 163, FRIENDS_LIST = 429;
        if (interfaceId == CHATBOX || interfaceId == PRIVATE_CHAT || interfaceId == FRIENDS_LIST)
        {
            return;
        }

        String text = widget.getText();
        if (text != null && !text.isEmpty())
        {
            String clean = Text.removeTags(text);

            String fr = null;

            if (config.translateNpcs())
            {
                fr = FrenchNpcTranslations.translateNpcName(clean);
            }

            if (fr == null && config.translateItems())
            {
                fr = FrenchItemTranslations.translateItemName(clean);
            }

            if (fr != null)
            {
                widget.setText(text.replace(clean, fr));
            }
        }

        Widget[] kids;

        kids = widget.getDynamicChildren();
        if (kids != null)
        {
            for (Widget w : kids) translateWidgetTree(w);
        }

        kids = widget.getStaticChildren();
        if (kids != null)
        {
            for (Widget w : kids) translateWidgetTree(w);
        }

        kids = widget.getNestedChildren();
        if (kids != null)
        {
            for (Widget w : kids) translateWidgetTree(w);
        }
    }
}
