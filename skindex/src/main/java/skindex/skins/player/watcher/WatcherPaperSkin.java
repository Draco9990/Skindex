package skindex.skins.player.watcher;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerImageSkin;
import skindex.skins.player.PlayerImageSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class WatcherPaperSkin extends PlayerImageSkin {
    /** Constructors */
    public WatcherPaperSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerImageSkinData {
        public static String ID = "PAPER";
        public SkinData(){
            imageUrl = "skindexResources/images/skins/player/watcher/paper/model.png";

            id = ID;
            name = "Paper-thin";

            icon = "skindexResources/images/skins/player/watcher/paper/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }
}
