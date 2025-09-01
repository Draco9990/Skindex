package skindex.skins.entity.player.defect;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class DefectYellowSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DefectYellowSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "YELLOW";
        public SkinData(){
            atlasUrl = "images/characters/defect/idle/skeleton.atlas";
            skeletonUrl = "images/characters/defect/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/yellow/";

            id = ID;
            name = "Yellow";

            icon = "skindexResources/images/skins/player/defect/yellow/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }
}
