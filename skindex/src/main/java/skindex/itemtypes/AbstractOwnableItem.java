package skindex.itemtypes;

import com.google.gson.annotations.SerializedName;
import skindex.SkindexDev;
import skindex.bundles.Bundle;
import skindex.registering.SkindexRegistry;
import skindex.trackers.SkindexUnlockTracker;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.AbstractUnlockMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractOwnableItem extends AbstractCustomizableItem {
    //region Variables

    public AbstractUnlockMethod unlockMethod;

    public ArrayList<Bundle> bundles;

    private String unlockDescription;

    //endregion

    //region Constructors
    public AbstractOwnableItem(OwnableItemData itemData){
        super(itemData);

        unlockDescription = itemData.unlockDescription;

        unlockMethod = SkindexRegistry.getUnlockMethodById(itemData.unlockMethod);
        bundles = new ArrayList<>();
    }
    //endregion

    //region Class Methods
    //region Unlock
    public boolean canUse(){
        if(SkindexDev.unlockAll) return true;

        if(unlockMethod.hasUnlocked(this)) return true;

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
    public AbstractOwnableItem setUnlockDescription(String unlockDescription){
        this.unlockDescription = unlockDescription;
        return this;
    }
    public String getUnlockDescription() {
        if(canUse()) return "Unlocked!";

        if(unlockDescription != null) return unlockDescription;

        String unlockDescription = "";
        for(Bundle b : bundles){
            unlockDescription += b.getItemUnlockDescription() + "\n";
        }

        if(unlockMethod != null){
            unlockDescription += unlockMethod.getItemUnlockDescription();
        }

        return unlockDescription;
    }
    //endregion

    //region Bundles
    public AbstractCustomizableItem setBundles(Bundle... keywords){
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
