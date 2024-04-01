package skindex.modcompat.skulHeroSlayer.skins.card;

import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import skulmod.character.LittleBone;

public class LittleBoneRoyalGuardCardSkin extends CardSkin {
    /** Constructor */
    public LittleBoneRoyalGuardCardSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends CardSkinData{
        public static String ID = "LITTLE_BONE_ROYALGUARD";
        public SkinData(){
            id = ID;
            name = "Royal Guard";

            attackBg = "skindexResources/images/skins/player/littlebone/royalguard/attackCardBg.png";
            skillBg = "skindexResources/images/skins/player/littlebone/royalguard/skillCardBg.png";
            powerBg = "skindexResources/images/skins/player/littlebone/royalguard/powerCardBg.png";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
        }
    }
}
