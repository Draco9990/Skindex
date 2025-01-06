package skindex.skins.player;

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
import skindex.entities.player.SkindexPlayerImageEntity;

import java.io.Serializable;
import java.util.Objects;

public class PlayerImageSkin extends PlayerSkin{
    /** Variables */
    private Texture image;

    /** Constructors */
    public PlayerImageSkin(PlayerImageSkinData skinData) {
        super(skinData);

        image = loadImageIfExists(skinData.imageUrl);
    }

    /** Update */
    @Override
    public void update(AbstractCreature updateSource) {
        super.update(updateSource);

        if(updateSource instanceof SkindexPlayerImageEntity){
            ((SkindexPlayerImageEntity) updateSource).setRenderImage(getImageToRender());
        }
        else if(updateSource instanceof AbstractPlayer){
            AbstractDungeon.player.img = getImageToRender();
            Reflection.setFieldValue("atlas", AbstractDungeon.player, null);
        }
    }

    /** Image */
    protected Texture getImageToRender(){
        return image;
    }

    /** Render Helpers */
    public void renderSkinImage(SpriteBatch sb, AbstractCreature creature, Texture img, float scaleMult){
        sb.setColor(renderColor);

        sb.draw(img, creature.drawX - ((float)img.getWidth() / 2.0F) * scale * Settings.xScale + creature.animX, creature.drawY, (float)img.getWidth() / 2, 0, (float)img.getWidth() * Settings.scale, (float)img.getHeight() * Settings.scale, scaleMult * scale * Settings.xScale, scaleMult * scale * Settings.yScale, 0, 0, 0, img.getWidth(), img.getHeight(), creature.flipHorizontal, creature.flipVertical);
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = AbstractPlayer.class, method = "render")
        public static class PlayerImageSkinPatcher {
            @SpireInsertPatch(locator = Locator.class)
            public static SpireReturn Insert(AbstractPlayer __instance, SpriteBatch sb){
                if(Objects.equals(AbstractDungeon.player, __instance)){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin instanceof PlayerImageSkin){
                        PlayerImageSkin imgSkin = (PlayerImageSkin) currentSkin;
                        imgSkin.renderSkinImage(sb, __instance, __instance.img, 1.0f);

                        return SpireReturn.Return();
                    }
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch2(clz = AbstractPlayer.class, method = "renderPlayerImage")
        public static class PlayerImageSkinPatcher2 {
            @SpireInsertPatch(locator = Locator.class)
            public static SpireReturn Insert(AbstractPlayer __instance, SpriteBatch sb){
                if(Objects.equals(AbstractDungeon.player, __instance)){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin instanceof PlayerImageSkin){
                        PlayerImageSkin imgSkin = (PlayerImageSkin) currentSkin;
                        imgSkin.renderSkinImage(sb, __instance, __instance.img, 1.0f);

                        return SpireReturn.Return();
                    }
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
