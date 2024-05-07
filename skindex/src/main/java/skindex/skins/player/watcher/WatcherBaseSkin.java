package skindex.skins.player.watcher;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.unlockmethods.FreeUnlockMethod;

public class WatcherBaseSkin extends WatcherAtlasSkin {
    /** Constructors */
    public WatcherBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "images/characters/watcher/idle/skeleton.atlas";
            skeletonUrl = "images/characters/watcher/idle/skeleton.json";
            resourceDirectoryUrl = "images/characters/watcher/idle/";

            eyeAtlasUrl = "images/characters/watcher/eye_anim/skeleton.atlas";
            eyeSkeletonUrl = "images/characters/watcher/eye_anim/skeleton.json";
            eyeResourceDirectoryUrl = "images/characters/watcher/eye_anim/";

            id = ID;
            name = "Base";

            icon = "skindexResources/images/skins/player/watcher/base/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }
}
