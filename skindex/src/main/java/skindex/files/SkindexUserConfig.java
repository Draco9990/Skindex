package skindex.files;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import dLib.files.JsonStorageFileRules;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.registering.SkindexRegistry;
import skindex.skins.entity.player.AbstractPlayerSkin;
import skindex.trackers.SkindexUnlockTracker;

import java.io.Serializable;
import java.util.HashMap;

public class SkindexUserConfig extends JsonDataFile implements Serializable {
    //region Variables

    public HashMap<String, String> favouritedItems = new HashMap<>();

    public HashMap<String, String> favouritedSkins = new HashMap<>();

    //endregion

    //region Class Methods

    //region Favourited Items

    public <T extends AbstractCustomizableItem> T getFavouritedItem(Class<T> itemType){
        if (favouritedItems == null) return null;
        if (!favouritedItems.containsKey(itemType)) return null;

        String favouritedItemId = favouritedItems.get(itemType);
        return SkindexRegistry.getItemById(itemType, favouritedItemId);
    }

    public void setFavouritedItem(AbstractCustomizableItem item){
        setFavouritedItem(item.itemTypeClass, item.getId());
    }
    public void setFavouritedItem(Class<? extends AbstractCustomizableItem> itemType, String itemId){
        if (favouritedItems == null) favouritedItems = new HashMap<>();

        favouritedItems.put(itemType.getName(), itemId);
        save();
    }

    //endregion

    //region Favourited Skins

    public AbstractPlayerSkin getFavouritedSkin(AbstractPlayer.PlayerClass playerClass){
        return getFavouritedSkin(playerClass.name());
    }
    public AbstractPlayerSkin getFavouritedSkin(String playerClass){
        if (favouritedSkins == null) return null;
        if (!favouritedSkins.containsKey(playerClass)) return null;

        String favouritedSkinID = favouritedSkins.get(playerClass);

        return SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.valueOf(playerClass), favouritedSkinID);
    }

    public void setFavouritedSkin(AbstractPlayerSkin skin){
        setFavouritedSkin(skin.playerClass, skin.getId());
    }
    public void setFavouritedSkin(AbstractPlayer.PlayerClass playerClass, String skinID){
        setFavouritedSkin(playerClass.name(), skinID);
    }
    public void setFavouritedSkin(String playerClass, String skinID){
        if (favouritedSkins == null) favouritedSkins = new HashMap<>();

        favouritedSkins.put(playerClass, skinID);
        save();
    }

    //endregion

    //endregion

    public static JsonStorageFileRules<SkindexUserConfig> makeRules(){
        JsonStorageFileRules<SkindexUserConfig> rules = new JsonStorageFileRules<>();

        rules.saveSteamCloud = true;
        rules.saveLocal = true;

        rules.localRelativeDirPath = "skindex";
        rules.fileName = "userConfig";
        rules.extension = ".skindex";
        rules.perSave = true;

        rules.makeNew = SkindexUserConfig::new;

        return rules;
    }
}
