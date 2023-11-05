package skindex.itemtypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    /** Variables */
    public String id;
    public String name;
    protected String resourceDirectory;

    private ArrayList<String> credits;

    /** Constructors */
    public CustomizableItem(CustomizableItemData itemData){
        this.id = itemData.id;
        this.name = itemData.name;

        credits = new ArrayList<>();
    }

    public CustomizableItem(String id, String name){
        this.id = id;
        this.name = name;

         credits = new ArrayList<>();
    }

    /** Getters and Setters */
    public String getId(){
        return id;
    }

    public CustomizableItem setName(String newName){
        this.name = newName;
        return this;
    }
    public String getName(){
        if(name == null) return id;
        else return name;
    }

    public CustomizableItem setCredits(String... credits){
        this.credits = new ArrayList<>(Arrays.asList(credits));
        return this;
    }
    public ArrayList<String> getCredits(){
        return credits;
    }

    /** Methods */
    public Integer getUUID(){
        return 10000 + new Random(id.hashCode()).nextInt(90000);
    }

    public Texture loadImageIfExists(String assetName){
        if(assetName == null) return null;

        String imageName = assetName.contains(".") ? assetName : assetName + ".png";
        return TextureManager.getTexture(imageName);
    }

    public TextureAtlas.AtlasRegion makeAtlasRegionFromTexture(Texture texture){
        if(texture == null) return null;

        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    /** System Methods */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CustomizableItem)) return false;

        CustomizableItem item = (CustomizableItem) obj;

        return item.getId().equals(((CustomizableItem) obj).getId());
    }

    /** System Methods */
    public CustomizableItem makeCopy(){
        try{
            return this.getClass().newInstance();
        }catch (Exception e){
            SkindexLogger.logError("Could not create an instance copy of " + id  + " since it doesn't have a no-param constructor. Contact the developer to fix pls thank u.", SkindexLogger.ErrorType.NON_FATAL);
        }

        return this;
    }
}
