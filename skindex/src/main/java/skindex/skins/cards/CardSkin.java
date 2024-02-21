package skindex.skins.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import skindex.SkindexGame;
import skindex.itemtypes.CustomizableItem;

public class CardSkin extends CustomizableItem {
    /** Variables */
    public AbstractCard.CardColor cardColor;

    public TextureAtlas.AtlasRegion attackBg;
    public TextureAtlas.AtlasRegion skillBg;
    public TextureAtlas.AtlasRegion powerBg;

    /** Constructors */
    public CardSkin(CardSkinData cardSkinData){
        super(cardSkinData);

        Texture attackTexture = loadImageIfExists(cardSkinData.attackBg);
        Texture skillTexture = loadImageIfExists(cardSkinData.skillBg);
        Texture powerTexture = loadImageIfExists(cardSkinData.powerBg);

        this.attackBg = makeAtlasRegionFromTexture(attackTexture);
        this.skillBg = makeAtlasRegionFromTexture(skillTexture);
        this.powerBg = makeAtlasRegionFromTexture(powerTexture);

        if(cardSkinData.cardColor != null) cardColor = AbstractCard.CardColor.valueOf(cardSkinData.cardColor);
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = AbstractCard.class, method = "renderAttackBg")
        public static class AttackCardBGPatcher{
            public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor){
                CardSkin cardSkin = SkindexGame.getActiveCardSkinForColor(__instance.color);
                if(cardSkin != null && cardSkin.attackBg != null){
                    dLib.util.Reflection.invokeMethod("renderHelper", __instance, sb, ___renderColor, cardSkin.attackBg, x, y);
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch2(clz = AbstractCard.class, method = "renderSkillBg")
        public static class SkillCardBGPatcher{
            public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor){
                CardSkin cardSkin = SkindexGame.getActiveCardSkinForColor(__instance.color);
                if(cardSkin != null && cardSkin.skillBg != null){
                    dLib.util.Reflection.invokeMethod("renderHelper", __instance, sb, ___renderColor, cardSkin.skillBg, x, y);
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch2(clz = AbstractCard.class, method = "renderPowerBg")
        public static class PowerCardBGPatcher{
            public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor){
                CardSkin cardSkin = SkindexGame.getActiveCardSkinForColor(__instance.color);
                if(cardSkin != null && cardSkin.powerBg != null){
                    dLib.util.Reflection.invokeMethod("renderHelper", __instance, sb, ___renderColor, cardSkin.powerBg, x, y);
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
}