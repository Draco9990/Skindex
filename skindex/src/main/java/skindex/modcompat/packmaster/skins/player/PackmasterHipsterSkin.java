package skindex.modcompat.packmaster.skins.player;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;
import thePackmaster.ThePackmaster;

public class PackmasterHipsterSkin extends PlayerAtlasSkin {
    /** Constructors */
    public PackmasterHipsterSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "HIPSTER";
        public SkinData(){
            atlasUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/PackmasterAnim.atlas";
            skeletonUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/PackmasterAnim.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/packmaster/hipster/";

            id = ID;
            name = "Hipster";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = ThePackmaster.Enums.THE_PACKMASTER.name();
        }
    }
}
