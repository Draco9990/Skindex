package skindex.skins.player;

import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.Reflection;
import javassist.*;
import org.clapper.util.classutil.ClassFinder;
import skindex.SkindexGame;
import skindex.util.SkindexLogger;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class PlayerSpriterSkin extends PlayerSkin{
    /** Variables */
    public SpriterAnimation model;

    /** Constructors */
    public PlayerSpriterSkin(PlayerSpriterSkinData skinData){
        super(skinData);

        this.model = new SpriterAnimation(skinData.scmlUrl);
    }

    /** Methods */
    public void renderModel(SpriteBatch sb, int x, int y, boolean flipHorizontal, boolean flipVertical, float scale){
        model.myPlayer.setScale(scale * this.scale * Settings.renderScale);
        model.setFlip(flipHorizontal, flipVertical);

        boolean simulatingPlayer = false;
        if(AbstractDungeon.player == null){
            simulatingPlayer = true;
            AbstractDungeon.player = CardCrawlGame.characterManager.getCharacter(AbstractPlayer.PlayerClass.IRONCLAD);
        }
        model.renderSprite(sb, x, y);
        if(simulatingPlayer){
            AbstractDungeon.player = null;
        }
    }

    public static boolean renderSpriterSkinModel(AbstractPlayer __instance, SpriteBatch sb){
        if(Objects.equals(__instance, AbstractDungeon.player)){
            PlayerSkin playerSkin = SkindexGame.getActivePlayerSkin();
            if(playerSkin instanceof PlayerSpriterSkin){
                ((PlayerSpriterSkin) playerSkin).renderModel(sb, (int)(__instance.drawX + __instance.animX), (int)(__instance.drawY + __instance.animY), __instance.flipHorizontal, __instance.flipVertical, 1);
                return true;
            }
        }

        return false;
    }

    /** Patches */
    public static class Patches{
        @SpirePatch(
                clz = CardCrawlGame.class,
                method = SpirePatch.CONSTRUCTOR
        )
        public static class DynamicSpriterPatch {
            public static void Raw(CtBehavior ctBehavior) {
                SkindexLogger.log("Skindex => Dynamically patching Spriter render.");

                for(CtMethod method : Reflection.findMethodsFromClasses(ctBehavior, AbstractPlayer.class, true, "renderPlayerImage", SpriteBatch.class)){
                    if(method == null) continue;

                    try{
                        method.insertBefore("{ if(" + PlayerSpriterSkin.class.getName() + ".renderSpriterSkinModel($0, $1)){ return; }}");
                        SkindexLogger.log("Skindex => Patched " + method.getName() + " of class " + method.getClass().getEnclosingClass().getName() + ".");
                    }catch (Exception e){
                        SkindexLogger.logError("Skindex => Could not compile the dynamic spriter patch on method " + method.getName() + " due to: " + e.getLocalizedMessage(), SkindexLogger.ErrorType.NON_FATAL);
                    }
                }

                System.out.println("Skindex => Finished dynamically patching Spriter render.");
            }
        }
    }
}
