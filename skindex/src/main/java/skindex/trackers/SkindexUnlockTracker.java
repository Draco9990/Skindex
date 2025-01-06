package skindex.trackers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import skindex.Skindex;
import skindex.bundles.Bundle;
import skindex.files.FileLocations;
import skindex.itemtypes.OwnableItem;
import skindex.skins.player.PlayerSkin;

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
    public HashMap<Class<? extends OwnableItem>, ArrayList<String>> unlocks = new HashMap<>();
    public ArrayList<String> unlockedBundles = new ArrayList<>();
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

    public <T extends OwnableItem> boolean hasItem(T item){
        return hasItem(item.itemTypeClass, item.getId());
    }
    public boolean hasItem(Class<? extends OwnableItem> itemType, String id){
        ensureItemTypeExists(itemType);

        return unlocks.get(itemType).contains(id);
    }

    public boolean unlockItem(OwnableItem item){
        return unlockItem(item.itemTypeClass, item.getId());
    }
    public boolean unlockItem(Class<? extends OwnableItem> itemType, String id){
        ensureItemTypeExists(itemType);

        if(hasItem(itemType, id)) return false;

        unlocks.get(itemType).add(id);
        save();
        return true;
    }

    private void ensureItemTypeExists(Class<? extends OwnableItem> itemType){
        if(!unlocks.containsKey(itemType)){
            unlocks.put(itemType, new ArrayList<>());
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

    //endregion
}
