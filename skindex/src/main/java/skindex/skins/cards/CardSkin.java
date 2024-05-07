package skindex.skins.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderFixSwitches;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import skindex.SkindexGame;
import skindex.itemtypes.CustomizableItem;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneAtlasSkin;
import skindex.skins.player.PlayerSkin;

public class CardSkin extends CustomizableItem {
    //region Variables
    protected TextureAtlas.AtlasRegion attackBg;
    protected TextureAtlas.AtlasRegion skillBg;
    protected TextureAtlas.AtlasRegion powerBg;

    protected AbstractCard.CardColor cardColor;
    //endregion Variables

    //region Constructors
    public CardSkin(CardSkinData cardSkinData){
        super(cardSkinData);

        Texture attackTexture = loadImageIfExists(cardSkinData.attackBg);
        Texture skillTexture = loadImageIfExists(cardSkinData.skillBg);
        Texture powerTexture = loadImageIfExists(cardSkinData.powerBg);

        if(attackTexture != null) this.attackBg = makeAtlasRegionFromTexture(attackTexture);
        if(skillTexture != null) this.skillBg = makeAtlasRegionFromTexture(skillTexture);
        if(powerTexture != null) this.powerBg = makeAtlasRegionFromTexture(powerTexture);

        if(cardSkinData.cardColor != null) cardColor = AbstractCard.CardColor.valueOf(cardSkinData.cardColor);
    }
    //endregion Constructors

    //region Class Methods

    @Override
    public void dispose() {
        super.dispose();

        if(this.attackBg != null) this.attackBg.getTexture().dispose();
        if(this.skillBg != null) this.skillBg.getTexture().dispose();
        if(this.powerBg != null) this.powerBg.getTexture().dispose();
    }
    //endregion Class Methods

    //region Patches
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

        /** For Modded Characters */
        @SpirePatch2(clz = RenderFixSwitches.class, method = "renderHelper", paramtypez = {AbstractCard.class, SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class})
        public static class CardBGPatcherModded{
            public static void Prefix(AbstractCard card, @ByRef TextureAtlas.AtlasRegion[] region){
                CardSkin cardSkin = SkindexGame.getActiveCardSkinForColor(card.color);
                if(cardSkin != null && region != null && region.length > 0){
                    if(card.type.equals(AbstractCard.CardType.ATTACK) && cardSkin.attackBg != null){
                        region[0] = cardSkin.attackBg;
                    }
                    if(card.type.equals(AbstractCard.CardType.SKILL) && cardSkin.skillBg != null){
                        region[0] = cardSkin.skillBg;
                    }
                    if(card.type.equals(AbstractCard.CardType.POWER) && cardSkin.powerBg != null){
                        region[0] = cardSkin.powerBg;
                    }
                }
            }
        }

        @SpirePatch2(clz = RenderFixSwitches.class, method = "renderHelper", paramtypez = {AbstractCard.class, SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class, float.class})
        public static class CardBGPatcherModded2{
            public static void Prefix(AbstractCard card, @ByRef TextureAtlas.AtlasRegion[] region){
                CardSkin cardSkin = SkindexGame.getActiveCardSkinForColor(card.color);
                if(cardSkin != null && region != null && region.length > 0){
                    if(card.type.equals(AbstractCard.CardType.ATTACK) && cardSkin.attackBg != null){
                        region[0] = cardSkin.attackBg;
                    }
                    if(card.type.equals(AbstractCard.CardType.SKILL) && cardSkin.skillBg != null){
                        region[0] = cardSkin.skillBg;
                    }
                    if(card.type.equals(AbstractCard.CardType.POWER) && cardSkin.powerBg != null){
                        region[0] = cardSkin.powerBg;
                    }
                }
            }
        }
    }
    //endregion
}
