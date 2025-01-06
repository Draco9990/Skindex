package skindex.itemtypes;

import com.google.gson.annotations.SerializedName;
import skindex.SkindexDev;
import skindex.bundles.Bundle;
import skindex.registering.SkindexRegistry;
import skindex.trackers.SkindexUnlockTracker;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class OwnableItem<ItemType extends OwnableItem> extends CustomizableItem{
    //region Variables
    public Class<ItemType> itemTypeClass;

    public UnlockMethod unlockMethod;

    public ArrayList<Bundle> bundles;

    private String unlockDescription;
    //endregion

    //region Constructors
    public OwnableItem(OwnableItemData itemData){
        super(itemData);

        itemTypeClass = (Class<ItemType>) this.getClass().getGenericSuperclass();

        unlockDescription = itemData.unlockDescription;

        unlockMethod = SkindexRegistry.getUnlockMethodById(itemData.unlockMethod);
        bundles = new ArrayList<>();
    }
    //endregion

    //region Class Methods
    //region Unlock
    public boolean canUse(){
        if(SkindexDev.unlockAll) return true;

        if(Objects.equals(unlockMethod.id, FreeUnlockMethod.methodId)) return true;

        return hasUnlocked();
    }

    public boolean unlock(){
        return SkindexUnlockTracker.get().unlockItem(this);
    }
    public boolean hasUnlocked(){
        if(SkindexUnlockTracker.get().hasItem(this)){
            return true;
        }

        for(Bundle b : bundles){
            if(SkindexUnlockTracker.get().hasBundle(b)){
                return true;
            }
        }

        return false;
    }
    //endregion

    //region Unlock Description
    public OwnableItem setUnlockDescription(String unlockDescription){
        this.unlockDescription = unlockDescription;
        return this;
    }
    public String getUnlockDescription() {
        if(canUse()) return "Unlocked!";

        if(unlockDescription != null) return unlockDescription;

        if(!bundles.isEmpty()){
            return bundles.get(0).getItemUnlockDescription();
        }

        if(unlockMethod != null) return unlockMethod.getItemUnlockDescription();

        return "";
    }
    //endregion

    //region Bundles
    public CustomizableItem setBundles(Bundle... keywords){
        this.bundles = new ArrayList<>(Arrays.asList(keywords));
        return this;
    }
    public boolean hasBundle(String bundleId){
        return bundles.contains(SkindexRegistry.getBundleById(bundleId));
    }
    public boolean hasBundle(Bundle bundle){
        return bundles.contains(bundle);
    }
    //endregion

    //endregion

    public static class OwnableItemData extends CustomizableItemData implements Serializable {
        static final long serialVersionUID = 1L;

        @SerializedName("unlockDescription")
        public String unlockDescription;

        @SerializedName("unlockMethod")
        public String unlockMethod = FreeUnlockMethod.methodId;
    }
}
