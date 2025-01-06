package skindex.modcompat.skins.player.theUnchained;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;
import theUnchainedMod.TheUnchainedMod;
import theUnchainedMod.characters.TheUnchained;

public class UnchainedPrinceUnboundSkin extends PlayerAtlasSkin {
    /** Constructors */
    public UnchainedPrinceUnboundSkin() {
        super(new SkinData());
    }

    @Override
    public boolean hasUnlocked() {
        return TheUnchainedMod.PrinceUnboundUnlocked();
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "PRINCEUNBOUND";
        public SkinData(){
            atlasUrl = "theUnchainedModResources/images/char/defaultCharacter/princeUnbound/princeUnboundSkeleton.atlas";
            skeletonUrl = "theUnchainedModResources/images/char/defaultCharacter/princeUnbound/princeUnboundSkeleton_Skeleton.json";
            resourceDirectoryUrl = "theUnchainedModResources/images/char/defaultCharacter/princeUnbound/";

            id = ID;
            name = "Prince Unbound";

            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = TheUnchained.Enums.THE_UNCHAINED.name();
        }
    }
}
