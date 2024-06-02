package skindex;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skindex.registering.SkindexRegistry;
import skindex.skins.cards.CardSkin;
import skindex.skins.orb.OrbSkin;
import skindex.skins.player.PlayerSkin;
import skindex.skins.stances.StanceSkin;

import java.util.Objects;

public class SkindexGame {
    private static PlayerSkin queuedSkin = null;

    /** Field Patches */
    public static class FieldPatches{
        /** Field Patches */
        @SpirePatch(clz = AbstractPlayer.class, method = "<class>")
        public static class Player {
            public static SpireField<PlayerSkin> skin = new SpireField<>(() -> null);
        }
    }

    /** Getters and Setters */
    public static void queuePlayerSkin(PlayerSkin skin){
        queuedSkin = skin;
    }
    public static PlayerSkin getQueuedPlayerSkin(){
        return queuedSkin;
    }
    public static boolean hasQueuedPlayerSkin(){
        return queuedSkin != null;
    }
    public static void clearQueuedPlayerSkin(){
        queuedSkin = null;
    }

    public static void setActivePlayerSkin(PlayerSkin skin){
        if(!CardCrawlGame.isInARun()) return;

        FieldPatches.Player.skin.set(AbstractDungeon.player, skin);
    }
    public static PlayerSkin getActivePlayerSkin(){
        if(!CardCrawlGame.isInARun()) return null;

        PlayerSkin current = FieldPatches.Player.skin.get(AbstractDungeon.player);
        if(current == null){
            current = SkindexRegistry.getDefaultPlayerSkinByClass(AbstractDungeon.player.chosenClass, true);
            if(current != null){
                current.loadOnPlayer();
            }
        }
        return current;
    }

    public static CardSkin getActiveCardSkin(AbstractCard.CardColor cardColor, AbstractCard.CardType cardType){
        PlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin == null){
            return null;
        }

        for(String cardSkinId : currentSkin.cardSkins){
            CardSkin cardSkin = SkindexRegistry.getCardSkin(cardSkinId);
            if(cardSkin == null) continue;

            if(Objects.equals(cardSkin.cardType, cardType) && Objects.equals(cardSkin.cardColor, cardColor)){
                return cardSkin;
            }
        }

        return null;
    }
    public static OrbSkin makeOrbSkinForOrb(String orbType){
        PlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin == null){
            return null;
        }

        return SkindexRegistry.getOrbSkinById(currentSkin.orbsSkinMap.get(orbType), true);
    }
    public static StanceSkin getActiveStanceSkin(String stanceId){
        PlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin == null){
            return null;
        }

        return currentSkin.stanceSkinMap.get(stanceId);
    }

    //region Stance Override
    private static AbstractCreature stanceOverrideRenderCreature = null;

    public static AbstractCreature getStanceOverrideRenderCreature(){
        return stanceOverrideRenderCreature;
    }

    public static void setStanceOverrideRenderCreature(AbstractCreature creature){
        stanceOverrideRenderCreature = creature;
    }
    //endregion Stance Override
}
