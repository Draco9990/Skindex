package skindex.skins.player.silent;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerImageSkin;
import skindex.skins.player.PlayerImageSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class SilentPaperSkin extends PlayerImageSkin {
    /** Constructors */
    public SilentPaperSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerImageSkinData {
        public static String ID = "PAPER";
        public SkinData(){
            imageUrl = "skindexResources/images/skins/player/silent/paper/model.png";

            id = ID;
            name = "Paper-thin";

            icon = "skindexResources/images/skins/player/silent/paper/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }
}
