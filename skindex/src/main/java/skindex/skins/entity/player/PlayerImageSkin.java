package skindex.skins.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.Reflection;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import skindex.SkindexGame;

import java.io.Serializable;

public class PlayerImageSkin extends AbstractPlayerSkin {
    /** Variables */
    private Texture image;

    /** Constructors */
    public PlayerImageSkin(PlayerImageSkinData skinData) {
        super(skinData);

        image = loadImageIfExists(skinData.imageUrl);
    }

    /** Update */
    @Override
    public void update(AbstractPlayer updateSource) {
        super.update(updateSource);

        if(updateSource != null){
            updateSource.img = getImageToRender();
            Reflection.setFieldValue("atlas", updateSource, null);
        }
    }

    /** Image */
    protected Texture getImageToRender(){
        return image;
    }

    /** Render Helpers */
    public void renderSkinImage(SpriteBatch sb, AbstractCreature creature, Texture img, float scaleMult){
        sb.setColor(renderColor);

        scaleMult *= SkindexGame.getPlayerScale((AbstractPlayer) creature);

        sb.draw(img, creature.drawX - ((float)img.getWidth() / 2.0F) * scale * Settings.xScale + creature.animX, creature.drawY, (float)img.getWidth() / 2, 0, (float)img.getWidth() * Settings.scale, (float)img.getHeight() * Settings.scale, scaleMult * scale * Settings.xScale, scaleMult * scale * Settings.yScale, 0, 0, 0, img.getWidth(), img.getHeight(), creature.flipHorizontal, creature.flipVertical);
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = AbstractPlayer.class, method = "render")
        public static class PlayerImageSkinPatcher {
            @SpireInsertPatch(locator = Locator.class)
            public static SpireReturn Insert(AbstractPlayer __instance, SpriteBatch sb){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin(__instance);
                if(currentSkin instanceof PlayerImageSkin){
                    PlayerImageSkin imgSkin = (PlayerImageSkin) currentSkin;
                    imgSkin.renderSkinImage(sb, __instance, __instance.img, 1.0f);

                    return SpireReturn.Return();
                }

                return SpireReturn.Continue();
            }
        }

        @SpirePatch2(clz = AbstractPlayer.class, method = "renderPlayerImage")
        public static class PlayerImageSkinPatcher2 {
            @SpireInsertPatch(locator = Locator.class)
            public static SpireReturn Insert(AbstractPlayer __instance, SpriteBatch sb){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin(__instance);
                if(currentSkin instanceof PlayerImageSkin){
                    PlayerImageSkin imgSkin = (PlayerImageSkin) currentSkin;
                    imgSkin.renderSkinImage(sb, __instance, __instance.img, 1.0f);

                    return SpireReturn.Return();
                }

                return SpireReturn.Continue();
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SpriteBatch.class, "draw");
                int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                return lines;
            }
        }
    }

    public static class PlayerImageSkinData extends PlayerSkinData implements Serializable {
        static final long serialVersionUID = 1L;

        /** Variables */
        @SerializedName("imageUrl")
        public String imageUrl;
    }
}
