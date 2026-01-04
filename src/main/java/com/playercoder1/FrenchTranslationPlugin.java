package com.playercoder1;

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
        description = "Translating basic stuff in French"
)
public class FrenchTranslationPlugin extends Plugin
{
    @Inject
    private Client client;

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

        String optFr = FrenchMenuTranslations.translateOption(entry.getOption());
        if (optFr != null)
        {
            entry.setOption(optFr);
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

        NPC npc = entry.getNpc();
        if (npc != null)
        {
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

        int itemId = entry.getItemId();
        if (itemId > 0)
        {
            String frItem = FrenchItemTranslations.translateItemName(base);
            if (frItem == null)
            {
                return null;
            }

            return targetWithTags.replace(base, frItem);
        }

        String fr = FrenchNpcTranslations.translateNpcName(base);
        if (fr == null)
        {
            fr = FrenchItemTranslations.translateItemName(base);
        }

        if (fr == null)
        {
            return null;
        }

        return targetWithTags.replace(base, fr);
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

        final int CHATBOX = 162, PRIVATE_CHAT = 163, FRIENDS_LIST = 429;
        if (interfaceId == CHATBOX || interfaceId == PRIVATE_CHAT || interfaceId == FRIENDS_LIST)
        {
            return;
        }

        String text = widget.getText();
        if (text != null && !text.isEmpty())
        {
            String clean = Text.removeTags(text);

            String fr = FrenchNpcTranslations.translateNpcName(clean);
            if (fr == null)
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

