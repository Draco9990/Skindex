package skindex.modcompat.skins.player.skulHeroSlayer;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

public class LittleBoneBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public LittleBoneBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "skulmod/character/animation/LittleBoneAnim.atlas";
            skeletonUrl = "skulmod/character/animation/LittleBoneAnim.json";
            resourceDirectoryUrl = "skulmod/character/animation/";

            id = ID;
            name = "Base";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
