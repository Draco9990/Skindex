package skindex.skins.player.watcher;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.unlockmethods.FreeUnlockMethod;

public class WatcherYellowSkin extends WatcherAtlasSkin {
    /** Constructors */
    public WatcherYellowSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "YELLOW";
        public SkinData(){
            atlasUrl = "images/characters/watcher/idle/skeleton.atlas";
            skeletonUrl = "images/characters/watcher/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/watcher/yellow/";

            eyeAtlasUrl = "images/characters/watcher/eye_anim/skeleton.atlas";
            eyeSkeletonUrl = "images/characters/watcher/eye_anim/skeleton.json";
            eyeResourceDirectoryUrl = "images/characters/watcher/eye_anim/";

            id = ID;
            name = "Yellow";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }
}
