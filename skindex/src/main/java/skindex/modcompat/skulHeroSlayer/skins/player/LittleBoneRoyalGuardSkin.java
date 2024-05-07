package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneRoyalGuardCardSkinSet;
import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

import java.util.ArrayList;

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

            cardSkins = new ArrayList<>(LittleBoneRoyalGuardCardSkinSet.collectCardSkinIds());

            id = ID;
            name = "Royal Guard";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
