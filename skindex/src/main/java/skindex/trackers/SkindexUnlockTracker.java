package skindex.trackers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import skindex.bundles.Bundle;
import skindex.files.FileLocations;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.skins.player.AbstractPlayerSkin;

import java.util.ArrayList;
import java.util.HashMap;

public class SkindexUnlockTracker extends JsonDataFile{
    //region Singleton(ish)

    public static SkindexUnlockTracker[] instances = new SkindexUnlockTracker[3];
    public static SkindexUnlockTracker get(int saveSlot){
        if(instances[saveSlot] == null){
            instances[saveSlot] = load(saveSlot);
        }
        return instances[saveSlot];
    }
    public static SkindexUnlockTracker get(){
        return get(CardCrawlGame.saveSlot);
    }

    //endregion

    //region Variables
    public HashMap<String, ArrayList<String>> unlocks = new HashMap<>();

    public ArrayList<String> unlockedBundles = new ArrayList<>();
    public HashMap<String, ArrayList<String>> unlockedSkins = new HashMap<>();
    //endregion

    //region Constructors
    public SkindexUnlockTracker(int saveSlot) {
        super(FileLocations.trackerFiles[saveSlot]);
    }
    public SkindexUnlockTracker(){
        this(CardCrawlGame.saveSlot);
    }
    //endregion

    //region Class Methods

    //region Save/Load
    public static SkindexUnlockTracker load(int saveSlot){
        SkindexUnlockTracker tracker = (SkindexUnlockTracker) load(FileLocations.trackerFiles[saveSlot], SkindexUnlockTracker.class);
        if(tracker == null){
            tracker = new SkindexUnlockTracker(saveSlot);
        }

        return tracker;
    }
    public static SkindexUnlockTracker load(){
        return load(CardCrawlGame.saveSlot);
    }

    //endregion

    //region Item Unlocking

    public <T extends AbstractOwnableItem> boolean hasItem(T item){
        return hasItem(item.itemTypeClass, item.getId());
    }
    public boolean hasItem(Class<? extends AbstractOwnableItem> itemType, String id){
        ensureItemTypeExists(itemType);

        return unlocks.get(itemType.getName()).contains(id);
    }

    public boolean unlockItem(AbstractOwnableItem item){
        return unlockItem(item.itemTypeClass, item.getId());
    }
    public boolean unlockItem(Class<? extends AbstractOwnableItem> itemType, String id){
        ensureItemTypeExists(itemType);

        if(hasItem(itemType, id)) return false;

        unlocks.get(itemType.getName()).add(id);
        save();
        return true;
    }

    private void ensureItemTypeExists(Class<? extends AbstractOwnableItem> itemType){
        if(!unlocks.containsKey(itemType)){
            unlocks.put(itemType.getName(), new ArrayList<>());
        }
    }

    //endregion

    //region Bundle Unlocking

    public boolean hasBundle(Bundle b) {
        if(b == null) return false;
        return hasBundle(b.id);
    }
    public boolean hasBundle(String bundleId) {
        return unlockedBundles.contains(bundleId);
    }

    public boolean unlockBundle(Bundle b) {
        if(b == null) return false;
        return unlockBundle(b.id);
    }
    public boolean unlockBundle(String bundleId) {
        if(hasBundle(bundleId)) return false;

        unlockedBundles.add(bundleId);
        save();
        return true;
    }

    //endregion

    //region Skin Unlocking

    public boolean hasSkin(AbstractPlayerSkin skin){
        return hasSkin(skin.playerClass, skin.getId());
    }
    public boolean hasSkin(AbstractPlayer.PlayerClass playerClass, String skinId){
        return hasSkin(playerClass.name(), skinId);
    }
    public boolean hasSkin(String playerClass, String skinId){
        return unlockedSkins.get(playerClass).contains(skinId);
    }

    public boolean unlockSkin(AbstractPlayerSkin skin){
        return unlockSkin(skin.playerClass, skin.getId());
    }
    public boolean unlockSkin(AbstractPlayer.PlayerClass playerClass, String skinId){
        return unlockSkin(playerClass.name(), skinId);
    }
    public boolean unlockSkin(String playerClass, String skinId){
        if(hasSkin(playerClass, skinId)) return false;

        unlockedSkins.get(playerClass).add(skinId);
        save();
        return true;
    }

    //endregion

    //endregion
}
