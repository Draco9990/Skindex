package skindex.itemtypes;

import skindex.SkindexDev;
import skindex.bundles.Bundle;
import skindex.registering.SkindexRegistry;
import skindex.trackers.SkindexDefaultTracker;
import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.UnlockMethod;
import skindex.util.SkindexLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class OwnableItem extends CustomizableItem{
    //region Variables
    public UnlockMethod unlockMethod;

    public ArrayList<Bundle> bundles;

    private String unlockDescription;

    protected String trackerId;
    //endregion

    //region Constructors
    public OwnableItem(OwnableItemData itemData){
        super(itemData);

        unlockDescription = itemData.unlockDescription;

        unlockMethod = SkindexRegistry.getUnlockMethodById(itemData.unlockMethod);
        bundles = new ArrayList<>();

        trackerId = itemData.tracker;
    }
    //endregion

    //region Class Methods
    //region Unlock
    public boolean canUse(){
        if(SkindexDev.unlockAll) return true;

        if(Objects.equals(unlockMethod.id, FreeUnlockMethod.methodId)) return true;

        return hasUnlocked();
    }

    public abstract boolean unlock();
    public boolean hasUnlocked(){
        if(getTracker() != null){
            for(Bundle b : bundles){
                if(getTracker().hasBundle(b)){
                    return true;
                }
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

    //region Tracker
    public SkindexTracker getTracker(){
        if(trackerId == null) return SkindexRegistry.getTrackerById(SkindexDefaultTracker.get().getId());

        return SkindexRegistry.getTrackerById(trackerId);
    }
    //endregion

    //endregion
}
