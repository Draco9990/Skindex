package skindex.files;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.AbstractPlayerSkin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SkindexUserConfig extends JsonDataFile implements Serializable {
    //region Singleton(ish)

    public static SkindexUserConfig[] instances = new SkindexUserConfig[3];
    public static SkindexUserConfig get(int saveSlot){
        if(instances[saveSlot] == null){
            instances[saveSlot] = load(saveSlot);
        }
        return instances[saveSlot];
    }
    public static SkindexUserConfig get(){
        return get(CardCrawlGame.saveSlot);
    }

    //endregion

    //region Variables

    public HashMap<String, String> favouritedItems = new HashMap<>();

    public HashMap<String, String> favouritedSkins = new HashMap<>();

    //endregion

    //region Constructors

    public SkindexUserConfig(int saveSlot) {
        super(FileLocations.userConfigFiles[saveSlot]);
    }
    public SkindexUserConfig(){
        this(CardCrawlGame.saveSlot);
    }

    //endregion

    //region Class Methods

    //region Save/Load

    public static SkindexUserConfig load(int saveSlot){
        SkindexUserConfig tracker = (SkindexUserConfig) load(FileLocations.userConfigFiles[saveSlot], SkindexUserConfig.class);
        if(tracker == null){
            tracker = new SkindexUserConfig(saveSlot);
        }

        return tracker;
    }
    public static SkindexUserConfig load(){
        return load(CardCrawlGame.saveSlot);
    }

    //endregion

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
}
