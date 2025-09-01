package skindex.trackers;

import com.codedisaster.steamworks.SteamRemoteStorage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import dLib.files.JsonStorageFileRules;
import dLib.util.Reflection;
import dLib.util.SerializationHelpers;
import dLib.util.helpers.SteamHelpers;
import skindex.bundles.Bundle;
import skindex.files.FileLocations;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.skins.entity.player.AbstractPlayerSkin;
import skindex.util.SkindexLogger;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class SkindexUnlockTracker extends JsonDataFile implements Serializable {
    public static final long serialVersionUID = 1L;

    //region Variables

    public HashMap<String, ArrayList<String>> unlocks = new HashMap<>();

    public ArrayList<String> unlockedBundles = new ArrayList<>();
    public HashMap<String, ArrayList<String>> unlockedSkins = new HashMap<>();

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
        if(!unlocks.containsKey(itemType.getName())){
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

    public static JsonStorageFileRules<SkindexUnlockTracker> makeRules(){
        JsonStorageFileRules<SkindexUnlockTracker> rules = new JsonStorageFileRules<>();

        rules.saveSteamCloud = true;
        rules.saveLocal = true;

        rules.localRelativeDirPath = "skindex";
        rules.fileName = "unlocks";
        rules.extension = ".skindex";
        rules.perSave = true;

        rules.makeNew = SkindexUnlockTracker::new;

        return rules;
    }
}
