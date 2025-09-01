package skindex.skins.entity.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.entity.player.PlayerImageSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class IroncladPaperSkin extends PlayerImageSkin {
    /** Constructors */
    public IroncladPaperSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerImageSkinData {
        public static String ID = "PAPER";
        public SkinData(){
            imageUrl = "skindexResources/images/skins/player/ironclad/paper/model.png";

            id = ID;
            name = "Paper-thin";

            icon = "skindexResources/images/skins/player/ironclad/paper/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
