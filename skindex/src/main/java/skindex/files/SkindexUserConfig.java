package skindex.files;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import skindex.Skindex;
import skindex.bundles.Bundle;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerSkin;
import skindex.trackers.SkindexDefaultTracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SkindexUserConfig extends JsonDataFile implements Serializable {
    /** Singleton(ish) */
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

    /** Variables */
    public HashMap<String, String> favouritedSkins = new HashMap<>();

    /** Constructors */
    public SkindexUserConfig(int saveSlot) {
        super(FileLocations.userConfigFiles[saveSlot]);
    }
    public SkindexUserConfig(){
        this(CardCrawlGame.saveSlot);
    }

    /** Load */
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

    //region Favourited Items
    public PlayerSkin getFavouritedSkin(AbstractPlayer.PlayerClass playerClass){
        if (favouritedSkins == null) return null;
        if (!favouritedSkins.containsKey(playerClass.name())) return null;

        String favouritedSkinID = favouritedSkins.get(playerClass.name());

        return SkindexRegistry.getPlayerSkinByClassAndId(playerClass, favouritedSkinID);
    }

    public void setFavouritedSkin(AbstractPlayer.PlayerClass playerClass, PlayerSkin skin){
        if (favouritedSkins == null) favouritedSkins = new HashMap<>();

        if(skin == null){
            return;
        }

        favouritedSkins.put(playerClass.name(), skin.getId());
        save();
    }
    //endregion
}
