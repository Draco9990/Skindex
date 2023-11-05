package skindex.trackers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import dLib.files.JsonDataFile;
import skindex.Skindex;
import skindex.bundles.Bundle;
import skindex.files.FileLocations;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;
import java.util.HashMap;

public class SkindexDefaultTracker extends JsonDataFile implements SkindexTracker{
    /** Singleton(ish) */
    public static SkindexDefaultTracker[] instances = new SkindexDefaultTracker[3];
    public static SkindexDefaultTracker get(int saveSlot){
        if(instances[saveSlot] == null){
            instances[saveSlot] = load(saveSlot);
        }
        return instances[saveSlot];
    }
    public static SkindexDefaultTracker get(){
        return get(CardCrawlGame.saveSlot);
    }

    private transient boolean undefined = false;

    /** Variables */
    public ArrayList<String> unlockedBundles = new ArrayList<>();
    public HashMap<String, ArrayList<String>> unlockedPlayerSkins = new HashMap<>();

    /** Constructors */
    public SkindexDefaultTracker(int saveSlot) {
        super(FileLocations.trackerFiles[saveSlot]);
    }
    public SkindexDefaultTracker(){
        this(CardCrawlGame.saveSlot);
    }

    /** Load */
    public static SkindexDefaultTracker load(int saveSlot){
        SkindexDefaultTracker tracker = (SkindexDefaultTracker) load(FileLocations.trackerFiles[saveSlot], SkindexDefaultTracker.class);
        if(tracker == null){
            tracker = new SkindexDefaultTracker(saveSlot);
        }

        return tracker;
    }
    public static SkindexDefaultTracker load(){
        return load(CardCrawlGame.saveSlot);
    }

    /** Skins */
    @Override
    public boolean hasSkin(PlayerSkin s) {
        if(s == null) return false;
        if(!unlockedPlayerSkins.containsKey(s.playerClass.name())) return false;

        return unlockedPlayerSkins.get(s.playerClass.name()).contains(s.id);
    }
    @Override
    public boolean unlockSkin(PlayerSkin s) {
        if(s == null) return false;
        if(hasSkin(s)) return false;

        if(!unlockedPlayerSkins.containsKey(s.playerClass.name())){
            unlockedPlayerSkins.put(s.playerClass.name(), new ArrayList<>());
        }

        unlockedPlayerSkins.get(s.playerClass.name()).add(s.id);
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
