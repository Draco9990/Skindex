package skindex;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skindex.registering.SkindexRegistry;
import skindex.skins.cards.CardSkin;
import skindex.skins.orb.OrbSkin;
import skindex.skins.entity.player.AbstractPlayerSkin;
import skindex.skins.stances.StanceSkin;

import java.util.Objects;

public class SkindexGame {
    @SpirePatch(clz = AbstractPlayer.class, method = "<class>")
    private static class FieldExtensions {
        public static SpireField<AbstractPlayerSkin> skin = new SpireField<>(() -> null);
    }

    public static <T extends AbstractPlayerSkin> T getPlayerSkinRaw(AbstractPlayer creature){
        return (T) FieldExtensions.skin.get(creature);
    }
    public static void setPlayerSkinRaw(AbstractPlayer creature, AbstractPlayerSkin skin){
        FieldExtensions.skin.set(creature, skin);
    }

    public static AbstractPlayerSkin getActivePlayerSkin(){
        if(!CardCrawlGame.isInARun() || AbstractDungeon.player == null) return null;

        return getActivePlayerSkin(AbstractDungeon.player);
    }
    public static AbstractPlayerSkin getActivePlayerSkin(AbstractPlayer player){
        if(player == null) return null;

        AbstractPlayerSkin current = getPlayerSkinRaw(player);
        if(current == null){
            current = SkindexRegistry.getDefaultPlayerSkinByClass(player.chosenClass, true);
            if(current != null){
                current.loadOnPlayer();
            }
        }
        return current;
    }

    public static CardSkin getActiveCardSkin(AbstractCard.CardColor cardColor, AbstractCard.CardType cardType){
        AbstractPlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin == null){
            return null;
        }

        for(String cardSkinId : currentSkin.cardSkins){
            CardSkin cardSkin = SkindexRegistry.getCardSkinById(cardSkinId, false);
            if(cardSkin == null) continue;

            if(Objects.equals(cardSkin.cardType, cardType) && Objects.equals(cardSkin.cardColor, cardColor)){
                return cardSkin;
            }
        }

        return null;
    }
    public static OrbSkin makeOrbSkinForOrb(String orbType){
        AbstractPlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin == null){
            return null;
        }

        return SkindexRegistry.getOrbSkinById(currentSkin.orbsSkinMap.get(orbType), true);
    }
    public static StanceSkin getActiveStanceSkin(String stanceId){
        AbstractPlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin == null){
            return null;
        }

        return currentSkin.stanceSkinMap.get(stanceId);
    }

    //region Stance Override

    private static AbstractPlayer stanceRenderOverridePlayer = null;

    public static AbstractPlayer getStanceRenderOverridePlayer(){
        return stanceRenderOverridePlayer;
    }

    public static void setStanceRenderOverridePlayer(AbstractPlayer creature){
        stanceRenderOverridePlayer = creature;
    }
    //endregion Stance Override
}
