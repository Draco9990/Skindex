package skindex.skins.player.watcher;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.unlockmethods.FreeUnlockMethod;

public class WatcherRedSkin extends WatcherAtlasSkin {
    /** Constructors */
    public WatcherRedSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "RED";
        public SkinData(){
            atlasUrl = "images/characters/watcher/idle/skeleton.atlas";
            skeletonUrl = "images/characters/watcher/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/watcher/red/";

            eyeAtlasUrl = "images/characters/watcher/eye_anim/skeleton.atlas";
            eyeSkeletonUrl = "images/characters/watcher/eye_anim/skeleton.json";
            eyeResourceDirectoryUrl = "images/characters/watcher/eye_anim/";

            id = ID;
            name = "Red";

            icon = "skindexResources/images/skins/player/watcher/red/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }
}
