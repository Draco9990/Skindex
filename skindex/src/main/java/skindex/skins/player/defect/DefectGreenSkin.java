package skindex.skins.player.defect;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class DefectGreenSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DefectGreenSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "GREEN";
        public SkinData(){
            atlasUrl = "images/characters/defect/idle/skeleton.atlas";
            skeletonUrl = "images/characters/defect/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/green/";

            id = ID;
            name = "Green";

            icon = "skindexResources/images/skins/player/defect/green/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }
}
