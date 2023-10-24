package skindex;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skindex.registering.SkindexRegistry;
import skindex.skins.cards.CardSkin;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;
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

    public static ArrayList<CardSkin> getActiveCardSkins(){
        PlayerSkin currentSkin = getActivePlayerSkin();
        if(currentSkin != null){
            return currentSkin.cardSkins;
        }
        return new ArrayList<>();
    }
    public static CardSkin getActiveCardSkinForColor(AbstractCard.CardColor cardColor){
        ArrayList<CardSkin> cardSkins = getActiveCardSkins();

        for(CardSkin cardSkin : cardSkins){
            if(Objects.equals(cardSkin.cardColor, cardColor)){
                return cardSkin;
            }
        }

        for(CardSkin cardSkin : cardSkins){
            if(cardSkin.cardColor == null){
                return cardSkin;
            }
        }

        return null;
    }
}
