package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneLittleHelperCardSkinSet;
import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

import java.util.ArrayList;

public class LittleBoneLittleHelperSkin extends LittleBoneAtlasSkin {
    /** Constructors */
    public LittleBoneLittleHelperSkin() {
        super(new SkinData());

        setCredits("Aurin");
    }

    /** Data */
    public static class SkinData extends LittleBoneAtlasSkinData {
        public static String ID = "LITTLEHELPER";
        public SkinData(){
            atlasDirectoryUrl = "skulmod/character/animation/";
            skeletonDirectoryUrl = "skulmod/character/animation/";
            resourceDirectoryUrl = "skindexResources/images/skins/player/littlebone/littlehelper/";

            cardSkins = new ArrayList<>(LittleBoneLittleHelperCardSkinSet.collectCardSkinIds());

            id = ID;
            name = "Little Helper";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
