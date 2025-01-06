package skindex.skins.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderFixSwitches;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import skindex.SkindexGame;
import skindex.itemtypes.AbstractCustomizableItem;

public final class CardSkin extends AbstractCustomizableItem {
    //region Variables
    public AbstractCard.CardType cardType;
    public AbstractCard.CardColor cardColor;

    public TextureAtlas.AtlasRegion cardBg;
    //endregion Variables

    //region Constructors
    public CardSkin(CardSkinData data){
        super(data);

        if(data.cardType != null) cardType = AbstractCard.CardType.valueOf(data.cardType);
        if(data.cardColor != null) cardColor = AbstractCard.CardColor.valueOf(data.cardColor);

        cardBg = makeAtlasRegionFromTexture(loadImageIfExists(data.cardBg));
    }
    //endregion Constructors

    //region Class Methods
    //endregion Class Methods

    //region Patches
    public static class Patches{
        @SpirePatch2(clz = AbstractCard.class, method = "renderAttackBg")
        public static class AttackCardBGPatcher{
            public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor){
                CardSkin cardSkin = SkindexGame.getActiveCardSkin(__instance.color, __instance.type);
                if(cardSkin != null){
                    dLib.util.Reflection.invokeMethod("renderHelper", __instance, sb, ___renderColor, cardSkin.cardBg, x, y);
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch2(clz = AbstractCard.class, method = "renderSkillBg")
        public static class SkillCardBGPatcher{
            public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor){
                CardSkin cardSkin = SkindexGame.getActiveCardSkin(__instance.color, __instance.type);
                if(cardSkin != null){
                    dLib.util.Reflection.invokeMethod("renderHelper", __instance, sb, ___renderColor, cardSkin.cardBg, x, y);
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch2(clz = AbstractCard.class, method = "renderPowerBg")
        public static class PowerCardBGPatcher{
            public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor){
                CardSkin cardSkin = SkindexGame.getActiveCardSkin(__instance.color, __instance.type);
                if(cardSkin != null){
                    dLib.util.Reflection.invokeMethod("renderHelper", __instance, sb, ___renderColor, cardSkin.cardBg, x, y);
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        /** For Modded Characters */
        @SpirePatch2(clz = RenderFixSwitches.class, method = "renderHelper", paramtypez = {AbstractCard.class, SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class})
        public static class CardBGPatcherModded{
            public static void Prefix(AbstractCard card, @ByRef TextureAtlas.AtlasRegion[] region){
                CardSkin cardSkin = SkindexGame.getActiveCardSkin(card.color, card.type);
                if(cardSkin != null && region != null && region.length > 0){
                    region[0] = cardSkin.cardBg;
                }
            }
        }

        @SpirePatch2(clz = RenderFixSwitches.class, method = "renderHelper", paramtypez = {AbstractCard.class, SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class, float.class})
        public static class CardBGPatcherModded2{
            public static void Prefix(AbstractCard card, @ByRef TextureAtlas.AtlasRegion[] region){
                CardSkin cardSkin = SkindexGame.getActiveCardSkin(card.color, card.type);
                if(cardSkin != null && region != null && region.length > 0){
                    region[0] = cardSkin.cardBg;
                }
            }
        }
    }
    //endregion
}
