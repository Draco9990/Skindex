package skindex.registering;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.random.Random;
import skindex.bundles.Bundle;
import skindex.callbacks.SkindexPostRegistryFinishCallback;
import skindex.skins.cards.CardSkin;
import skindex.skins.orb.OrbSkin;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.skins.stances.StanceSkin;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SkindexRegistry {
    /** Registrant Variables */
    private static ArrayList<SkindexUnlockMethodRegistrant> unlockMethodRegistrants = new ArrayList<>();
    private static ArrayList<SkindexBundleRegistrant> bundleRegistrants = new ArrayList<>();

    private static ArrayList<SkindexOrbSkinRegistrant> orbSkinRegistrants = new ArrayList<>();
    private static ArrayList<SkindexStanceSkinRegistrant> stanceSkinRegistrants = new ArrayList<>();
    private static ArrayList<SkindexCardSkinRegistrant> cardSkinRegistrants = new ArrayList<>();

    private static ArrayList<SkindexPlayerSkinRegistrant> playerSkinRegistrants = new ArrayList<>();

    private static ArrayList<SkindexPostRegistryFinishCallback> postRegistryFinishCallbacks = new ArrayList<>();

    /** Vairables */
    private static LinkedHashMap<String, UnlockMethod> unlockMethods = new LinkedHashMap<>();
    private static LinkedHashMap<String, Bundle> bundles = new LinkedHashMap<>();

    private static LinkedHashMap<String, OrbSkin> orbSkins = new LinkedHashMap<>();
    private static LinkedHashMap<String, StanceSkin> stanceSkins = new LinkedHashMap<>();

    private static LinkedHashMap<String, CardSkin> cardSkins = new LinkedHashMap<>();

    private static LinkedHashMap<AbstractPlayer.PlayerClass, HashMap<String, AbstractPlayerSkin>> playerSkins = new LinkedHashMap<>();
    private static LinkedHashMap<AbstractPlayer.PlayerClass, AbstractPlayerSkin> defaultPlayerSkins = new LinkedHashMap<>();

    /** Getters and Setters */
    public static void subscribe(ISkindexSubscriber skindexRegistrant){
        if(skindexRegistrant instanceof  SkindexUnlockMethodRegistrant) unlockMethodRegistrants.add((SkindexUnlockMethodRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexBundleRegistrant) bundleRegistrants.add((SkindexBundleRegistrant) skindexRegistrant);

        if(skindexRegistrant instanceof  SkindexOrbSkinRegistrant) orbSkinRegistrants.add((SkindexOrbSkinRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexStanceSkinRegistrant) stanceSkinRegistrants.add((SkindexStanceSkinRegistrant) skindexRegistrant);
        if(skindexRegistrant instanceof  SkindexCardSkinRegistrant) cardSkinRegistrants.add((SkindexCardSkinRegistrant) skindexRegistrant);

        if(skindexRegistrant instanceof  SkindexPlayerSkinRegistrant) playerSkinRegistrants.add((SkindexPlayerSkinRegistrant) skindexRegistrant);

        if(skindexRegistrant instanceof  SkindexPostRegistryFinishCallback) postRegistryFinishCallbacks.add((SkindexPostRegistryFinishCallback) skindexRegistrant);
    }
    public static void processRegistrants(){
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
            List<AbstractPlayerSkin> defaultPlayerSkinsToRegister = skindexRegistrant.getDefaultPlayerSkinsToRegister();
            if(defaultPlayerSkinsToRegister != null){
                for(AbstractPlayerSkin item : defaultPlayerSkinsToRegister){
                    registerDefaultPlayerSkin(item);
                }
            }

            List<AbstractPlayerSkin> playerSkinsToRegister = skindexRegistrant.getPlayerSkinsToRegister();
            if(playerSkinsToRegister != null){
                for(AbstractPlayerSkin item : skindexRegistrant.getPlayerSkinsToRegister()){
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
        if(orbSkin == null){
            return null;
        }

        if(!makeCopy) return orbSkin;

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

    private static void registerPlayerSkin(AbstractPlayerSkin playerSkin){
        if(playerSkin == null) return;

        if(!playerSkins.containsKey(playerSkin.playerClass)){
            playerSkins.put(playerSkin.playerClass, new LinkedHashMap<>());
        }

        playerSkins.get(playerSkin.playerClass).put(playerSkin.getId(), playerSkin);
    }
    public static AbstractPlayerSkin getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass playerClass, String id, boolean makeCopy){
        if(playerClass == null || id == null) return null;

        if(!playerSkins.containsKey(playerClass)){
            return null;
        }

        AbstractPlayerSkin playerSkin = playerSkins.get(playerClass).get(id);
        if(playerSkin == null || !makeCopy) return playerSkin;

        return (AbstractPlayerSkin) playerSkins.get(playerClass).get(id).makeCopy();
    }
    public static AbstractPlayerSkin getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass playerClass, String id){
        return getPlayerSkinByClassAndId(playerClass, id, false);
    }

    public static ArrayList<AbstractPlayerSkin> getSkinsForClass(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned, boolean makeCopy){
        if(playerClass == null) return new ArrayList<>();
        if(!playerSkins.containsKey(playerClass)) return new ArrayList<>();

        ArrayList<AbstractPlayerSkin> skins = new ArrayList<>(playerSkins.get(playerClass).values());

        skins.removeIf(skin -> skin.unlockMethod.id.equals(NonUnlockableUnlockMethod.methodId));
        if(onlyOwned){
            skins.removeIf(skin -> !skin.canUse());
        }

        if(makeCopy){
            ArrayList<AbstractPlayerSkin> skinArrayCopy = new ArrayList<>();
            for(AbstractPlayerSkin s : skins){
                skinArrayCopy.add((AbstractPlayerSkin) s.makeCopy());
            }
            return skinArrayCopy;
        }

        return skins;
    }
    public static ArrayList<AbstractPlayerSkin> getSkinsForClass(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned){
        return getSkinsForClass(playerClass, onlyOwned, false);
    }
    public static AbstractPlayerSkin getPreviousSkin(AbstractPlayerSkin current, boolean onlyOwned, boolean makeCopy){
        if(current == null) return null;

        ArrayList<AbstractPlayerSkin> skins = getSkinsForClass(current.playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        int indexOf = skins.indexOf(current);
        indexOf--;
        if(indexOf < 0) indexOf = skins.size() - 1;

        AbstractPlayerSkin skin = skins.get(indexOf);
        if(skin == null || !makeCopy) return skin;

        return (AbstractPlayerSkin) skin.makeCopy();
    }
    public static AbstractPlayerSkin getPreviousSkin(AbstractPlayerSkin current, boolean onlyOwned){
        return getPreviousSkin(current, onlyOwned, false);
    }
    public static AbstractPlayerSkin getNextSkin(AbstractPlayerSkin current, boolean onlyOwned, boolean makeCopy){
        if(current == null) return null;

        ArrayList<AbstractPlayerSkin> skins = getSkinsForClass(current.playerClass, onlyOwned, false);
        if(skins.isEmpty()) return null;

        int indexOf = skins.indexOf(current);
        indexOf++;
        if(indexOf >= skins.size()) indexOf = 0;

        AbstractPlayerSkin skin = skins.get(indexOf);
        if(skin == null || !makeCopy) return skin;

        return (AbstractPlayerSkin) skin.makeCopy();
    }
    public static AbstractPlayerSkin getNextSkin(AbstractPlayerSkin current, boolean onlyOwned){
        return getNextSkin(current, onlyOwned, false);
    }
    public static AbstractPlayerSkin getRandomSkin(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned, boolean makeCopy){
        if(playerClass == null) return null;

        ArrayList<AbstractPlayerSkin> skins = getSkinsForClass(playerClass, onlyOwned);
        if(skins.isEmpty()) return null;

        AbstractPlayerSkin skin = skins.get(new Random().random(skins.size()-1));
        if(skin == null || !makeCopy) return skin;

        return (AbstractPlayerSkin) skin.makeCopy();
    }
    public static AbstractPlayerSkin getRandomSkin(AbstractPlayer.PlayerClass playerClass, boolean onlyOwned){
        return getRandomSkin(playerClass, onlyOwned, false);
    }

    private static void registerDefaultPlayerSkin(AbstractPlayerSkin playerSkin){
        if(playerSkin == null) return;

        defaultPlayerSkins.put(playerSkin.playerClass, playerSkin);
        registerPlayerSkin(playerSkin);
    }
    public static AbstractPlayerSkin getDefaultPlayerSkinByClass(AbstractPlayer.PlayerClass playerClass, boolean makeCopy){
        if(playerClass == null) return null;

        AbstractPlayerSkin skin = defaultPlayerSkins.get(playerClass);
        if(skin == null || !makeCopy) return skin;

        return (AbstractPlayerSkin) skin.makeCopy();
    }
    public static AbstractPlayerSkin getDefaultPlayerSkinByClass(AbstractPlayer.PlayerClass playerClass){
        return getDefaultPlayerSkinByClass(playerClass, false);
    }

    public static ArrayList<AbstractPlayer.PlayerClass> getRegisteredPlayerClasses(){
        ArrayList<AbstractPlayer.PlayerClass> playerClasses = new ArrayList<>();
        playerClasses.addAll(defaultPlayerSkins.keySet());
        return playerClasses;
    }
}
