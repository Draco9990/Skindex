package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneElMuerteCardSkinSet;
import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

import java.util.ArrayList;

public class LittleBoneElMuerteSkin extends LittleBoneAtlasSkin {
    /** Constructors */
    public LittleBoneElMuerteSkin() {
        super(new SkinData());

        setCredits("Aurin");
    }

    /** Data */
    public static class SkinData extends LittleBoneAtlasSkinData {
        public static String ID = "ELMUERTE";
        public SkinData(){
            atlasDirectoryUrl = "skulmod/character/animation/";
            skeletonDirectoryUrl = "skulmod/character/animation/";
            resourceDirectoryUrl = "skindexResources/images/skins/player/littlebone/elmuerte/";

            cardSkins = new ArrayList<>(LittleBoneElMuerteCardSkinSet.collectCardSkinIds());

            id = ID;
            name = "El Muerte";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
