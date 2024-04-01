package skindex.modcompat.unchained.skins.card;

import com.megacrit.cardcrawl.cards.AbstractCard;
import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import theUnchainedMod.characters.TheUnchained;

public class UnchainedPixelCardSkin extends CardSkin {
    /** Constructor */
    public UnchainedPixelCardSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends CardSkinData{
        public static String ID = "UNCHAINED_PIXEL";
        public SkinData(){
            id = ID;
            name = "Pixel";

            attackBg = "skindexResources/images/skins/player/unchained/pixel/attackCardBg.png";
            skillBg = "skindexResources/images/skins/player/unchained/pixel/skillCardBg.png";
            powerBg = "skindexResources/images/skins/player/unchained/pixel/powerCardBg.png";

            cardColor = TheUnchained.Enums.UNCHAINED_COLOR.name();
        }
    }
}
