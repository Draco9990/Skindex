package skindex.skins.player.watcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Watcher;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.AssetLoader;
import dLib.util.Reflection;
import skindex.SkindexGame;
import skindex.entities.player.SkindexPlayerAtlasEntity;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.entities.player.SkindexWatcherAtlasEntity;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.skins.player.PlayerSpriterSkin;

public class WatcherAtlasSkin extends PlayerAtlasSkin {
    /** Variables */
    public String eyeAtlasUrl;
    public String eyeSkeletonUrl;
    public String eyeResourceDirectory;

    /** Constructors */
    public WatcherAtlasSkin(WatcherAtlasSkinData skinData) {
        super(skinData);

        this.eyeAtlasUrl = skinData.eyeAtlasUrl;
        this.eyeSkeletonUrl = skinData.eyeSkeletonUrl;
        this.eyeResourceDirectory = skinData.eyeResourceDirectoryUrl;
    }

    /** Methods */
    @Override
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        if(!super.loadOnEntity(entity)) return false;
        if(!(entity instanceof SkindexWatcherAtlasEntity)) return false;

        if(eyeAtlasUrl != null && eyeSkeletonUrl != null){
            ((SkindexWatcherAtlasEntity) entity).setEyeBone(((SkindexPlayerAtlasEntity) entity).getSkeleton().findBone("eye_anchor"));
            loadEyeAnimationOnEntity(((SkindexWatcherAtlasEntity) entity));
        }
        return true;
    }
    public void loadEyeAnimationOnEntity(SkindexWatcherAtlasEntity entity){
        entity.setEyeAtlas(AssetLoader.loadTextureAtlas(eyeAtlasUrl, eyeResourceDirectory));
        SkeletonJson json = new SkeletonJson(entity.getEyeAtlas());
        json.setScale(Settings.renderScale / scale * entity.getScale());

        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(eyeSkeletonUrl));

        Skeleton eyeSkeleton = new Skeleton(skeletonData);
        eyeSkeleton.setColor(Color.WHITE);
        entity.setEyeSkeleton(eyeSkeleton);

        AnimationStateData eyeStateData = new AnimationStateData(skeletonData);
        AnimationState eyeState = new AnimationState(eyeStateData);
        eyeStateData.setDefaultMix(0.2F);
        eyeState.setAnimation(0, "None", true);
        entity.setEyeStateData(eyeStateData);
        entity.setEyeState(eyeState);
    }

    @Override
    public boolean loadOnPlayer(AbstractPlayer player) {
        if(!super.loadOnPlayer(player)) return false;

        if(eyeAtlasUrl != null && eyeSkeletonUrl != null){
            Skeleton skeleton = Reflection.getFieldValue("skeleton", player);
            Reflection.setFieldValue("eyeBone", player, skeleton.findBone("eye_anchor"));
            loadEyeAnimationOnPlayer(player);
        }
        else if(player instanceof Watcher){
            Skeleton eyeSkeleton = Reflection.getFieldValue("eyeSkeleton", player);
            eyeSkeleton.setDrawOrder(new Array<>());
            Reflection.setFieldValue("eyeSkeleton", player, eyeSkeleton);
        }

        return true;
    }

    private void loadEyeAnimationOnPlayer(AbstractPlayer player) {
        Watcher w = (Watcher) player;

        TextureAtlas eyeAtlas = AssetLoader.loadTextureAtlas(eyeAtlasUrl, eyeResourceDirectory);
        Reflection.setFieldValue("eyeAtlas", w, eyeAtlas);

        SkeletonJson json = new SkeletonJson(eyeAtlas);
        json.setScale(Settings.renderScale / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(eyeSkeletonUrl));

        Skeleton skeleton = new Skeleton(skeletonData);
        skeleton.setColor(Color.WHITE);
        Reflection.setFieldValue("eyeSkeleton", w, skeleton);

        AnimationStateData eyeStateData = new AnimationStateData(skeletonData);
        eyeStateData.setDefaultMix(0.2F);
        Reflection.setFieldValue("eyeStateData", w, eyeStateData);

        AnimationState eyeState = new AnimationState(eyeStateData);
        eyeState.setAnimation(0, "None", true);
        Reflection.setFieldValue("eyeState", w, eyeState);
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = Watcher.class, method = "renderPlayerImage")
        public static class NoEyeRenderPatch{
            public static SpireReturn Prefix(Watcher __instance, SpriteBatch sb){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin(__instance);

                if(currentSkin instanceof PlayerSpriterSkin){
                    ((PlayerSpriterSkin) currentSkin).renderModel(sb, (int)(__instance.drawX + __instance.animX), (int)(__instance.drawY + __instance.animY), __instance.flipHorizontal, __instance.flipVertical, 1);
                    return SpireReturn.Return();
                }

                return SpireReturn.Continue();
            }
        }
    }

    public static class WatcherAtlasSkinData extends PlayerAtlasSkinData {
        /** Variables */
        @SerializedName("eyeAtlasUrl")
        public String eyeAtlasUrl;
        @SerializedName("eyeSkeletonUrl")
        public String eyeSkeletonUrl;
        @SerializedName("eyeResourceDirectoryUrl")
        public String eyeResourceDirectoryUrl;

    }
}
