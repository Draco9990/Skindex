package skindex.modcompat.skulHeroSlayer.skins.card;

import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import skulmod.character.LittleBone;

public class LittleBoneLittleHelperCardSkin extends CardSkin {
    /** Constructor */
    public LittleBoneLittleHelperCardSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends CardSkinData{
        public static String ID = "LITTLE_BONE_LITTLEHELPER";
        public SkinData(){
            id = ID;
            name = "Little Helper";

            attackBg = "skindexResources/images/skins/player/littlebone/littlehelper/attackCardBg.png";
            skillBg = "skindexResources/images/skins/player/littlebone/littlehelper/skillCardBg.png";
            powerBg = "skindexResources/images/skins/player/littlebone/littlehelper/powerCardBg.png";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
        }
    }
}
