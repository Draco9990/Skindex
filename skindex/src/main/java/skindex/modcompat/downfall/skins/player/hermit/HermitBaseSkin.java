package skindex.modcompat.downfall.skins.player.hermit;

import hermit.characters.hermit;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class HermitBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public HermitBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "hermitResources/images/char/hermit/Hermit.atlas";
            skeletonUrl = "hermitResources/images/char/hermit/Hermit.json";
            resourceDirectoryUrl = "hermitResources/images/char/hermit/";

            id = ID;
            name = "Base";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = hermit.Enums.HERMIT.name();
        }
    }
}
