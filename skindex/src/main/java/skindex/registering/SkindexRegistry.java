package skindex.registering;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.random.Random;
import skindex.bundles.Bundle;
import skindex.skins.cards.CardSkin;
import skindex.skins.player.PlayerSkin;
import skindex.skins.player.PlayerSkinData;
import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SkindexRegistry {
    /** Registrant Variables */
    private static ArrayList<SkindexTrackerRegistrant> trackerRegistrants = new ArrayList<>();
    private static ArrayList<SkindexUnlockMethodRegistrant> unlockMethodRegistrants = new ArrayList<>();
    private static ArrayList<SkindexBundleRegistrant> bundleRegistrants = new ArrayList<>();
    private static ArrayList<SkindexCardSkinRegistrant> cardSkinRegistrants = new ArrayList<>();
    private static ArrayList<SkindexPlayerSkinRegistrant> playerSkinRegistrants = new ArrayList<>();

    /** Vairables */
    private static LinkedHashMap<String, Bundle> bundles = new LinkedHashMap<>();

    private static LinkedHashMap<AbstractPlayer.PlayerClass, HashMap<String, PlayerSkin>> playerSkins = new LinkedHashMap<>();
    private static LinkedHashMap<AbstractPlayer.PlayerClass, PlayerSkin> defaultPlayerSkins = new LinkedHashMap<>();

    private static LinkedHashMap<String, CardSkin> cardSkins = new LinkedHashMap<>();

    private static LinkedHashMap<String, UnlockMethod> unlockMethods = new LinkedHashMap<>();

    private static LinkedHashMap<String, SkindexTracker> trackers = new LinkedHashMap<>();

    /** Getters and Setters */
    public static void subscribe(ISkindexSubscriber skindexRegistrant){
        if(skindexRegistrant instanceof  SkindexTrackerRegistrant) trackerRegistrants.add((SkindexTrackerRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexUnlockMethodRegistrant) unlockMethodRegistrants.add((SkindexUnlockMethodRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexBundleRegistrant) bundleRegistrants.add((SkindexBundleRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexCardSkinRegistrant) cardSkinRegistrants.add((SkindexCardSkinRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexPlayerSkinRegistrant) playerSkinRegistrants.add((SkindexPlayerSkinRegistrant) skindexRegistrant);
    }
    public static void processRegistrants(){
        for(SkindexTrackerRegistrant skindexRegistrant : trackerRegistrants){
            for(SkindexTracker item : skindexRegistrant.getTrackersToRegister()){
                registerTracker(item);
            }
        }
        for(SkindexUnlockMethodRegistrant skindexRegistrant : unlockMethodRegistrants){
            for(UnlockMethod item : skindexRegistrant.getUnlockMethodsToRegister()){
                registerUnlockMethod(item);
            }
        }
        for(SkindexBundleRegistrant skindexRegistrant : bundleRegistrants){
            for(Bundle item : skindexRegistrant.getBundlesToRegister()){
                registerBundle(item);
            }
        }
        for(SkindexCardSkinRegistrant skindexRegistrant : cardSkinRegistrants){
            for(CardSkin item : skindexRegistrant.getCardSkinsToRegister()){
                registerCardSkin(item);
            }
        }
        for(SkindexPlayerSkinRegistrant skindexRegistrant : playerSkinRegistrants){
            for(PlayerSkin item : skindexRegistrant.getDefaultPlayerSkinsToRegister()){
                registerDefaultPlayerSkin(item);
            }
            for(PlayerSkin item : skindexRegistrant.getPlayerSkinsToRegister()){
                registerPlayerSkin(item);
            }
        }
    }

    private static void registerBundle(Bundle bundle){
        if(bundle == null) return;

        bundles.put(bundle.id, bundle);
    }
    public static Bundle getBundleById(String bundleId){
        if(bundleId == null) return null;

        return bundles.get(bundleId);
    }
    public static ArrayList<Bundle> getAllBundles(){
        return new ArrayList<>(bundles.values());
    }

    private static void registerPlayerSkin(PlayerSkin playerSkin){
        if(playerSkin == null) return;

        if(!playerSkins.containsKey(playerSkin.playerClass)){
            playerSkins.put(playerSkin.playerClass, new LinkedHashMap<>());
        }

        playerSkins.get(playerSkin.playerClass).put(playerSkin.id, playerSkin);
    }
    public static PlayerSkin getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass playerClass, String id){
        if(playerClass == null || id == null) return null;

        if(!playerSkins.containsKey(playerClass)){
            return null;
        }
        return playerSkins.get(playerClass).get(id);
    }

    public static ArrayList<PlayerSkin> getSkinsForClass(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned){
        if(playerClass == null) return new ArrayList<>();
        if(!playerSkins.containsKey(playerClass)) return new ArrayList<>();

        ArrayList<PlayerSkin> skins = new ArrayList<>(playerSkins.get(playerClass).values());

        skins.removeIf(skin -> skin.unlockMethod.id.equals(NonUnlockableUnlockMethod.methodId));
        if(onlyOwned){
            skins.removeIf(skin -> !skin.canUse());
        }

        return skins;
    }
    public static PlayerSkin getPreviousSkin(PlayerSkin current, boolean onlyOwned){
        if(current == null) return null;

        ArrayList<PlayerSkin> skins = getSkinsForClass(current.playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        int indexOf = skins.indexOf(current);
        indexOf--;
        if(indexOf < 0) indexOf = skins.size() - 1;

        return skins.get(indexOf);
    }
    public static PlayerSkin getNextSkin(PlayerSkin current, boolean onlyOwned){
        if(current == null) return null;

        ArrayList<PlayerSkin> skins = getSkinsForClass(current.playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        int indexOf = skins.indexOf(current);
        indexOf++;
        if(indexOf >= skins.size()) indexOf = 0;

        return skins.get(indexOf);
    }
    public static PlayerSkin getRandomSkin(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned){
        if(playerClass == null) return null;

        ArrayList<PlayerSkin> skins = getSkinsForClass(playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        return skins.get(new Random().random(skins.size()));
    }

    private static void registerDefaultPlayerSkin(PlayerSkin playerSkin){
        if(playerSkin == null) return;

        defaultPlayerSkins.put(playerSkin.playerClass, playerSkin);
        registerPlayerSkin(playerSkin);
    }
    public static PlayerSkin getDefaultPlayerSkinByClass(AbstractPlayer.PlayerClass playerClass){
        if(playerClass == null) return null;

        return defaultPlayerSkins.get(playerClass);
    }

    private static void registerCardSkin(CardSkin cardSkin){
        if(cardSkin == null) return;

        cardSkins.put(cardSkin.id, cardSkin);
    }
    public static CardSkin getCardSkinById(String id){
        if(id == null) return null;

        return cardSkins.get(id);
    }

    private static void registerUnlockMethod(UnlockMethod unlockMethod){
        if(unlockMethod == null) return;

        unlockMethods.put(unlockMethod.id, unlockMethod);
    }
    public static UnlockMethod getUnlockMethodById(String id){
        if(id == null) return null;
        return unlockMethods.get(id);
    }

    private static void registerTracker(SkindexTracker tracker){
        if(tracker == null) return;

        trackers.put(tracker.getId(), tracker);
    }
    public static SkindexTracker getTrackerById(String id){
        if(id == null) return null;

        return trackers.get(id);
    }
}
