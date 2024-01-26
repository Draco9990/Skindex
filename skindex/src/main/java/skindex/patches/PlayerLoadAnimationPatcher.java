package skindex.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import dLib.util.AssetLoader;
import dLib.util.Reflection;

public class PlayerLoadAnimationPatcher {
    @SpirePatch2(clz = AbstractCreature.class, method = "loadAnimation")
    public static class LoadAnimationConsumer{
        public static String resourceDirectoryUrl = null;

        public static SpireReturn Prefix(AbstractCreature __instance, String atlasUrl, String skeletonUrl, float scale){
            if(resourceDirectoryUrl != null && __instance instanceof AbstractPlayer){
                TextureAtlas atlas = AssetLoader.loadTextureAtlas(atlasUrl, resourceDirectoryUrl);
                Reflection.setFieldValue("atlas", __instance, atlas);
                SkeletonJson json = new SkeletonJson(atlas);

                if (CardCrawlGame.dungeon != null && AbstractDungeon.player != null) {
                    if (AbstractDungeon.player.hasRelic("PreservedInsect") && !__instance.isPlayer && AbstractDungeon.getCurrRoom().eliteTrigger) {
                        scale += 0.3F;
                    }

                    if (ModHelper.isModEnabled("MonsterHunter") && !__instance.isPlayer) {
                        scale -= 0.3F;
                    }
                }

                json.setScale(Settings.renderScale / scale);
                SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
                Skeleton skeleton = new Skeleton(skeletonData);
                Reflection.setFieldValue("skeleton", __instance, skeleton);
                AnimationStateData stateData = new AnimationStateData(skeletonData);
                Reflection.setFieldValue("stateData", __instance, stateData);
                __instance.state = new AnimationState(stateData);

                resourceDirectoryUrl = null;

                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }
    }
}
