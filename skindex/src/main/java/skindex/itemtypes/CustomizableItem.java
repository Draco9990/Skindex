package skindex.itemtypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import dLib.util.TextureManager;
import skindex.SkindexDev;
import skindex.registering.SkindexRegistry;
import skindex.bundles.Bundle;
import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.UnlockMethod;
import skindex.util.SkindexLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public abstract class CustomizableItem {
    //region Variables
    protected String id;
    protected String name;

    protected String previewIconPath;

    protected ArrayList<String> credits;

    private CustomizableItemData dataInitializer;
    //endregion Variables

    //region Constructors
    public CustomizableItem(CustomizableItemData itemData){
        this.id = itemData.id;
        this.name = itemData.name;

        this.previewIconPath = itemData.icon;

        credits = itemData.credits;

        dataInitializer = itemData;
    }
    //endregion Constructors

    //region Class Methods
    //region Id
    public String getId(){
        return id;
    }
    //endregion

    //region Name
    public CustomizableItem setName(String newName){
        this.name = newName;
        return this;
    }
    public String getName(){
        if(name == null) return id;
        else return name;
    }
    //endregion

    //region Preview Icon
    public Texture makePreviewIcon(){
        return ImageMaster.loadImage(previewIconPath);
    }
    //endregion

    //region Credits
    public CustomizableItem setCredits(String... credits){
        this.credits = new ArrayList<>(Arrays.asList(credits));
        return this;
    }
    public ArrayList<String> getCredits(){
        return credits;
    }
    //endregion

    //region UUID
    public Integer getUUID(){
        return 10000000 + new Random(id.hashCode()).nextInt(90000000);
    }
    //endregion

    //region Utility
    public Texture loadImageIfExists(String assetName){
        if(assetName == null) return null;

        String imageName = assetName.contains(".") ? assetName : assetName + ".png";
        return TextureManager.getTexture(imageName);
    }

    public TextureAtlas.AtlasRegion makeAtlasRegionFromTexture(Texture texture){
        if(texture == null) return null;

        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
    //endregion

    //region Misc
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CustomizableItem)) return false;

        CustomizableItem item = (CustomizableItem) obj;

        return item.getId().equals(((CustomizableItem) obj).getId());
    }

    public CustomizableItem makeCopy(){
        try{
            return this.getClass().getConstructor(CustomizableItemData.class).newInstance(dataInitializer);
        }catch (Exception e){
            SkindexLogger.logError("Could not create an instance copy of " + id  + " since it doesn't have a data initializer constructor. Contact the developer to fix pls thank u.", SkindexLogger.ErrorType.NON_FATAL);
        }

        return this;
    }
    //endregion

    public void dispose(){}
    //endregion Class Methods
}
