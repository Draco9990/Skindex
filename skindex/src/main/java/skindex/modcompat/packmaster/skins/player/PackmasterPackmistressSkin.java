package skindex.modcompat.packmaster.skins.player;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;
import thePackmaster.ThePackmaster;

public class PackmasterPackmistressSkin extends PlayerAtlasSkin {
    /** Constructors */
    public PackmasterPackmistressSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "PACKMISTRESS";
        public SkinData(){
            atlasUrl = "anniv5Resources/images/char/mainChar/skins/packmistress/PackmasterAnim.atlas";
            skeletonUrl = "anniv5Resources/images/char/mainChar/skins/packmistress/PackmasterAnim.json";
            resourceDirectoryUrl = "anniv5Resources/images/char/mainChar/skins/packmistress/";

            shoulderIMG = "anniv5Resources/images/char/mainChar/skins/packmistress/shoulder.png";
            shoulder2IMG = "anniv5Resources/images/char/mainChar/skins/packmistress/shoulder2.png";
            corpseIMG = "anniv5Resources/images/char/mainChar/skins/packmistress/corpse.png";

            id = ID;
            name = "Packmistress";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = ThePackmaster.Enums.THE_PACKMASTER.name();
        }
    }
}
