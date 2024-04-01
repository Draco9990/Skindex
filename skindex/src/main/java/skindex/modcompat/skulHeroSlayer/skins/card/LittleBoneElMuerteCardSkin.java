package skindex.modcompat.skulHeroSlayer.skins.card;

import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import skulmod.character.LittleBone;

public class LittleBoneElMuerteCardSkin extends CardSkin {
    /** Constructor */
    public LittleBoneElMuerteCardSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends CardSkinData{
        public static String ID = "LITTLE_BONE_ELMUERTE";
        public SkinData(){
            id = ID;
            name = "El Muerte";

            attackBg = "skindexResources/images/skins/player/littlebone/elmuerte/attackCardBg.png";
            skillBg = "skindexResources/images/skins/player/littlebone/elmuerte/skillCardBg.png";
            powerBg = "skindexResources/images/skins/player/littlebone/elmuerte/powerCardBg.png";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
        }
    }
}
