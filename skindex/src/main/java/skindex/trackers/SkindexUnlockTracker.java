package skindex.trackers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.files.JsonDataFile;
import skindex.Skindex;
import skindex.bundles.Bundle;
import skindex.files.FileLocations;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;
import java.util.HashMap;

public class SkindexUnlockTracker extends JsonDataFile implements SkindexTracker{
    /** Singleton(ish) */
    public static SkindexUnlockTracker[] instances = new SkindexUnlockTracker[3];
    public static SkindexUnlockTracker get(int saveSlot){
        if(instances[saveSlot] == null){
            instances[saveSlot] = load(saveSlot);
        }
        return instances[saveSlot];
    }
    public static SkindexUnlockTracker get(){
        return get(CardCrawlGame.saveSlot);
    }

    private transient boolean undefined = false;

    /** Variables */
    public ArrayList<String> unlockedBundles = new ArrayList<>();
    public HashMap<String, ArrayList<String>> unlockedPlayerSkins = new HashMap<>();

    /** Constructors */
    public SkindexUnlockTracker(int saveSlot) {
        super(FileLocations.trackerFiles[saveSlot]);
    }
    public SkindexUnlockTracker(){
        this(CardCrawlGame.saveSlot);
    }

    /** Load */
    public static SkindexUnlockTracker load(int saveSlot){
        SkindexUnlockTracker tracker = (SkindexUnlockTracker) load(FileLocations.trackerFiles[saveSlot], SkindexUnlockTracker.class);
        if(tracker == null){
            tracker = new SkindexUnlockTracker(saveSlot);
        }

        return tracker;
    }
    public static SkindexUnlockTracker load(){
        return load(CardCrawlGame.saveSlot);
    }

    /** Skins */
    @Override
    public boolean hasSkin(PlayerSkin s) {
        if(s == null) return false;
        if(!unlockedPlayerSkins.containsKey(s.playerClass.name())) return false;

        return unlockedPlayerSkins.get(s.playerClass.name()).contains(s.getId());
    }
    @Override
    public boolean unlockSkin(PlayerSkin s) {
        if(s == null) return false;
        if(hasSkin(s)) return false;

        if(!unlockedPlayerSkins.containsKey(s.playerClass.name())){
            unlockedPlayerSkins.put(s.playerClass.name(), new ArrayList<>());
        }

        unlockedPlayerSkins.get(s.playerClass.name()).add(s.getId());
        save();
        return true;
    }

    /** Bundles */
    @Override
    public boolean hasBundle(Bundle b) {
        if(b == null) return false;

        return unlockedBundles.contains(b.id);
    }
    @Override
    public boolean unlockBundle(Bundle b) {
        if(b == null) return false;
        if(hasBundle(b)) return false;

        unlockedBundles.add(b.id);
        save();
        return true;
    }

    /** Misc */
    @Override
    public String getId() {
        return Skindex.getModID();
    }
}
