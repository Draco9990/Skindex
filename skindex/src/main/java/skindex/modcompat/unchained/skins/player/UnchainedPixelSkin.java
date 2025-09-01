package skindex.modcompat.unchained.skins.player;

import skindex.modcompat.unchained.skins.card.UnchainedPixelCardSkinSet;
import skindex.skins.entity.player.PlayerGifSkin;
import skindex.skins.entity.player.PlayerGifSkinData;
import skindex.unlockmethods.FreeUnlockMethod;
import theUnchainedMod.characters.TheUnchained;

import java.util.ArrayList;

public class UnchainedPixelSkin extends PlayerGifSkin {
    /** Constructors */
    public UnchainedPixelSkin() {
        super(new SkinData());
        setCredits("Mezix");
    }

    /** Data */
    public static class SkinData extends PlayerGifSkinData {
        public static String ID = "PIXEL";
        public SkinData(){
            gifUrl = "skindexResources/images/skins/player/unchained/pixel/model.gif";

            cardSkins = new ArrayList<>(UnchainedPixelCardSkinSet.collectCardSkinIds());

            id = ID;
            name = "Pixel";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheUnchained.Enums.THE_UNCHAINED.name();
        }
    }
}
