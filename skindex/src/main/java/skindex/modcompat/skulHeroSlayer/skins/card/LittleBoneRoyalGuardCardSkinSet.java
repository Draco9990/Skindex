package skindex.modcompat.skulHeroSlayer.skins.card;

import com.megacrit.cardcrawl.cards.AbstractCard;
import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import skulmod.character.LittleBone;

import java.util.Arrays;
import java.util.List;

public class LittleBoneRoyalGuardCardSkinSet {
    public static List<CardSkin> collectCardSkins(){
        return Arrays.asList(
                new CardSkin(new Attack()),
                new CardSkin(new Skill()),
                new CardSkin(new Power())
        );
    }

    public static List<String> collectCardSkinIds(){
        return Arrays.asList(
                Attack.ID,
                Skill.ID,
                Power.ID
        );
    }

    public static class Attack extends CardSkinData {
        public static String ID = "LITTLE_BONE_ROYALGUARD_ATTACK";
        public Attack(){
            id = ID;
            name = "Royal Guard";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
            cardType = AbstractCard.CardType.ATTACK.name();

            cardBg = "skindexResources/images/skins/player/littlebone/royalguard/attackCardBg.png";
        }
    }

    public static class Skill extends CardSkinData{
        public static String ID = "LITTLE_BONE_ROYALGUARD_SKILL";
        public Skill(){
            id = ID;
            name = "Royal Guard";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
            cardType = AbstractCard.CardType.SKILL.name();

            cardBg = "skindexResources/images/skins/player/littlebone/royalguard/skillCardBg.png";
        }
    }

    public static class Power extends CardSkinData{
        public static String ID = "LITTLE_BONE_ROYALGUARD_POWER";
        public Power(){
            id = ID;
            name = "Royal Guard";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
            cardType = AbstractCard.CardType.POWER.name();

            cardBg = "skindexResources/images/skins/player/littlebone/royalguard/powerCardBg.png";
        }
    }
}
