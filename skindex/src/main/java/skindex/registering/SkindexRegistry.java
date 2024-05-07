package skindex.registering;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.random.Random;
import skindex.bundles.Bundle;
import skindex.callbacks.SkindexPostRegistryFinishCallback;
import skindex.skins.cards.CardSkin;
import skindex.skins.orb.OrbSkin;
import skindex.skins.player.PlayerSkin;
import skindex.skins.stances.StanceSkin;
import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SkindexRegistry {
    /** Registrant Variables */
    private static ArrayList<SkindexTrackerRegistrant> trackerRegistrants = new ArrayList<>();
    private static ArrayList<SkindexUnlockMethodRegistrant> unlockMethodRegistrants = new ArrayList<>();
    private static ArrayList<SkindexBundleRegistrant> bundleRegistrants = new ArrayList<>();

    private static ArrayList<SkindexOrbSkinRegistrant> orbSkinRegistrants = new ArrayList<>();
    private static ArrayList<SkindexStanceSkinRegistrant> stanceSkinRegistrants = new ArrayList<>();
    private static ArrayList<SkindexCardSkinRegistrant> cardSkinRegistrants = new ArrayList<>();

    private static ArrayList<SkindexPlayerSkinRegistrant> playerSkinRegistrants = new ArrayList<>();

    private static ArrayList<SkindexPostRegistryFinishCallback> postRegistryFinishCallbacks = new ArrayList<>();

    /** Vairables */
    private static LinkedHashMap<Integer, LinkedHashMap<String, SkindexTracker>> trackers = new LinkedHashMap<Integer, LinkedHashMap<String, SkindexTracker>>();

    private static LinkedHashMap<String, UnlockMethod> unlockMethods = new LinkedHashMap<>();
    private static LinkedHashMap<String, Bundle> bundles = new LinkedHashMap<>();

    private static LinkedHashMap<String, OrbSkin> orbSkins = new LinkedHashMap<>();
    private static LinkedHashMap<String, StanceSkin> stanceSkins = new LinkedHashMap<>();

    private static LinkedHashMap<String, CardSkin> cardSkins = new LinkedHashMap<>();

    private static LinkedHashMap<AbstractPlayer.PlayerClass, HashMap<String, PlayerSkin>> playerSkins = new LinkedHashMap<>();
    private static LinkedHashMap<AbstractPlayer.PlayerClass, PlayerSkin> defaultPlayerSkins = new LinkedHashMap<>();

    /** Getters and Setters */
    public static void subscribe(ISkindexSubscriber skindexRegistrant){
        if(skindexRegistrant instanceof  SkindexTrackerRegistrant) trackerRegistrants.add((SkindexTrackerRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexUnlockMethodRegistrant) unlockMethodRegistrants.add((SkindexUnlockMethodRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexBundleRegistrant) bundleRegistrants.add((SkindexBundleRegistrant) skindexRegistrant);

        if(skindexRegistrant instanceof  SkindexOrbSkinRegistrant) orbSkinRegistrants.add((SkindexOrbSkinRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexStanceSkinRegistrant) stanceSkinRegistrants.add((SkindexStanceSkinRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexCardSkinRegistrant) cardSkinRegistrants.add((SkindexCardSkinRegistrant) skindexRegistrant);

        if(skindexRegistrant instanceof  SkindexPlayerSkinRegistrant) playerSkinRegistrants.add((SkindexPlayerSkinRegistrant) skindexRegistrant);

        if(skindexRegistrant instanceof  SkindexPostRegistryFinishCallback) postRegistryFinishCallbacks.add((SkindexPostRegistryFinishCallback) skindexRegistrant);
    }
    public static void processRegistrants(){
        for(SkindexTrackerRegistrant skindexRegistrant : trackerRegistrants){
            for(int i = 0; i < 3; i++){
                List<SkindexTracker> trackersToRegister = skindexRegistrant.getTrackersToRegister(i);
                if(trackersToRegister == null) continue;

                for(SkindexTracker item : trackersToRegister){
                    registerTracker(i, item);
                }
            }
        }
        for(SkindexUnlockMethodRegistrant skindexRegistrant : unlockMethodRegistrants){
            List<UnlockMethod> unlockMethodsToRegister = skindexRegistrant.getUnlockMethodsToRegister();
            if(unlockMethodsToRegister == null) continue;

            for(UnlockMethod item : unlockMethodsToRegister){
                registerUnlockMethod(item);
            }
        }
        for(SkindexBundleRegistrant skindexRegistrant : bundleRegistrants){
            List<Bundle> bundlesToRegister = skindexRegistrant.getBundlesToRegister();
            if(bundlesToRegister == null) continue;

            for(Bundle item : bundlesToRegister){
                registerBundle(item);
            }
        }

        for(SkindexOrbSkinRegistrant skindexRegistrant : orbSkinRegistrants){
            List<OrbSkin> orbSkinsToRegister = skindexRegistrant.getOrbSkinsToRegister();
            if(orbSkinsToRegister == null) continue;

            for(OrbSkin item : orbSkinsToRegister){
                registerOrbSkin(item);
            }
        }
        for(SkindexStanceSkinRegistrant skindexRegistrant : stanceSkinRegistrants){
            List<StanceSkin> stanceSkinsToRegister = skindexRegistrant.getStanceSkinsToRegister();
            if(stanceSkinsToRegister == null) continue;

            for(StanceSkin item : stanceSkinsToRegister){
                registerStanceSkin(item);
            }
        }
        for(SkindexCardSkinRegistrant skindexRegistrant : cardSkinRegistrants){
            List<CardSkin> cardSkinsToRegister = skindexRegistrant.getCardSkinsToRegister();
            if(cardSkinsToRegister == null) continue;

            for(CardSkin item : cardSkinsToRegister){
                registerCardSkin(item);
            }
        }

        for(SkindexPlayerSkinRegistrant skindexRegistrant : playerSkinRegistrants){
            List<PlayerSkin> defaultPlayerSkinsToRegister = skindexRegistrant.getDefaultPlayerSkinsToRegister();
            if(defaultPlayerSkinsToRegister != null){
                for(PlayerSkin item : defaultPlayerSkinsToRegister){
                    registerDefaultPlayerSkin(item);
                }
            }

            List<PlayerSkin> playerSkinsToRegister = skindexRegistrant.getPlayerSkinsToRegister();
            if(playerSkinsToRegister != null){
                for(PlayerSkin item : skindexRegistrant.getPlayerSkinsToRegister()){
                    registerPlayerSkin(item);
                }
            }
        }

        for(SkindexPostRegistryFinishCallback skindexRegistrant : postRegistryFinishCallbacks){
            if(skindexRegistrant != null){
                skindexRegistrant.onRegistryFinish();
            }
        }
    }

    private static void registerUnlockMethod(UnlockMethod unlockMethod){
        if(unlockMethod == null) return;

        unlockMethods.put(unlockMethod.id, unlockMethod);
    }
    public static UnlockMethod getUnlockMethodById(String id){
        if(id == null) return null;
        return unlockMethods.get(id);
    }

    private static void registerTracker(int saveSlot, SkindexTracker tracker){
        if(tracker == null) return;

        if(!trackers.containsKey(saveSlot)){
            trackers.put(saveSlot, new LinkedHashMap<>());
        }

        trackers.get(saveSlot).put(tracker.getId(), tracker);
    }
    public static SkindexTracker getTrackerById(String id){
        if(id == null) return null;

        if(!trackers.containsKey(CardCrawlGame.saveSlot)){
            return null;
        }

        return trackers.get(CardCrawlGame.saveSlot).get(id);
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

    private static void registerOrbSkin(OrbSkin orbSkin){
        if(orbSkin == null) return;

        orbSkins.put(orbSkin.getId(), orbSkin);
    }
    public static OrbSkin getOrbSkinById(String id, boolean makeCopy){
        if(id == null) return null;

        OrbSkin orbSkin = orbSkins.get(id);
        if(orbSkin == null || !makeCopy) return orbSkin;

        return (OrbSkin) orbSkin.makeCopy();
    }
    public static OrbSkin getOrbSkinById(String id){
        return getOrbSkinById(id, false);
    }

    private static void registerStanceSkin(StanceSkin stanceSkin){
        if(stanceSkin == null) return;

        stanceSkins.put(stanceSkin.getId(), stanceSkin);
    }
    public static StanceSkin getStanceSkinById(String id, boolean makeCopy){
        if(id == null) return null;

        StanceSkin stanceSkin = stanceSkins.get(id);
        if(stanceSkin == null || !makeCopy) return stanceSkin;

        return (StanceSkin) stanceSkin.makeCopy();
    }
    public static StanceSkin getStanceSkinById(String id){
        return getStanceSkinById(id, false);
    }

    private static void registerCardSkin(CardSkin cardSkinSet){
        if(cardSkinSet == null) return;

        cardSkins.put(cardSkinSet.getId(), cardSkinSet);
    }
    public static CardSkin getCardSkin(String id, boolean makeCopy){
        if(id == null) return null;

        CardSkin cardSkin = cardSkins.get(id);
        if(cardSkin == null || !makeCopy) return cardSkin;

        return (CardSkin) cardSkin.makeCopy();
    }
    public static CardSkin getCardSkin(String id){
        return getCardSkin(id, false);
    }

    private static void registerPlayerSkin(PlayerSkin playerSkin){
        if(playerSkin == null) return;

        if(!playerSkins.containsKey(playerSkin.playerClass)){
            playerSkins.put(playerSkin.playerClass, new LinkedHashMap<>());
        }

        playerSkins.get(playerSkin.playerClass).put(playerSkin.getId(), playerSkin);
    }
    public static PlayerSkin getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass playerClass, String id, boolean makeCopy){
        if(playerClass == null || id == null) return null;

        if(!playerSkins.containsKey(playerClass)){
            return null;
        }

        PlayerSkin playerSkin = playerSkins.get(playerClass).get(id);
        if(playerSkin == null || !makeCopy) return playerSkin;

        return (PlayerSkin) playerSkins.get(playerClass).get(id).makeCopy();
    }
    public static PlayerSkin getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass playerClass, String id){
        return getPlayerSkinByClassAndId(playerClass, id, false);
    }

    public static ArrayList<PlayerSkin> getSkinsForClass(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned, boolean makeCopy){
        if(playerClass == null) return new ArrayList<>();
        if(!playerSkins.containsKey(playerClass)) return new ArrayList<>();

        ArrayList<PlayerSkin> skins = new ArrayList<>(playerSkins.get(playerClass).values());

        skins.removeIf(skin -> skin.unlockMethod.id.equals(NonUnlockableUnlockMethod.methodId));
        if(onlyOwned){
            skins.removeIf(skin -> !skin.canUse());
        }

        if(makeCopy){
            ArrayList<PlayerSkin> skinArrayCopy = new ArrayList<>();
            for(PlayerSkin s : skins){
                skinArrayCopy.add((PlayerSkin) s.makeCopy());
            }
            return skinArrayCopy;
        }

        return skins;
    }
    public static ArrayList<PlayerSkin> getSkinsForClass(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned){
        return getSkinsForClass(playerClass, onlyOwned, false);
    }
    public static PlayerSkin getPreviousSkin(PlayerSkin current, boolean onlyOwned, boolean makeCopy){
        if(current == null) return null;

        ArrayList<PlayerSkin> skins = getSkinsForClass(current.playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        int indexOf = skins.indexOf(current);
        indexOf--;
        if(indexOf < 0) indexOf = skins.size() - 1;

        PlayerSkin skin = skins.get(indexOf);
        if(skin == null || !makeCopy) return skin;

        return (PlayerSkin) skin.makeCopy();
    }
    public static PlayerSkin getPreviousSkin(PlayerSkin current, boolean onlyOwned){
        return getPreviousSkin(current, onlyOwned, false);
    }
    public static PlayerSkin getNextSkin(PlayerSkin current, boolean onlyOwned, boolean makeCopy){
        if(current == null) return null;

        ArrayList<PlayerSkin> skins = getSkinsForClass(current.playerClass, onlyOwned, false);
        if(skins.isEmpty()) return null;

        int indexOf = skins.indexOf(current);
        indexOf++;
        if(indexOf >= skins.size()) indexOf = 0;

        PlayerSkin skin = skins.get(indexOf);
        if(skin == null || !makeCopy) return skin;

        return (PlayerSkin) skin.makeCopy();
    }
    public static PlayerSkin getNextSkin(PlayerSkin current, boolean onlyOwned){
        return getNextSkin(current, onlyOwned, false);
    }
    public static PlayerSkin getRandomSkin(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned, boolean makeCopy){
        if(playerClass == null) return null;

        ArrayList<PlayerSkin> skins = getSkinsForClass(playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        PlayerSkin skin = skins.get(new Random().random(skins.size()-1));
        if(skin == null || !makeCopy) return skin;

        return (PlayerSkin) skin.makeCopy();
    }
    public static PlayerSkin getRandomSkin(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned){
        return getRandomSkin(playerClass, onlyOwned, false);
    }

    private static void registerDefaultPlayerSkin(PlayerSkin playerSkin){
        if(playerSkin == null) return;

        defaultPlayerSkins.put(playerSkin.playerClass, playerSkin);
        registerPlayerSkin(playerSkin);
    }
    public static PlayerSkin getDefaultPlayerSkinByClass(AbstractPlayer.PlayerClass playerClass, boolean makeCopy){
        if(playerClass == null) return null;

        PlayerSkin skin = defaultPlayerSkins.get(playerClass);
        if(skin == null || !makeCopy) return skin;

        return (PlayerSkin) skin.makeCopy();
    }
    public static PlayerSkin getDefaultPlayerSkinByClass(AbstractPlayer.PlayerClass playerClass){
        return getDefaultPlayerSkinByClass(playerClass, false);
    }

    public static ArrayList<AbstractPlayer.PlayerClass> getRegisteredPlayerClasses(){
        ArrayList<AbstractPlayer.PlayerClass> playerClasses = new ArrayList<>();
        playerClasses.addAll(defaultPlayerSkins.keySet());
        return playerClasses;
    }
}
