package skindex.itemtypes;

import skindex.SkindexDev;
import skindex.bundles.Bundle;
import skindex.registering.SkindexRegistry;
import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.UnlockMethod;
import skindex.util.SkindexLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class OwnableItem extends CustomizableItem{
    /** Variables */
    public UnlockMethod unlockMethod;

    public ArrayList<Bundle> bundles;

    private String unlockDescription;

    protected String trackerId;

    /** Constructors */
    public OwnableItem(OwnableItemData itemData){
        super(itemData);

        unlockMethod = SkindexRegistry.getUnlockMethodById(itemData.unlockMethod);
        bundles = new ArrayList<>();

        trackerId = itemData.tracker;
    }

    /** Getters and Setters */
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

    public SkindexTracker getTracker(){
        if(trackerId == null) return null;

        return SkindexRegistry.getTrackerById(trackerId);
    }

    /** Methods */
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
}
