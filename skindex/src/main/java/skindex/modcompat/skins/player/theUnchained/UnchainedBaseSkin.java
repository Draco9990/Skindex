package skindex.modcompat.skins.player.theUnchained;

import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;
import theUnchainedMod.characters.TheUnchained;

public class UnchainedBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public UnchainedBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "theUnchainedModResources/images/char/defaultCharacter/idle/skeleton.atlas";
            skeletonUrl = "theUnchainedModResources/images/char/defaultCharacter/idle/skeleton_Skeleton.json";
            resourceDirectoryUrl = "theUnchainedModResources/images/char/defaultCharacter/idle/";

            id = ID;
            name = "Base";

            icon = "skindexResources/images/skins/player/unchained/base/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheUnchained.Enums.THE_UNCHAINED.name();

            isDefault = true;
        }
    }
}
