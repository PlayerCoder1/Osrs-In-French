package com.playercoder1;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.PostItemComposition;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.callback.ClientThread;
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
    private static final int IFACE_CHATBOX = 162;
    private static final int IFACE_PRIVATE_CHAT = 163;
    private static final int IFACE_FRIENDS_LIST = 429;

    private static final String CONTINUE_EN = "click here to continue";
    private static final String CONTINUE_FR = "Cliquez ici pour continuer";

    private static final int CHATLEFT_GROUP = 231;

    private static final int CHATRIGHT_GROUP = 217;

    private static final int CHATMENU_GROUP = 219;

    private static final int CHILD_NAME = 4;
    private static final int CHILD_CONTINUE = 5;
    private static final int CHILD_TEXT = 6;

    @Inject private Client client;
    @Inject private ClientThread clientThread;

    @Inject private FrenchTranslationConfig config;

    @Provides
    FrenchTranslationConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(FrenchTranslationConfig.class);
    }

    private final Map<Integer, String> originalItemNames = new HashMap<>();

    private final Map<Integer, String> lastWidgetText = new HashMap<>();

    @Override
    protected void startUp()
    {
        FrenchItemTranslations.init();
        FrenchNpcTranslations.init();
        FrenchDialogTranslations.init();
        lastWidgetText.clear();
        log.info("French Translation started");
    }

    @Override
    protected void shutDown()
    {
        clientThread.invoke(() ->
        {
            try
            {
                for (Map.Entry<Integer, String> e : originalItemNames.entrySet())
                {
                    client.getItemDefinition(e.getKey()).setName(e.getValue());
                }
                originalItemNames.clear();
                lastWidgetText.clear();
            }
            catch (Exception ex)
            {
                log.debug("Could not fully revert item names: {}", ex.getMessage());
            }

            log.info("French Translation stopped");
        });
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

        if (config.translateMenu())
        {
            String optFr = FrenchMenuTranslations.translateOption(entry.getOption());
            if (optFr != null)
            {
                entry.setOption(optFr);
            }
        }

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

        if (config.translateDialogs())
        {
            translateDialogWidgets();
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        if (!config.translateNpcs() && !config.translateItems())
        {
            return;
        }

        for (Widget root : client.getWidgetRoots())
        {
            if (root == null)
            {
                continue;
            }

            int iface = WidgetUtil.componentToInterface(root.getId());
            if (iface == IFACE_CHATBOX || iface == IFACE_PRIVATE_CHAT || iface == IFACE_FRIENDS_LIST)
            {
                continue;
            }

            translateGeneralWidgetTree(root);
        }
    }

    private void translateDialogWidgets()
    {

        translateOneDialogWidget(CHATLEFT_GROUP, CHILD_NAME, true);

        translateOneDialogWidget(CHATLEFT_GROUP, CHILD_TEXT, false);

        translateOneDialogWidget(CHATLEFT_GROUP, CHILD_CONTINUE, false);

        translateOneDialogWidget(CHATRIGHT_GROUP, CHILD_NAME, true);

        translateOneDialogWidget(CHATRIGHT_GROUP, CHILD_TEXT, false);

        translateOneDialogWidget(CHATRIGHT_GROUP, CHILD_CONTINUE, false);

        translateChatmenuOptions();
    }

    private void translateOneDialogWidget(int groupId, int childId, boolean isNameWidget)
    {
        Widget w = client.getWidget(groupId, childId);
        if (w == null || w.isHidden())
        {
            return;
        }

        String raw = w.getText();
        if (raw == null || raw.isEmpty())
        {
            return;
        }

        String visible = TranslationFileLoader.visibleText(raw).trim();
        if (visible.isEmpty())
        {
            return;
        }

        if (TranslationFileLoader.normKey(visible).equals(CONTINUE_EN))
        {
            w.setText(preserveOuterTagsFast(raw, CONTINUE_FR));
            return;
        }

        if (isNameWidget)
        {

            if (config.translateNpcs())
            {
                String frNpc = FrenchNpcTranslations.translateNpcName(visible);
                if (frNpc != null)
                {
                    w.setText(preserveOuterTagsFast(raw, frNpc));
                }
            }
            return;
        }

        String fr = FrenchDialogTranslations.translateDialog(raw);
        if (fr != null)
        {
            w.setText(preserveOuterTagsFast(raw, fr));
        }
    }

    private void translateChatmenuOptions()
    {
        Widget options = client.getWidget(CHATMENU_GROUP, 1);

        if (options == null || options.isHidden())
        {
            return;
        }

        Widget[] kids = options.getDynamicChildren();
        if (kids == null)
        {
            return;
        }

        for (Widget k : kids)
        {
            if (k == null || k.isHidden())
            {
                continue;
            }

            String raw = k.getText();
            if (raw == null || raw.isEmpty())
            {
                continue;
            }

            String visible = TranslationFileLoader.visibleText(raw).trim();
            if (visible.isEmpty())
            {
                continue;
            }

            String fr = FrenchDialogTranslations.translateDialog(raw);
            if (fr != null)
            {
                k.setText(preserveOuterTagsFast(raw, fr));
            }
        }
    }

    private void translateGeneralWidgetTree(Widget widget)
    {
        if (widget == null || widget.isHidden())
        {
            return;
        }

        int iface = WidgetUtil.componentToInterface(widget.getId());
        if (iface == IFACE_CHATBOX)
        {
            return;
        }

        translateWidgetTextIfChanged(widget);

        Widget[] kids;

        kids = widget.getDynamicChildren();
        if (kids != null) for (Widget w : kids) translateGeneralWidgetTree(w);

        kids = widget.getStaticChildren();
        if (kids != null) for (Widget w : kids) translateGeneralWidgetTree(w);

        kids = widget.getNestedChildren();
        if (kids != null) for (Widget w : kids) translateGeneralWidgetTree(w);
    }

    private void translateWidgetTextIfChanged(Widget widget)
    {
        final String rawText = widget.getText();
        if (rawText == null || rawText.isEmpty())
        {
            return;
        }

        final int id = widget.getId();
        final String prev = lastWidgetText.get(id);
        if (rawText.equals(prev))
        {
            return;
        }

        String cleanVisible = TranslationFileLoader.visibleText(rawText).trim();
        if (cleanVisible.isEmpty())
        {
            lastWidgetText.put(id, rawText);
            return;
        }

        String fr = null;

        if (config.translateNpcs())
        {
            fr = FrenchNpcTranslations.translateNpcName(cleanVisible);
        }

        if (fr == null && config.translateItems())
        {
            fr = FrenchItemTranslations.translateItemName(cleanVisible);
        }

        if (fr != null)
        {
            String newText = preserveOuterTagsFast(rawText, fr);
            widget.setText(newText);
            lastWidgetText.put(id, newText);
        }
        else
        {
            lastWidgetText.put(id, rawText);
        }
    }

    private static String preserveOuterTagsFast(String original, String replacement)
    {
        if (original == null)
        {
            return replacement;
        }

        if (original.indexOf('<') == -1)
        {
            return replacement;
        }

        int i = 0;
        StringBuilder pre = new StringBuilder();
        while (i < original.length() && original.charAt(i) == '<')
        {
            int end = original.indexOf('>', i);
            if (end == -1) break;

            String tag = original.substring(i, end + 1);
            if (tag.startsWith("</"))
            {
                break;
            }

            pre.append(tag);
            i = end + 1;
        }

        int j = original.length();
        StringBuilder post = new StringBuilder();
        while (j > 0)
        {
            int start = original.lastIndexOf('<', j - 1);
            if (start == -1) break;

            int end = original.indexOf('>', start);
            if (end == -1) break;

            String tag = original.substring(start, end + 1);
            if (!tag.startsWith("</"))
            {
                break;
            }

            post.insert(0, tag);
            j = start;
        }

        if (pre.length() == 0 && post.length() == 0)
        {
            return replacement;
        }

        return pre + replacement + post;
    }
}

