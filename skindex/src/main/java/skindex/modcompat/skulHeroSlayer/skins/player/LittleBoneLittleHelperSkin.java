package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

public class LittleBoneLittleHelperSkin extends LittleBoneAtlasSkin {
    /** Constructors */
    public LittleBoneLittleHelperSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends LittleBoneAtlasSkinData {
        public static String ID = "LITTLEHELPER";
        public SkinData(){
            atlasDirectoryUrl = "skulmod/character/animation/";
            skeletonDirectoryUrl = "skulmod/character/animation/";
            resourceDirectoryUrl = "skindexResources/images/skins/player/littlebone/littlehelper/";

            id = ID;
            name = "Little Helper";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
