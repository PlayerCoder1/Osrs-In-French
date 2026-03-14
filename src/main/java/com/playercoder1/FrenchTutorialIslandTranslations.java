package com.playercoder1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FrenchTutorialIslandTranslations
{
    private FrenchTutorialIslandTranslations() {}

    public static final int IFACE_TUTORIAL_DISPLAY_NAME = 558;
    public static final int IFACE_TUTORIAL_PLAYER_EXPERIENCE = 929;
    public static final int IFACE_TUTORIAL_OVERLAY = 614;

    private static final Map<String, String> RAW_BY_VISIBLE = new HashMap<>();

    private static final Pattern DISPLAY_NAME_NOT_AVAILABLE_PATTERN = Pattern.compile(
            "^\\s*Sorry,\\s*the\\s*display\\s*name\\s*<col=ffffff>(.+?)</col>\\s*is\\s*<col=ff0000>not\\s+available</col>\\.\\s*<br>\\s*Try\\s+clicking\\s+one\\s+of\\s+our\\s+suggestions,\\s*instead:\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL
    );

    private static final Pattern DISPLAY_NAME_AVAILABLE_PATTERN = Pattern.compile(
            "^\\s*Great!\\s*The\\s*display\\s*name\\s*<col=ffffff>(.+?)</col>\\s*is\\s*<col=00ff00>available</col>!\\s*<br>\\s*You\\s+may\\s+set\\s+this\\s+name\\s+now,\\s+or\\s+enter\\s+another\\s+to\\s+look\\s+up\\.\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL
    );

    public static void init()
    {
        // no-op
    }

    static
    {
        put("Set display name", "Définir le nom d'affichage");
        put("Display name", "Nom d'affichage");
        put("Please look up a name to see whether it is available.", "Veuillez rechercher un nom pour voir s'il est disponible.");
        put("Look up name", "Vérifier le nom");
        put("Set name", "Choisir ce nom");

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "In order to play Old School RuneScape, you must select a unique display name for your character, up to twelve characters long. This may be changed at a later stage."
                ),
                "Pour jouer à Old School RuneScape, vous devez choisir un <col=ffffff>nom d'affichage</col> unique pour votre personnage, avec un maximum de douze caractères.<br><br>Vous pourrez le modifier plus tard."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Setting your name Before you get started, you'll need to set a display name. Please use the open interface to set one."
                ),
                "<col=0000ff>Définir votre nom</col><br>Avant de commencer, vous devrez choisir un nom d'affichage. Veuillez utiliser l'interface ouverte pour en définir un."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Past Experience Before you get started, please use the open interface to select your experience with Old School RuneScape."
                ),
                "<col=0000ff>Expérience passée</col><br>Avant de commencer, veuillez utiliser l'interface ouverte pour sélectionner votre niveau d'expérience avec Old School RuneScape."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Setting your appearance Before you get started, you'll need to set the appearance of your character. Please use the open interface to set your appearance."
                ),
                "<col=0000ff>Définir votre apparence</col><br>Avant de commencer, vous devrez définir l'apparence de votre personnage. Veuillez utiliser l'interface ouverte pour définir votre apparence."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Getting started When you're ready to get started, click on the Gielinor Guide. He is indicated by a flashing yellow arrow."
                ),
                "<col=0000ff>Pour commencer</col><br>Lorsque vous êtes prêt à commencer, cliquez sur le Guide de Gielinor. Il est indiqué par une flèche jaune clignotante."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Settings menu Please click on the flashing spanner icon found at the bottom right of your screen. This will display your settings menu."
                ),
                "<col=0000ff>Menu des paramètres</col><br>Veuillez cliquer sur l'icône de clé à molette clignotante située en bas à droite de votre écran. Cela affichera votre menu des paramètres."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Settings menu On the side panel, you can now see a variety of game settings. You can also click the all settings button to see all available settings. Talk to the Gielinor Guide to continue."
                ),
                "<col=0000ff>Menu des paramètres</col><br>Dans le panneau latéral, vous pouvez maintenant voir divers paramètres du jeu. Vous pouvez aussi cliquer sur le bouton de tous les paramètres pour voir tous les paramètres disponibles. Parlez au Guide de Gielinor pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on It's time to meet your first instructor. To continue, all you need to do is click on the door. It's indicated by a flashing yellow arrow. Remember, you can use your arrow keys to rotate the camera."
                ),
                "<col=0000ff>En route</col><br>Il est temps de rencontrer votre premier instructeur. Pour continuer, il vous suffit de cliquer sur la porte. Elle est indiquée par une flèche jaune clignotante. N'oubliez pas que vous pouvez utiliser les touches fléchées pour faire pivoter la caméra."
        );

        put("How familiar are you with Old School RuneScape?", "Quel est votre niveau d'expérience avec Old School RuneScape ?");
        put("New player", "<col=ff9040>Nouveau joueur</col>");
        put("I'm brand new! This is my first time here.", "Je débute ! C'est ma première fois ici.");
        put("Returning player", "<col=ff9040>Joueur de retour</col>");
        put("I've played in the past, but not recently.", "J'y ai déjà joué par le passé, mais pas récemment.");
        put("Experienced player", "<col=ff9040>Joueur expérimenté</col>");
        put("I'm an experienced player.", "Je suis un joueur expérimenté.");

        put("Exit through the nearby door", "Sortez par la porte voisine");
        put("to continue the tutorial.", "pour continuer le tutoriel.");
        put("This box will remind you of", "Cette boîte vous rappellera");
        put("what you need to do in future steps.", "ce que vous devrez faire dans les étapes suivantes.");

        put("Follow the path to find", "Suivez le chemin pour trouver");
        put("the Survival Instructor.", "l'instructeur de survie.");
        put("<col=ffffff>Click<col=ff981f> on the ground", "<col=ffffff>Cliquez</col><col=ff981f> sur le sol");
        put("or minimap to move.", "ou sur la minimap pour vous déplacer.");

        put("<col=ffffff>Click<col=ff981f> a sparkling fishing spot", "<col=ffffff>Cliquez</col><col=ff981f> sur un lieu de pêche scintillant");
        put("<col=ffffff>Click<col=ff981f> the flashing icon", "<col=ffffff>Cliquez</col><col=ff981f> sur l'icône clignotante");
        put("<col=ffffff>Click<col=ff981f> a nearby tree", "<col=ffffff>Cliquez</col><col=ff981f> sur un arbre voisin");
        put("to select it.", "pour le sélectionner.");
        put("to select them.", "pour les sélectionner.");
        put("<col=fffffff>Click<col=ff981f> the nearby range to", "<col=ffffff>Cliquez</col><col=ff981f> sur la cuisinière voisine pour");
        put("<col=fffffff>Click<col=ff981f> on the door to pass through it.", "<col=ffffff>Cliquez</col><col=ff981f> sur la porte pour la franchir.");
        put("to mine tin ore.", "pour miner du minerai d'étain.");
        put("to smelt a bronze bar.", "pour fondre une barre de bronze.");
        put("You must make a <col=fffffff>bronze dagger<col=ff981f>.", "Vous devez fabriquer une <col=ffffff>dague en bronze</col><col=ff981f>.");
        put("and <col=ffffff>click<col=ff981f> to attack a rat.", "et <col=ffffff>cliquez</col><col=ff981f> pour attaquer un rat.");
        put("Equip your bow and arrows,", "Équipez votre arc et vos flèches,");
        put("ladder to the North.", "échelle au nord.");
        put("and follow the path to the chapel.", "et suivez le chemin jusqu'à la chapelle.");
        put("and follow the path heading eastward.", "et suivez le chemin vers l'est.");
        put("<col=ffffff>click<col=ff981f> the spell in your spellbook.", "<col=ffffff>cliquez</col><col=ff981f> sur le sort dans votre grimoire.");
        put("When you're ready, speak to the", "Quand vous êtes prêt, parlez au");
        put("When you're ready, use your Home Teleport spell.", "Quand vous êtes prêt, utilisez votre sort Téléportation maison.");

        put("to start fishing for shrimp.", "pour commencer à pêcher des crevettes.");
        put("to open your skills menu.", "pour ouvrir votre menu de compétences.");
        put("to begin cutting it down.", "pour commencer à l'abattre.");
        put("Then <col=ffffff>click<col=ff981f> your", "Ensuite, <col=ffffff>cliquez</col><col=ff981f> sur votre");
        put("Then, <col=fffffff>click<col=ff981f> on a fire", "Ensuite, <col=ffffff>cliquez</col><col=ff981f> sur un feu");
        put("bake your dough into bread", "cuire votre pâte pour en faire du pain");
        put("Then, speak to the Quest Guide.", "Ensuite, parlez au Guide des quêtes.");
        put("Then, <col=ffffff>click<col=ff981f> a copper rock", "Ensuite, <col=ffffff>cliquez</col><col=ff981f> sur un rocher de cuivre");
        put("This will use your", "Cela utilisera votre");
        put("You need a hammer to smith at an", "Vous avez besoin d'un marteau pour forger sur une");
        put("Return to the Combat Instructor", "Retournez voir l'instructeur de combat");
        put("Then, follow the path to learn", "Ensuite, suivez le chemin pour apprendre");
        put("Once there, speak to", "Une fois là-bas, parlez à");
        put("Then, speak to the", "Ensuite, parlez au");
        put("With Wind Strike selected, <col=ffffff>click<col=ff981f>", "Avec Frappe du vent sélectionné, <col=ffffff>cliquez</col><col=ff981f> sur");
        put("Magic Instructor to continue.", "l'instructeur de magie pour continuer.");
        put("This will take you to the mainland.", "Cela vous emmènera sur le continent.");

        put("<col=ffffff>Click<col=ff981f> on your <col=ffffff>tinderbox<col=ff981f>", "<col=ffffff>Cliquez</col><col=ff981f> sur votre <col=ffffff>boîte à amadou</col><col=ff981f>");
        put("<col=ffffff>Click<col=ff981f> the shrimp in your inventory", "<col=ffffff>Cliquez</col><col=ff981f> sur les crevettes dans votre inventaire");
        put("Follow the path to your next guide.", "Suivez le chemin jusqu'à votre prochain guide.");
        put("<col=fffffff>Click<col=ff981f> a tin rock", "<col=ffffff>Cliquez</col><col=ff981f> sur un rocher d'étain");
        put("<col=fffffff>Click<col=ff981f> a the nearby furnace", "<col=ffffff>Cliquez</col><col=ff981f> sur le fourneau voisin");
        put("<col=fffffff>Click<col=ff981f> the anvil to begin smithing.", "<col=ffffff>Cliquez</col><col=ff981f> sur l'enclume pour commencer à forger.");
        put("Enter the cage through the nearby gate,", "Entrez dans la cage par la porte voisine,");
        put("When you're ready, climb up the", "Quand vous êtes prêt, montez à l'");
        put("Exit through the door", "Sortez par la porte");
        put("To <col=ffffff>cast<col=ff981f> your first spell,", "Pour <col=ffffff>lancer</col><col=ff981f> votre premier sort,");

        put("<col=ffffff>logs<col=ff981f> in order to light them.", "<col=ffffff>bûches</col><col=ff981f> afin de les allumer.");
        put("to begin cooking them.", "pour commencer à les cuire.");
        put("to mine copper ore.", "pour miner du minerai de cuivre.");
        put("tin and copper ores.", "minerais d'étain et de cuivre.");
        put("anvil.", "enclume.");
        put("once the rat is dead.", "une fois le rat mort.");
        put("about Banking and Polls.", "sur la Banque et les Sondages.");
        put("Brother Brace.", "Frère Brace.");
        put("Magic Instructor.", "l'instructeur de magie.");
        put("a chicken to cast it.", "sur un poulet pour le lancer.");

        put("The survival expert gives you a small fishing net.", "L'expert en survie vous donne un <col=000080>petit filet de pêche</col>.");
        put("You manage to catch some shrimp.", "Vous réussissez à attraper quelques crevettes.");

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "The survival expert gives you a bronze axe and a tinderbox."
                ),
                "L'expert en survie vous donne une <col=000080>hache en bronze</col> et une <col=000080>boîte à amadou</col>."
        );

        put("You manage to cut some logs.", "Vous réussissez à couper quelques bûches.");
        put("You manage to cook some shrimp.", "Vous réussissez à cuire quelques crevettes.");
        put("You make some dough.", "Vous faites de la pâte.");
        put("You manage to bake some bread.", "Vous réussissez à cuire du pain.");

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "The minimap in the top right corner of the screen has various icons to show different points of interest. Look for the icon to the left to find quest start points."
                ),
                "La minimap en haut à droite de l'écran comporte différentes icônes indiquant des points d'intérêt. Cherchez l'icône à gauche pour trouver les points de départ des quêtes."
        );

        put("The mining instructor gives you a bronze pickaxe.", "L'instructeur minier vous donne une <col=000080>pioche en bronze</col>.");
        put("You manage to mine some tin.", "Vous réussissez à miner un peu d'étain.");
        put("You manage to mine some copper.", "Vous réussissez à miner un peu de cuivre.");
        put("The mining instructor gives you a hammer.", "L'instructeur minier vous donne un <col=000080>marteau</col>.");

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "You've been given an item To view the item you've been given, you'll need to open your inventory. To do so, click on the flashing backpack icon to the right hand side of your screen."
                ),
                "<col=0000ff>Vous avez reçu un objet</col><br>Pour voir l'objet qui vous a été donné, vous devez ouvrir votre inventaire. Pour cela, cliquez sur l'icône de sac à dos clignotante à droite de votre écran."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Inventory This is your inventory. You can view all of your items here, including the net you've just been given. Let's use it to catch some shrimp."
                ),
                "<col=0000ff>Inventaire</col><br>Voici votre inventaire. Vous pouvez y voir tous vos objets, y compris le filet que vous venez de recevoir. Utilisons-le pour attraper quelques crevettes."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "You've gained some experience Click on the flashing bar graph icon near the inventory button to see your skills menu.Click the flashing icon"
                ),
                "<col=0000ff>Vous avez gagné de l'expérience</col><br>Cliquez sur l'icône de graphique à barres clignotante près du bouton d'inventaire pour voir votre menu de compétences.<col=ffffff>Cliquez</col><col=ff981f> sur l'icône clignotante"
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Skills and Experience On this menu you can view your skills. Your skills can be leveled up by earning experience. As you level up your skills, you will earn new unlocks. Speak to the survival expert to continue."
                ),
                "<col=0000ff>Compétences et expérience</col><br>Dans ce menu, vous pouvez voir vos compétences. Vos compétences augmentent de niveau en gagnant de l'expérience. En augmentant vos niveaux, vous débloquerez de nouvelles choses. Parlez à l'expert en survie pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Woodcutting It's time to cook your shrimp. However, you require a fire to do that which means you need some logs. You can cut down trees using your Woodcutting skill, all you need is an axe."
                ),
                "<col=0000ff>Coupe de bois</col><br>Il est temps de faire cuire vos crevettes. Cependant, vous avez besoin d'un feu pour cela, ce qui signifie qu'il vous faut des bûches. Vous pouvez abattre des arbres grâce à votre compétence Coupe de bois ; tout ce qu'il vous faut, c'est une hache."
        );

        put(
                "Your character is now attempting to cut down the tree. Sit back for a moment while he does all the hard work.",
                "Votre personnage est en train d'essayer d'abattre l'arbre. Attendez un instant pendant qu'il fait tout le travail."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Cooking Now it's time to get cooking. To do so, click on the shrimp in your inventory. Then, with the shrimp highlighted, click on a fire to cook them. If you look at the top left of the screen, you'll see the instructions that you're giving to your character."
                ),
                "<col=0000ff>Cuisine</col><br>Il est maintenant temps de cuisiner. Pour cela, cliquez sur les crevettes dans votre inventaire. Ensuite, avec les crevettes sélectionnées, cliquez sur un feu pour les faire cuire. Si vous regardez en haut à gauche de l'écran, vous verrez les instructions que vous donnez à votre personnage."
        );

        put(
                "Your character is now attempting to cook some shrimp. This will only take a few seconds.",
                "Votre personnage essaie maintenant de cuire des crevettes. Cela ne prendra que quelques secondes."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on Well done, you've just cooked your first meal! Speak to the survival expert if you want a recap, otherwise you can move on. Click on the gate shown and follow the path. Remember, you can use your arrow keys to rotate the camera."
                ),
                "<col=0000ff>En route</col><br>Bien joué, vous venez de cuisiner votre premier repas ! Parlez à l'expert en survie si vous voulez un récapitulatif, sinon vous pouvez continuer. Cliquez sur la porte indiquée et suivez le chemin. N'oubliez pas que vous pouvez utiliser les touches fléchées pour faire pivoter la caméra."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on Follow the path until you get to the door with the yellow arrow above it. Click on the door to open it. If you can't find it, try moving your camera by clicking and dragging your middle mouse button."
                ),
                "<col=0000ff>En route</col><br>Suivez le chemin jusqu'à atteindre la porte avec la flèche jaune au-dessus. Cliquez sur la porte pour l'ouvrir. Si vous ne la trouvez pas, essayez de déplacer votre caméra en cliquant et en faisant glisser le bouton du milieu de votre souris."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Cooking Talk to the chef to learn the more advanced aspects of Cooking such as combining ingredients!"
                ),
                "<col=0000ff>Cuisine</col><br>Parlez au chef pour apprendre les aspects plus avancés de la Cuisine, comme la combinaison d'ingrédients !"
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Making dough This is the base for many meals. To make dough you must mix flour with water. To do so, click on the flour in your inventory. Then, with the flour highlighted, click on the water to combine them into dough."
                ),
                "<col=0000ff>Faire de la pâte</col><br>C'est la base de nombreux repas. Pour faire de la pâte, vous devez mélanger de la farine avec de l'eau. Pour cela, cliquez sur la farine dans votre inventaire. Ensuite, avec la farine sélectionnée, cliquez sur l'eau pour les combiner en pâte."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Cooking dough Now you have made the dough, you can bake it into some bread. To do so, just click on the indicated range."
                ),
                "<col=0000ff>Cuire la pâte</col><br>Maintenant que vous avez fait la pâte, vous pouvez la cuire pour faire du pain. Pour cela, cliquez simplement sur la cuisinière indiquée."
        );

        put(
                "Your character is now attempting to bake some bread. This will only take a few seconds.",
                "Votre personnage essaie maintenant de cuire du pain. Cela ne prendra que quelques secondes."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on Well done! You've baked your first loaf of bread. As you gain experience in Cooking, you will be able to make other things like pies and cakes. You can now use the next door to move on. If you need a recap on anything, talk to the master chef."
                ),
                "<col=0000ff>En route</col><br>Bien joué ! Vous avez cuit votre première miche de pain. En gagnant de l'expérience en Cuisine, vous pourrez préparer d'autres choses comme des tartes et des gâteaux. Vous pouvez maintenant utiliser la porte suivante pour continuer. Si vous avez besoin d'un récapitulatif, parlez au maître chef."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Fancy a run? When navigating the world, you can either run or walk. Running is faster but you can't run for long as you'll soon run out of energy. You can use the flashing orb next to the minimap to toggle running. Why not try it as you head to the next section?"
                ),
                "<col=0000ff>Envie de courir ?</col><br>Courir est plus rapide, mais cela consomme de l'énergie. Cliquez sur l'orbe clignotant près de la minimap pour activer la course, puis essayez-la en allant à la prochaine section."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Quests It's time to learn about quests! Just talk to the Quest Guide to get started."
                ),
                "<col=0000ff>Quêtes</col><br>Il est temps d'apprendre ce que sont les quêtes ! Parlez simplement au Guide des quêtes pour commencer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Quest journal Click on the flashing icon to the left of your inventory."
                ),
                "<col=0000ff>Journal de quêtes</col><br>Cliquez sur l'icône clignotante à gauche de votre inventaire."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Quest journal This is your quest journal. It lists every quest in the game. Talk to the quest guide again for an explanation on how it works."
                ),
                "<col=0000ff>Journal de quêtes</col><br>Voici votre journal de quêtes. Il répertorie toutes les quêtes du jeu. Reparlez au Guide des quêtes pour obtenir une explication de son fonctionnement."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on It's time to enter some caves. Click on the ladder to go down to the next area."
                ),
                "<col=0000ff>En route</col><br>Il est temps d'entrer dans des cavernes. Cliquez sur l'échelle pour descendre dans la zone suivante."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Mining and Smithing Next let's get you a weapon, or more to the point, you can make your first weapon yourself. Don't panic, the mining instructor will help you. Talk to him and he'll tell you all about it."
                ),
                "<col=0000ff>Extraction minière et Forge</col><br>Ensuite, allons vous procurer une arme, ou plus exactement, vous pouvez fabriquer vous-même votre première arme. Ne paniquez pas, l'instructeur minier va vous aider. Parlez-lui et il vous expliquera tout."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Mining It's quite simple really. To mine a rock, all you need to do is click on it. First up, try mining some tin."
                ),
                "<col=0000ff>Extraction minière</col><br>C'est très simple en réalité. Pour miner un rocher, il vous suffit de cliquer dessus. Pour commencer, essayez de miner un peu d'étain."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Your character is now attempting to mine the rock. This should only take a few seconds."
                ),
                "Votre personnage essaie maintenant de miner le rocher.<br>Cela ne devrait prendre que quelques secondes."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Smelting You now have some tin ore and some copper ore. You can smelt these into a bronze bar. To do so, just click on the indicated furnace. Try it now."
                ),
                "<col=0000ff>Fonte</col><br>Vous avez maintenant du minerai d'étain et du minerai de cuivre. Vous pouvez les fondre pour obtenir une barre de bronze. Pour cela, cliquez simplement sur le fourneau indiqué. Essayez maintenant."
        );

        put(
                "You smelt the copper and tin together in the furnace.",
                "Vous fondez le cuivre et l'étain ensemble dans le fourneau."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Smelting You've made a bronze bar! Speak to the mining instructor and he'll show you how to make it into a weapon."
                ),
                "<col=0000ff>Fonte</col><br>Vous avez fabriqué une barre de bronze ! Parlez à l'instructeur minier et il vous montrera comment la transformer en arme."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Smithing a dagger To smith you'll need a hammer and enough metal bars to make the desired item, as well as a handy anvil. To start the process, click on the anvil, or alternatively use the bar on it."
                ),
                "<col=0000ff>Forger une dague</col><br>Pour forger, vous aurez besoin d'un marteau, d'assez de barres de métal pour fabriquer l'objet souhaité, ainsi que d'une enclume. Pour commencer, cliquez sur l'enclume ou utilisez la barre dessus."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Smithing a dagger Now you have the smithing menu open, you will see a list of all the things you can make. Only the dagger can be made at your skill level; this is shown by the white text under it. You'll need to select the dagger to continue."
                ),
                "<col=0000ff>Forger une dague</col><br>Maintenant que le menu de forge est ouvert, vous verrez une liste de tout ce que vous pouvez fabriquer. Seule la dague peut être fabriquée à votre niveau de compétence ; cela est indiqué par le texte blanc en dessous. Vous devrez sélectionner la dague pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on Congratulations, you've made your first weapon. Now it's time to move on. Go through the gates shown by the arrow. Remember, you may need to move the camera to see your surroundings. Speak to the mining instructor for a recap at any time."
                ),
                "<col=0000ff>En route</col><br>Félicitations, vous avez fabriqué votre première arme. Il est maintenant temps de continuer. Passez par les portes indiquées par la flèche. N'oubliez pas que vous devrez peut-être déplacer la caméra pour voir autour de vous. Parlez à l'instructeur minier si vous voulez un récapitulatif."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Combat In this area you will find out about melee and ranged combat. Speak to the guide and he will tell you all about it."
                ),
                "<col=0000ff>Combat</col><br>Dans cette zone, vous découvrirez le combat au corps à corps et à distance. Parlez au guide et il vous expliquera tout."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Equipping items You now have access to a new interface. Click on the flashing icon of a man, the one to the right of your backpack icon."
                ),
                "<col=0000ff>Équiper des objets</col><br>Vous avez maintenant accès à une nouvelle interface. Cliquez sur l'icône clignotante représentant un homme, à droite de votre icône de sac à dos."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Worn inventory This is your worn inventory. Here you can see what items you have equipped. In the bottom left corner, you will notice a flashing button with a shield and helmet on it. This button lets you view more details on what you have equipped. Click on it now."
                ),
                "<col=0000ff>Équipement porté</col><br>Voici votre équipement porté. Ici, vous pouvez voir quels objets vous avez équipés. En bas à gauche, vous remarquerez un bouton clignotant avec un bouclier et un casque. Ce bouton vous permet de voir plus de détails sur ce que vous portez. Cliquez dessus maintenant."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Equipment stats You can see what items you are wearing in the worn inventory to the left of the screen, with their combined statistics on the right. Let's add something. Click your dagger to equip it."
                ),
                "<col=0000ff>Statistiques d'équipement</col><br>Vous pouvez voir les objets que vous portez dans l'équipement à gauche de l'écran, avec leurs statistiques combinées à droite. Ajoutons quelque chose. Cliquez sur votre dague pour l'équiper."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Equipment stats You're now holding your dagger. Clothes, armour, weapons and more are equipped like this. Alternatively, you can click items directly from your inventory to equip them without opening the stats window. Speak to the combat instructor to continue."
                ),
                "<col=0000ff>Statistiques d'équipement</col><br>Vous tenez maintenant votre dague. Les vêtements, armures, armes et autres objets s'équipent ainsi. Vous pouvez aussi cliquer directement sur des objets dans votre inventaire pour les équiper sans ouvrir la fenêtre de statistiques. Parlez à l'instructeur de combat pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Unequipping items To unequip an item, go to your worn inventory and click on the item. Alternatively, equipping a new item into the same slot will unequip the old one. Try this out now by swapping your dagger for the sword and shield that the combat instructor gave you."
                ),
                "<col=0000ff>Déséquiper des objets</col><br>Pour déséquiper un objet, allez dans votre équipement porté et cliquez sur l'objet. Sinon, équiper un nouvel objet dans le même emplacement déséquipera l'ancien. Essayez maintenant en remplaçant votre dague par l'épée et le bouclier que l'instructeur de combat vous a donnés."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Combat interface Click on the flashing crossed swords icon to open the combat interface."
                ),
                "<col=0000ff>Interface de combat</col><br>Cliquez sur l'icône clignotante des épées croisées pour ouvrir l'interface de combat."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Combat interface This is your combat interface. From here, you can select the attack style that you'll use in combat. Using different attack styles will give different types of experience. As well as this, monsters are weak to specific attack styles. Click on the gates to continue."
                ),
                "<col=0000ff>Interface de combat</col><br>Voici votre interface de combat. Depuis ici, vous pouvez sélectionner le style d'attaque que vous utiliserez au combat. Utiliser différents styles d'attaque donne différents types d'expérience. De plus, les monstres sont faibles face à certains styles d'attaque. Cliquez sur les portes pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Attacking It's time to slay some rats! To attack a rat, all you have to do is click on it. This will cause you to walk over and start hitting it."
                ),
                "<col=0000ff>Attaquer</col><br>Il est temps de tuer quelques rats ! Pour attaquer un rat, il vous suffit de cliquer dessus. Cela vous fera avancer vers lui et commencera l'attaque."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Sit back and watch While you are fighting you will see a bar over your head. The bar shows how much health you have left. Your opponent will have one too. You will continue to attack the rat until it's dead or you do something else."
                ),
                "<col=0000ff>Observez un instant</col><br>Pendant le combat, vous verrez une barre au-dessus de votre tête. Elle montre combien de points de vie il vous reste. Votre adversaire en aura une aussi. Vous continuerez à attaquer le rat jusqu'à ce qu'il soit mort ou que vous fassiez autre chose."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Well done, you've made your first kill! Pass through the gate and talk to the combat instructor. He will give you your next task."
                ),
                "<col=0000ff>Bien joué, vous avez fait votre première victime !</col><br>Passez par la porte et parlez à l'instructeur de combat. Il vous donnera votre prochaine tâche."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Rat ranging Before you can use the shortbow and arrows you'll need to equip them by clicking on them. Once equipped with the ranging gear, try killing another rat. You don't need to enter the pen this time. To attack a rat, just click on it."
                ),
                "<col=0000ff>Combat à distance sur les rats</col><br>Avant de pouvoir utiliser l'arc court et les flèches, vous devrez les équiper en cliquant dessus. Une fois l'équipement à distance mis, essayez de tuer un autre rat. Cette fois, vous n'avez pas besoin d'entrer dans l'enclos. Pour attaquer un rat, cliquez simplement dessus."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on You have completed the tasks here. To move on, click on the indicated ladder. If you need to go over any of what you learnt here, just talk to the combat instructor and he'll tell you what he can."
                ),
                "<col=0000ff>En route</col><br>Vous avez terminé les tâches ici. Pour continuer, cliquez sur l'échelle indiquée. Si vous voulez revoir ce que vous avez appris, parlez à l'instructeur de combat et il vous expliquera ce qu'il peut."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Banking This is the Bank of Gielinor, where you can store all your most valued items. To open your bank, just click on the indicated booth."
                ),
                "<col=0000ff>Banque</col><br>Voici la Banque de Gielinor, où vous pouvez stocker tous vos objets les plus précieux. Pour ouvrir votre banque, cliquez simplement sur le guichet indiqué."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Banking This is your bank. You can store things here for safekeeping. To deposit something from your inventory, just click on it. You can withdraw things in the same way. To continue, close the bank and click on the indicated poll booth."
                ),
                "<col=0000ff>Banque</col><br>Voici votre banque. Vous pouvez y stocker des objets en sécurité. Pour déposer quelque chose depuis votre inventaire, cliquez simplement dessus. Vous pouvez retirer des objets de la même façon. Pour continuer, fermez la banque et cliquez sur le bureau de sondage indiqué."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on Polls are run periodically to let the Old School RuneScape community vote on how the game should - or shouldn't - change. When you're ready, move on through the door indicated."
                ),
                "<col=0000ff>En route</col><br>Des sondages sont organisés régulièrement pour permettre à la communauté Old School RuneScape de voter sur la manière dont le jeu devrait - ou ne devrait pas - évoluer. Quand vous êtes prêt, continuez par la porte indiquée."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Account Management The guide here will tell you all about your account. Just click on him to hear what he's got to say."
                ),
                "<col=0000ff>Gestion du compte</col><br>Le guide ici vous expliquera tout à propos de votre compte. Cliquez simplement sur lui pour entendre ce qu'il a à dire."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Account Management Click on the flashing icon to open your Account Management menu."
                ),
                "<col=0000ff>Gestion du compte</col><br>Cliquez sur l'icône clignotante pour ouvrir votre menu de gestion du compte."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Account Management This is your Account Management menu where you can control various aspects of your account. Talk to the Account Guide to learn more."
                ),
                "<col=0000ff>Gestion du compte</col><br>Voici votre menu de gestion du compte, où vous pouvez contrôler différents aspects de votre compte. Parlez au Guide du compte pour en apprendre davantage."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Moving on Continue through the next door."
                ),
                "<col=0000ff>En route</col><br>Continuez par la porte suivante."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Prayer menu Click on the flashing icon to open the Prayer menu."
                ),
                "<col=0000ff>Menu des prières</col><br>Cliquez sur l'icône clignotante pour ouvrir le menu des prières."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Prayer menu Talk with Brother Brace and he'll tell you about prayers."
                ),
                "<col=0000ff>Menu des prières</col><br>Parlez avec Frère Brace et il vous expliquera les prières."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Your final instructor! You're almost finished on tutorial island. Pass through the door to find the path leading to your final instructor."
                ),
                "<col=0000ff>Votre dernier instructeur !</col><br>Vous avez presque terminé l'île tutorielle. Passez par la porte pour trouver le chemin menant à votre dernier instructeur."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Your final instructor! Follow the path to the wizard's house, where you will be shown how to cast spells. When you get there, just talk with the magic instructor."
                ),
                "<col=0000ff>Votre dernier instructeur !</col><br>Suivez le chemin jusqu'à la maison du sorcier, où l'on vous montrera comment lancer des sorts. Une fois arrivé, parlez simplement à l'instructeur de magie."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Open up your final menu Open up the magic interface by clicking on the flashing icon."
                ),
                "<col=0000ff>Ouvrez votre dernier menu</col><br>Ouvrez l'interface de magie en cliquant sur l'icône clignotante."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Magic This is your magic interface. All of your spells can be found here. The list is currently filtered to only show the spells you have the Magic level for. You can change this in the filter menu. Talk to the instructor to continue."
                ),
                "<col=0000ff>Magie</col><br>Voici votre interface de magie. Tous vos sorts s'y trouvent. La liste est actuellement filtrée pour n'afficher que les sorts correspondant à votre niveau de Magie. Vous pouvez modifier cela dans le menu de filtre. Parlez à l'instructeur pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Magic casting You now have some runes. All spells require runes to cast them. Look for the Wind Strike spell in your magic interface. Click on this spell to select it and then click on a chicken to cast it. Talk to the instructor if you need more runes."
                ),
                "<col=0000ff>Lancer de sorts</col><br>Vous avez maintenant des runes. Tous les sorts nécessitent des runes pour être lancés. Cherchez le sort Frappe du vent dans votre interface de magie. Cliquez sur ce sort pour le sélectionner, puis cliquez sur un poulet pour le lancer. Parlez à l'instructeur si vous avez besoin de plus de runes."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "To the mainland! You're nearly finished with the tutorial. All you need to do now is move on to the mainland. Just speak with the magic instructor to continue."
                ),
                "<col=0000ff>Vers le continent !</col><br>Vous avez presque terminé le tutoriel. Il ne vous reste plus qu'à rejoindre le continent. Parlez simplement à l'instructeur de magie pour continuer."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "To the mainland! You're nearly finished with the tutorial. All you need to do now is move on to the mainland. Cast your Home Teleport spell to be taken to Lumbridge."
                ),
                "<col=0000ff>Vers le continent !</col><br>Vous avez presque terminé le tutoriel. Il ne vous reste plus qu'à rejoindre le continent. Lancez votre sort Téléportation maison pour être envoyé à Lumbridge."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Poll booths are found in towns across the world. The Old School RuneScape community is invited to vote on future game updates, to decide whether each update should or shouldn't be released."
                ),
                "Les bureaux de sondage se trouvent dans des villes du monde entier. La communauté Old School RuneScape est invitée à voter sur les futures mises à jour du jeu, afin de décider si chaque mise à jour doit ou non être publiée."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Voting is open to members with a skill total of 300. To preserve the old-school character of this game, an update must gain 70% support to be released."
                ),
                "Le vote est ouvert aux <col=7f0000>membres</col> ayant un <col=7f0000>total de compétences de 300</col>. Pour préserver le caractère old-school du jeu, une mise à jour doit obtenir <col=7f0000>70%</col> de soutien pour être publiée."
        );

        put(
                "Congratulations, you've completed a quest: Learning the Ropes",
                "Félicitations, vous avez terminé une quête : <col=081190>Apprendre les bases</col>"
        );

        put(
                "Your character is now attempting to light a fire. This should only take a few seconds.",
                "Votre personnage essaie maintenant d'allumer un feu. Cela ne devrait prendre que quelques secondes."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Firemaking Now that you have some logs, it's time to light a fire. First, click on the tinderbox in your inventory. Then, with the tinderbox highlighted, click on the logs to use the tinderbox on them."
                ),
                "<col=0000ff>Allumage du feu</col><br>Maintenant que vous avez des bûches, il est temps d'allumer un feu. Cliquez d'abord sur la boîte à amadou dans votre inventaire. Ensuite, avec la boîte à amadou sélectionnée, cliquez sur les bûches pour l'utiliser dessus."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "The master chef gives you some flour and some water."
                ),
                "Le maître chef vous donne de la <col=000080>farine</col> et de l'<col=000080>eau</col>."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "The combat instructor gives you a bronze sword and a wooden shield."
                ),
                "L'instructeur de combat vous donne une <col=000080>épée en bronze</col> et un <col=000080>bouclier en bois</col>."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "The combat instructor gives you a shortbow and some bronze arrows."
                ),
                "L'instructeur de combat vous donne un <col=000080>arc court</col> et des <col=000080>flèches en bronze</col>."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "Terrova gives you some air runes and mind runes."
                ),
                "Terrova vous donne des <col=000080>runes de l'air</col> et des <col=000080>runes mentales</col>."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "A flag appears on the booth to let you know when you're invited to vote in a poll."
                ),
                "Un drapeau apparaît sur le bureau pour vous indiquer lorsque vous êtes invité à voter à un sondage."
        );

        RAW_BY_VISIBLE.put(
                TranslationFileLoader.normKey(
                        "When you get to Lumbridge, look out for Adventurer Jon, he can be found outside of the Sheared Ram pub."
                ),
                "Lorsque vous arriverez à Lumbridge, cherchez l'Aventurier Jon ; il se trouve à l'extérieur du pub du Mouton tondu."
        );
    }

    private static void put(String englishVisible, String frenchRaw)
    {
        RAW_BY_VISIBLE.put(TranslationFileLoader.normKey(englishVisible), frenchRaw);
    }

    public static boolean isTutorialIslandInterface(int iface)
    {
        return iface == IFACE_TUTORIAL_DISPLAY_NAME
                || iface == IFACE_TUTORIAL_PLAYER_EXPERIENCE
                || iface == IFACE_TUTORIAL_OVERLAY;
    }

    public static String translateRawWidgetText(String rawText)
    {
        if (rawText == null || rawText.isEmpty())
        {
            return null;
        }

        String exact = RAW_BY_VISIBLE.get(TranslationFileLoader.normKey(rawText));
        if (exact != null)
        {
            return exact;
        }

        Matcher unavailable = DISPLAY_NAME_NOT_AVAILABLE_PATTERN.matcher(rawText);
        if (unavailable.matches())
        {
            String playerName = unavailable.group(1).trim();

            return "Désolé, le nom d'affichage <col=ffffff>"
                    + playerName
                    + "</col> est <col=ff0000>indisponible</col>.<br>Essayez plutôt de cliquer sur l'une de nos suggestions :";
        }

        Matcher available = DISPLAY_NAME_AVAILABLE_PATTERN.matcher(rawText);
        if (available.matches())
        {
            String playerName = available.group(1).trim();

            return "Super ! Le nom d'affichage <col=ffffff>"
                    + playerName
                    + "</col> est <col=00ff00>disponible</col> !<br>Vous pouvez choisir ce nom maintenant, ou en saisir un autre pour le vérifier.";
        }

        return null;
    }
}
