package skindex.modcompat.packmaster.skins.player;

import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;
import thePackmaster.ThePackmaster;

public class PackmasterBuilderSkin extends PlayerAtlasSkin {
    /** Constructors */
    public PackmasterBuilderSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BUILDER";
        public SkinData(){
            atlasUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/PackmasterAnim.atlas";
            skeletonUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/PackmasterAnim.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/packmaster/builder/";

            id = ID;
            name = "Buildmaster";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = ThePackmaster.Enums.THE_PACKMASTER.name();
        }
    }
}
