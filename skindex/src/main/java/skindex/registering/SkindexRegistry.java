package skindex.registering;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.random.Random;
import skindex.bundles.Bundle;
import skindex.callbacks.SkindexPostRegistryFinishCallback;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.skins.cards.CardSkin;
import skindex.skins.orb.OrbSkin;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.skins.stances.StanceSkin;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.AbstractUnlockMethod;

import java.util.*;

public class SkindexRegistry {
    //region Variables
    private static HashMap<Class<? extends AbstractCustomizableItem>, LinkedHashMap<String, AbstractCustomizableItem>> items = new HashMap<>();

    private static HashMap<String, Bundle> bundles = new HashMap<>();
    private static HashMap<String, AbstractUnlockMethod> unlockMethods = new HashMap<>();

    private static LinkedHashMap<AbstractPlayer.PlayerClass, LinkedHashMap<String, AbstractPlayerSkin>> playerSkins = new LinkedHashMap<>();
    private static LinkedHashMap<AbstractPlayer.PlayerClass, AbstractPlayerSkin> defaultPlayerSkins = new LinkedHashMap<>();

    private static ArrayList<SkindexPostRegistryFinishCallback> postRegistryFinishCallbacks = new ArrayList<>();

    //region Class Methods

    //region Subscribing and Registering

    public static void subscribe(ISkindexSubscriber skindexRegistrant){
        if(skindexRegistrant instanceof SkindexPostRegistryFinishCallback) postRegistryFinishCallbacks.add((SkindexPostRegistryFinishCallback) skindexRegistrant);
    }
    public static void processRegistrants(){
        for(SkindexPostRegistryFinishCallback skindexRegistrant : postRegistryFinishCallbacks){
            if(skindexRegistrant != null){
                skindexRegistrant.onRegistryFinish();
            }
        }
    }

    public static void registerUnlockMethod(AbstractUnlockMethod unlockMethod){
        if(unlockMethod == null) return;

        unlockMethods.put(unlockMethod.id, unlockMethod);
    }

    public static void registerBundle(Bundle bundle){
        if(bundle == null) return;

        bundles.put(bundle.id, bundle);
    }

    public static void registerPlayerSkin(AbstractPlayerSkin playerSkin){
        if(playerSkin == null) return;

        if(playerSkin.isDefault){
            defaultPlayerSkins.put(playerSkin.playerClass, playerSkin);
        }
        else{
            if(!playerSkins.containsKey(playerSkin.playerClass)){
                playerSkins.put(playerSkin.playerClass, new LinkedHashMap<>());
            }

            playerSkins.get(playerSkin.playerClass).put(playerSkin.getId(), playerSkin);
        }
    }

    public static void registerItem(AbstractCustomizableItem item){
        if(item == null) return;

        if(item instanceof AbstractPlayerSkin){
            registerPlayerSkin((AbstractPlayerSkin) item);
        }
        else{
            if(!items.containsKey(item.itemTypeClass)){
                items.put(item.itemTypeClass, new LinkedHashMap<>());
            }

            items.get(item.itemTypeClass).put(item.getId(), item);
        }
    }

    //endregion

    //region Unlock Methods

    public static AbstractUnlockMethod getUnlockMethodById(String id){
        if(id == null) return null;
        return unlockMethods.get(id);
    }

    //endregion

    //region Bundles

    public static Bundle getBundleById(String bundleId){
        if(bundleId == null) return null;

        return bundles.get(bundleId);
    }

    public static ArrayList<Bundle> getAllBundles(){
        return new ArrayList<>(bundles.values());
    }

    //endregion

    //region Item Types

    public static <T extends AbstractCustomizableItem> T getItemById(Class<T> itemType, String id){
        return getItemById(itemType, id, false);
    }
    public static <T extends AbstractCustomizableItem> T getItemById(Class<T> itemType, String id, boolean makeCopy){
        if(itemType == null || id == null) return null;

        AbstractCustomizableItem item = items.get(itemType).get(id);
        if(item == null || !makeCopy) return (T) item;

        return (T) item.makeCopy();
    }

    public static <T extends AbstractCustomizableItem> ArrayList<T> getAllItemsOfType(Class<T> itemType){
        return getAllItemsOfType(itemType, false, false);
    }
    public static <T extends AbstractCustomizableItem> ArrayList<T> getAllItemsOfType(Class<T> itemType, boolean onlyOwned){
        return getAllItemsOfType(itemType, onlyOwned, false);
    }
    public static <T extends AbstractCustomizableItem> ArrayList<T> getAllItemsOfType(Class<T> itemType, boolean onlyOwned, boolean makeCopy){
        if(itemType == null || !items.containsKey(itemType)) return new ArrayList<>();

        ArrayList<T> itemsOfType = new ArrayList<T>((Collection<? extends T>) items.get(itemType).values());
        if(onlyOwned){
            itemsOfType.removeIf(item -> !((AbstractOwnableItem) item).canUse());
        }

        if(makeCopy){
            ArrayList<T> itemsCopy = new ArrayList<>();
            for(T item : itemsOfType){
                itemsCopy.add((T) item.makeCopy());
            }
            return itemsCopy;
        }

        return itemsOfType;
    }

    //endregion

    //region Player Skins

    public static AbstractPlayerSkin getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass playerClass, String id, boolean makeCopy){
        if(playerClass == null || id == null) return null;

        if(playerSkins.containsKey(playerClass)){
            AbstractPlayerSkin playerSkin = playerSkins.get(playerClass).get(id);

            if(playerSkin != null){
                if(!makeCopy) return playerSkin;

                return (AbstractPlayerSkin) playerSkins.get(playerClass).get(id).makeCopy();
            }
        }
        if(defaultPlayerSkins.containsKey(playerClass)){
            AbstractPlayerSkin playerSkin = defaultPlayerSkins.get(playerClass);

            if(playerSkin != null){
                if(!makeCopy) return playerSkin;

                return (AbstractPlayerSkin) playerSkin.makeCopy();
            }
        }

        return null;
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

    public static AbstractPlayerSkin getDefaultPlayerSkinByClass(AbstractPlayer.PlayerClass playerClass, boolean makeCopy){
        if(playerClass == null) return null;

        AbstractPlayerSkin skin = defaultPlayerSkins.get(playerClass);
        if(skin == null || !makeCopy) return skin;

        return (AbstractPlayerSkin) skin.makeCopy();
    }

    public static ArrayList<AbstractPlayer.PlayerClass> getRegisteredPlayerClasses(){
        ArrayList<AbstractPlayer.PlayerClass> playerClasses = new ArrayList<>();
        playerClasses.addAll(defaultPlayerSkins.keySet());
        return playerClasses;
    }

    //endregion

    //region Convenience Methods

    public static OrbSkin getOrbSkinById(String id, boolean makeCopy){
        return getItemById(OrbSkin.class, id, makeCopy);
    }

    public static StanceSkin getStanceSkinById(String id, boolean makeCopy){
        return getItemById(StanceSkin.class, id, makeCopy);
    }

    public static CardSkin getCardSkinById(String id, boolean makeCopy){
        return getItemById(CardSkin.class, id, makeCopy);
    }

    //endregion

    //endregion
}
