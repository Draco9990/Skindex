package skindex.skins.player.defect;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class DefectBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DefectBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "images/characters/defect/idle/skeleton.atlas";
            skeletonUrl = "images/characters/defect/idle/skeleton.json";
            resourceDirectoryUrl = "images/characters/defect/idle/";

            id = ID;
            name = "Base";

            icon = "skindexResources/images/skins/player/defect/base/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }
}
