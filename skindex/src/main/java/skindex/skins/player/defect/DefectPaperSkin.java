package skindex.skins.player.defect;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerImageSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class DefectPaperSkin extends PlayerImageSkin {
    /** Constructors */
    public DefectPaperSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerImageSkinData {
        public static String ID = "PAPER";
        public SkinData(){
            imageUrl = "skindexResources/images/skins/player/defect/paper/model.png";

            id = ID;
            name = "Paper-thin";

            icon = "skindexResources/images/skins/player/defect/paper/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }
}
