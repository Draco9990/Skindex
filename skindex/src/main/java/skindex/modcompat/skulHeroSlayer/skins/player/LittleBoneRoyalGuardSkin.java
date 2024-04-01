package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneRoyalGuardCardSkin;
import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

public class LittleBoneRoyalGuardSkin extends LittleBoneAtlasSkin {
    /** Constructors */
    public LittleBoneRoyalGuardSkin() {
        super(new SkinData());

        setCredits("Aurin");
    }

    /** Data */
    public static class SkinData extends LittleBoneAtlasSkinData {
        public static String ID = "ROYALGUARD";
        public SkinData(){
            atlasDirectoryUrl = "skulmod/character/animation/";
            skeletonDirectoryUrl = "skulmod/character/animation/";
            resourceDirectoryUrl = "skindexResources/images/skins/player/littlebone/royalguard/";

            cardSkins.add(LittleBoneRoyalGuardCardSkin.SkinData.ID);

            id = ID;
            name = "Royal Guard";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
