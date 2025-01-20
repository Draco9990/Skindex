package skindex.trackers;

import com.codedisaster.steamworks.SteamRemoteStorage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import dLib.util.Reflection;
import dLib.util.SerializationHelpers;
import dLib.util.helpers.SteamHelpers;
import skindex.bundles.Bundle;
import skindex.files.FileLocations;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.util.SkindexLogger;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class SkindexUnlockTracker extends JsonDataFile implements Serializable {
    public static final long serialVersionUID = 1L;

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


    @Override
    public void save() {
        super.save();

        if(SteamHelpers.isSteamAvailable()){
            SteamRemoteStorage remoteStorage = SteamHelpers.remoteStorage;
            if(remoteStorage != null){
                try{
                    remoteStorage.fileWrite("skindex_tracker_" + CardCrawlGame.saveSlot + ".json", SerializationHelpers.toByteBuffer(this));
                }catch (Exception e){
                    SkindexLogger.logError("Failed to save SkindexUnlockTracker to Steam Cloud due to " + e.getLocalizedMessage(), SkindexLogger.ErrorType.NON_FATAL);
                    e.printStackTrace();
                }
            }
        }
    }

    public static SkindexUnlockTracker load(int saveSlot){
        SkindexUnlockTracker tracker = (SkindexUnlockTracker) load(FileLocations.trackerFiles[saveSlot], SkindexUnlockTracker.class);
        if(tracker != null){
            return tracker;
        }

        if(SteamHelpers.isSteamAvailable()){
            SteamRemoteStorage remoteStorage = SteamHelpers.remoteStorage;
            if(remoteStorage != null){
                try{
                    ByteBuffer dataBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
                    remoteStorage.fileRead("skindex_tracker_" + saveSlot + ".json", dataBuffer);
                    tracker = SerializationHelpers.fromByteBuffer(dataBuffer);
                    if(tracker != null){
                        SkindexUnlockTracker emptyTracker = new SkindexUnlockTracker();

                        Reflection.setFieldValue("filePath", tracker, Reflection.getFieldValue("filePath", emptyTracker));
                        Reflection.setFieldValue("encrypted", tracker, Reflection.getFieldValue("encrypted", emptyTracker));
                        Reflection.setFieldValue("encryptionKey", tracker, Reflection.getFieldValue("encryptionKey", emptyTracker));

                        tracker.save();
                        return tracker;
                    }
                }catch (Exception e){
                    SkindexLogger.logError("Failed to load SkindexUnlockTracker from Steam Cloud due to " + e.getLocalizedMessage(), SkindexLogger.ErrorType.NON_FATAL);
                    e.printStackTrace();
                }
            }
        }

        tracker = new SkindexUnlockTracker(saveSlot);
        tracker.save();
        return tracker;
    }
    public static SkindexUnlockTracker load(){
        return load(CardCrawlGame.saveSlot);
    }

    //endregion

    //region Item Unlocking

    public <T extends AbstractOwnableItem> boolean hasItem(T item){
        return hasItem((Class<? extends AbstractOwnableItem>) item.itemTypeClass, item.getId());
    }
    public boolean hasItem(Class<? extends AbstractOwnableItem> itemType, String id){
        ensureItemTypeExists(itemType);

        return unlocks.get(itemType.getName()).contains(id);
    }

    public boolean unlockItem(AbstractOwnableItem item){
        return unlockItem((Class<? extends AbstractOwnableItem>) item.itemTypeClass, item.getId());
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

    public boolean hasPlayerSkin(AbstractPlayerSkin skin){
        return hasPlayerSkin(skin.playerClass, skin.getId());
    }
    public boolean hasPlayerSkin(AbstractPlayer.PlayerClass playerClass, String skinId){
        return hasPlayerSkin(playerClass.name(), skinId);
    }
    public boolean hasPlayerSkin(String playerClass, String skinId){
        if(!unlockedSkins.containsKey(playerClass)) return false;

        return unlockedSkins.get(playerClass).contains(skinId);
    }

    public boolean unlockPlayerSkin(AbstractPlayerSkin skin){
        return unlockPlayerSkin(skin.playerClass, skin.getId());
    }
    public boolean unlockPlayerSkin(AbstractPlayer.PlayerClass playerClass, String skinId){
        return unlockPlayerSkin(playerClass.name(), skinId);
    }
    public boolean unlockPlayerSkin(String playerClass, String skinId){
        if(hasPlayerSkin(playerClass, skinId)) return false;

        if(!unlockedSkins.containsKey(playerClass)){
            unlockedSkins.put(playerClass, new ArrayList<>());
        }

        unlockedSkins.get(playerClass).add(skinId);
        save();
        return true;
    }

    //endregion

    //endregion
}
