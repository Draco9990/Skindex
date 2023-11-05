package skindex.skins.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.skins.player.PlayerImageSkin;
import skindex.skins.player.PlayerImageSkinData;
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

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
