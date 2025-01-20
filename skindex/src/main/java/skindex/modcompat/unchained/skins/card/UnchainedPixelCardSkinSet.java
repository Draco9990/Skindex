package skindex.modcompat.unchained.skins.card;

import com.megacrit.cardcrawl.cards.AbstractCard;
import skindex.registering.SkindexRegistry;
import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import theUnchainedMod.characters.TheUnchained;

import java.util.Arrays;
import java.util.List;

public class UnchainedPixelCardSkinSet {
    public static void registerCardSkins(){
        SkindexRegistry.registerItem(new CardSkin(new Attack()));
        SkindexRegistry.registerItem(new CardSkin(new Skill()));
        SkindexRegistry.registerItem(new CardSkin(new Power()));
    }

    public static List<String> collectCardSkinIds(){
        return Arrays.asList(
                Attack.ID,
                Skill.ID,
                Power.ID
        );
    }

    public static class Attack extends CardSkinData {
        public static String ID = "UNCHAINED_PIXEL_ATTACK";
        public Attack(){
            id = ID;
            name = "Pixel";

            cardColor = TheUnchained.Enums.UNCHAINED_COLOR.name();
            cardType = AbstractCard.CardType.ATTACK.name();

            cardBg = "skindexResources/images/skins/player/unchained/pixel/attackCardBg.png";
        }
    }

    public static class Skill extends CardSkinData{
        public static String ID = "UNCHAINED_PIXEL_SKILL";
        public Skill(){
            id = ID;
            name = "Pixel";

            cardColor = TheUnchained.Enums.UNCHAINED_COLOR.name();
            cardType = AbstractCard.CardType.SKILL.name();

            cardBg = "skindexResources/images/skins/player/unchained/pixel/skillCardBg.png";
        }
    }

    public static class Power extends CardSkinData{
        public static String ID = "UNCHAINED_PIXEL_POWER";
        public Power(){
            id = ID;
            name = "Pixel";

            cardColor = TheUnchained.Enums.UNCHAINED_COLOR.name();
            cardType = AbstractCard.CardType.POWER.name();

            cardBg = "skindexResources/images/skins/player/unchained/pixel/powerCardBg.png";
        }
    }
}
