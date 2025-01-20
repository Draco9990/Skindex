package skindex.modcompat.skulHeroSlayer.skins.card;

import com.megacrit.cardcrawl.cards.AbstractCard;
import skindex.registering.SkindexRegistry;
import skindex.skins.cards.CardSkin;
import skindex.skins.cards.CardSkinData;
import skulmod.character.LittleBone;

import java.util.Arrays;
import java.util.List;

public class LittleBoneLittleHelperCardSkinSet{
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
        public static String ID = "LITTLE_BONE_LITTLEHELPER_ATTACK";
        public Attack(){
            id = ID;
            name = "Little Helper";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
            cardType = AbstractCard.CardType.ATTACK.name();

            cardBg = "skindexResources/images/skins/player/littlebone/littlehelper/attackCardBg.png";
        }
    }

    public static class Skill extends CardSkinData{
        public static String ID = "LITTLE_BONE_LITTLEHELPER_SKILL";
        public Skill(){
            id = ID;
            name = "Little Helper";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
            cardType = AbstractCard.CardType.SKILL.name();

            cardBg = "skindexResources/images/skins/player/littlebone/littlehelper/skillCardBg.png";
        }
    }

    public static class Power extends CardSkinData{
        public static String ID = "LITTLE_BONE_LITTLEHELPER_POWER";
        public Power(){
            id = ID;
            name = "Little Helper";

            cardColor = LittleBone.Enums.CARD_COLOR.name();
            cardType = AbstractCard.CardType.POWER.name();

            cardBg = "skindexResources/images/skins/player/littlebone/littlehelper/powerCardBg.png";
        }
    }
}
