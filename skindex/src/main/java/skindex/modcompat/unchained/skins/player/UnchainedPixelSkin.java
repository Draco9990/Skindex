package skindex.modcompat.unchained.skins.player;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerGifSkin;
import skindex.skins.player.PlayerGifSkinData;
import skindex.skins.player.PlayerImageSkin;
import skindex.skins.player.PlayerImageSkinData;
import skindex.unlockmethods.FreeUnlockMethod;
import theUnchainedMod.characters.TheUnchained;

public class UnchainedPixelSkin extends PlayerGifSkin {
    /** Constructors */
    public UnchainedPixelSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerGifSkinData {
        public static String ID = "PIXEL";
        public SkinData(){
            gifUrl = "skindexResources/images/skins/player/unchained/pixel/model.gif";

            id = ID;
            name = "Pixel";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheUnchained.Enums.THE_UNCHAINED.name();
        }
    }
}
