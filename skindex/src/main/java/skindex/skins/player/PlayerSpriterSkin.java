package skindex.skins.player;

import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skindex.SkindexGame;

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

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = AbstractPlayer.class, method = "renderPlayerImage")
        public static class EffectPatcher{
            public static SpireReturn Prefix(AbstractPlayer __instance, SpriteBatch sb){
                if(Objects.equals(__instance, AbstractDungeon.player)){
                    PlayerSkin playerSkin = SkindexGame.getActivePlayerSkin();
                    if(playerSkin instanceof PlayerSpriterSkin){
                        ((PlayerSpriterSkin) playerSkin).renderModel(sb, (int)(__instance.drawX + __instance.animX), (int)(__instance.drawY + __instance.animY), __instance.flipHorizontal, __instance.flipVertical, 1);
                        return SpireReturn.Return();
                    }
                }
                return SpireReturn.Continue();
            }
        }
    }
}
