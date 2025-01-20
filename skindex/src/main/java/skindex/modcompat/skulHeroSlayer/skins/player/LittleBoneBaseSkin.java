package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

public class LittleBoneBaseSkin extends LittleBoneAtlasSkin {
    /** Constructors */
    public LittleBoneBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends LittleBoneAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasDirectoryUrl = "skulmod/character/animation/";
            skeletonDirectoryUrl = "skulmod/character/animation/";
            resourceDirectoryUrl = "skulmod/character/animation/";

            id = ID;
            name = "Base";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();

            isDefault = true;
        }
    }
}
