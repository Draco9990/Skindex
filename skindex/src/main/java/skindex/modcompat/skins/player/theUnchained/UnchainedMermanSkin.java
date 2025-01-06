package skindex.modcompat.skins.player.theUnchained;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;
import theUnchainedMod.characters.TheUnchained;

public class UnchainedMermanSkin extends PlayerAtlasSkin {
    /** Constructors */
    public UnchainedMermanSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "MERMAN";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/unchained/merman/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/unchained/merman/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/unchained/merman/";

            id = ID;
            name = "Merman";

            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = TheUnchained.Enums.THE_UNCHAINED.name();
        }
    }
}
