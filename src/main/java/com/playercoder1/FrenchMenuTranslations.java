package com.playercoder1;

import java.util.Locale;
import java.util.Map;

public final class FrenchMenuTranslations {
    private FrenchMenuTranslations() {}

    private static String norm(String s) {
        return s.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    private static final Map < String, String > MENU_OPTIONS = Map.ofEntries(

            Map.entry("walk here", "Marcher ici"),
            Map.entry("follow", "Suivre"),
            Map.entry("trade with", "Échanger avec"),
            Map.entry("trade", "Échanger"),
            Map.entry("report", "Signaler"),
            Map.entry("examine", "Examiner"),
            Map.entry("inspect", "Inspecter"),
            Map.entry("attack", "Attaquer"),
            Map.entry("pickpocket", "Voler à la tire"),
            Map.entry("enter", "Entrer"),
            Map.entry("take", "Prendre"),
            Map.entry("talk-to", "Parler à"),
            Map.entry("commune", "Communier"),

            Map.entry("climb-up", "Monter"),
            Map.entry("climb-down", "Descendre"),
            Map.entry("walk-down", "Descendre"),
            Map.entry("pass-through", "Passer"),
            Map.entry("join", "Rejoindre"),
            Map.entry("observe", "Observer"),
            Map.entry("study", "Étudier"),
            Map.entry("read", "Lire"),
            Map.entry("view", "Voir"),
            Map.entry("tutorial", "Tutoriel"),

            Map.entry("bank", "Banque"),
            Map.entry("collect", "Collecter"),
            Map.entry("search", "Fouiller"),
            Map.entry("check", "Vérifier"),
            Map.entry("setup", "Configurer"),
            Map.entry("configure", "Configurer"),
            Map.entry("toggle warnings", "Activer/Désactiver les avertissements"),
            Map.entry("lock", "Verrouiller"),

            Map.entry("wield", "Manier"),
            Map.entry("wear", "Porter"),
            Map.entry("use", "Utiliser"),
            Map.entry("drop", "Jeter"),
            Map.entry("destroy", "Détruire"),
            Map.entry("empty", "Vider"),
            Map.entry("rub", "Frotter"),
            Map.entry("charge", "Charger"),
            Map.entry("uncharge", "Décharger"),
            Map.entry("revert", "Rétablir"),
            Map.entry("upgrade", "Améliorer"),
            Map.entry("armour", "Armure"),

            Map.entry("build", "Construire"),
            Map.entry("travel", "Voyager"),
            Map.entry("travel to plateform", "Voyager vers la plateforme"),
            Map.entry("teleport", "Téléporter"),
            Map.entry("teleport menu", "Menu de téléportation"),

            Map.entry("remove", "Retirer"),
            Map.entry("remove board advert", "Retirer l'annonce du panneau"),
            Map.entry("deposit", "Déposer"),
            Map.entry("dump", "Déverser"),
            Map.entry("exchange", "Échanger"),
            Map.entry("cast", "Lancer"),
            Map.entry("pray-at", "Prier à"),
            Map.entry("venerate", "Vénérer"),
            Map.entry("claim-trophies", "Réclamer les trophées"),
            Map.entry("sacrifice", "Sacrifier"),
            Map.entry("toggle-minerals", "Activer/Désactiver minéraux"),

            Map.entry("pay (north)", "Payer (nord)"),
            Map.entry("pay (south)", "Payer (sud)"),

            Map.entry("top-floor", "Étage supérieur"),
            Map.entry("bottom-floor", "Étage inférieur"),

            Map.entry("chop down", "Abattre"),
            Map.entry("chop-down", "Abattre"),

            Map.entry("cook", "Cuisiner"),
            Map.entry("cook-at", "Cuisiner à"),

            Map.entry("pick-up", "Ramasser"),
            Map.entry("take-5", "Prendre-5"),
            Map.entry("take-10", "Prendre-10"),
            Map.entry("take-x", "Prendre-X"),

            Map.entry("get-rewards", "Obtenir les récompenses"),
            Map.entry("claim-rewards", "Réclamer les récompenses"),
            Map.entry("claim-tokens", "Réclamer les jetons"),
            Map.entry("claim-shield", "Réclamer le bouclier"),

            Map.entry("cancel-task", "Annuler la tâche"),

            Map.entry("eat", "Manger"),
            Map.entry("drink", "Boire"),

            Map.entry("cancel", "Annuler"),
            Map.entry("open", "Ouvrir"),
            Map.entry("close", "Fermer"),

            Map.entry("lure", "Leurre"),
            Map.entry("bait", "Appât"),
            Map.entry("small net", "Petit filet de pêche"),
            Map.entry("take-net", "Prendre le filet"),
            Map.entry("net", "Filet"),
            Map.entry("cage", "Cage"),
            Map.entry("harpoon", "Harpon"),

            Map.entry("mine", "Miner"),

            Map.entry("check-health", "Vérifier la santé"),
            Map.entry("guide", "Guide"),
            Map.entry("pick-from", "Cueillir sur"),
            Map.entry("rake", "Râteler"),
            Map.entry("harvest", "Récolter"),
            Map.entry("pick", "Cueillir"),

            Map.entry("loot", "Butin"),
            Map.entry("prices", "Prix"),
            Map.entry("decant", "Décanter"),
            Map.entry("climb-into", "Monter dans"),
            Map.entry("assignment", "Affectation"),
            Map.entry("rewards", "Récompenses"),
            Map.entry("smelt", "Fondre"),
            Map.entry("skull", "Tête de mort"),
            Map.entry("go-down", "Descendre"),
            Map.entry("climb", "Monter"),
            Map.entry("pull", "Tirer"),
            Map.entry("shut", "Fermer"),
            Map.entry("smith", "Forger"),

            Map.entry("check-count", "Vérifier le nombre"),
            Map.entry("slash", "Trancher"),
            Map.entry("relocate", "Déplacer"),
            Map.entry("redecorate", "Redécorer"),

            Map.entry("squeeze-through", "Se faufiler"),
            Map.entry("climb-through", "Grimper à travers"),
            Map.entry("peek", "Jeter un œil"),
            Map.entry("jump", "Sauter"),
            Map.entry("cross", "Traverser"),
            Map.entry("walk-across", "Marcher dessus"),
            Map.entry("climb-over", "Escalader"),
            Map.entry("walk-on", "Marcher dessus"),

            Map.entry("withdraw-1", "Retirer-1"),
            Map.entry("withdraw-5", "Retirer-5"),
            Map.entry("withdraw-10", "Retirer-10"),
            Map.entry("withdraw-x", "Retirer-X"),
            Map.entry("withdraw-all", "Retirer-tout"),
            Map.entry("withdraw-all-but-1", "Retirer-tout-sauf-1"),

            Map.entry("board", "Monter à bord"),
            Map.entry("board-previous", "Monter à bord (précédent)"),
            Map.entry("board-friend", "Monter à bord (ami)"),
            Map.entry("dock", "Accoster"),
            Map.entry("ferry", "Ferry"),

            Map.entry("quick-climb", "Monter rapidement"),
            Map.entry("quick-start", "Démarrage rapide"),
            Map.entry("solo-start", "Démarrer en solo"),
            Map.entry("sort-salvage", "Trier le butin"),
            Map.entry("cut", "Couper"),
            Map.entry("go-through", "Traverser"),
            Map.entry("look-at", "Regarder"),
            Map.entry("reinvigorate", "Revigorer"),
            Map.entry("check-approval", "Vérifier l’approbation"),
            Map.entry("animate", "Animer"),

            Map.entry("fill", "Remplir"),
            Map.entry("check-ammo", "Vérifier les munitions"),
            Map.entry("tether", "Attacher"),
            Map.entry("forfeit", "Abandonner")
    );

    public static String translateOption(String englishOption) {
        if (englishOption == null || englishOption.isEmpty()) {
            return null;
        }
        return MENU_OPTIONS.get(norm(englishOption));
    }
}