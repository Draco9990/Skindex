package skindex.modcompat.skulHeroSlayer.skins.player;

import skindex.unlockmethods.FreeUnlockMethod;
import skulmod.character.LittleBone;

public class LittleBoneElMuerteSkin extends LittleBoneAtlasSkin {
    /** Constructors */
    public LittleBoneElMuerteSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends LittleBoneAtlasSkinData {
        public static String ID = "ELMUERTE";
        public SkinData(){
            atlasDirectoryUrl = "skulmod/character/animation/";
            skeletonDirectoryUrl = "skulmod/character/animation/";
            resourceDirectoryUrl = "skindexResources/images/skins/player/littlebone/elmuerte/";

            id = ID;
            name = "El Muerte";
            defaultAnimName = "IDLE";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = LittleBone.Enums.Little_Bone.name();
        }
    }
}
