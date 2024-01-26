package skindex.modcompat.skins.player.theUnchained;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;
import theUnchainedMod.TheUnchainedMod;
import theUnchainedMod.characters.TheUnchained;

public class UnchainedBirthdaySkin extends PlayerAtlasSkin {
    /** Constructors */
    public UnchainedBirthdaySkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BIRTHDAY";
        public SkinData(){
            atlasUrl = "theUnchainedModResources/images/char/defaultCharacter/birthdaySkin/UnchainedBirthdayBoi.atlas";
            skeletonUrl = "theUnchainedModResources/images/char/defaultCharacter/birthdaySkin/UnchainedBirthdayBoi_Skeleton.json";
            resourceDirectoryUrl = "theUnchainedModResources/images/char/defaultCharacter/birthdaySkin/";

            id = ID;
            name = "Birthday";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheUnchained.Enums.THE_UNCHAINED.name();
        }
    }
}
