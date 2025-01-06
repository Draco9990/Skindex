package skindex.skins.player;

import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.Reflection;
import skindex.entities.player.SkindexPlayerAtlasEntity;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.patches.PlayerLoadAnimationPatcher;

public class PlayerAtlasSkin extends AbstractPlayerSkin {
    /** Variables */
    public String atlasUrl;
    public String skeletonUrl;

    public String defaultAnimName;

    public Float defaultTimeScale;

    /** Constructors */
    public PlayerAtlasSkin(PlayerAtlasSkinData skinData){
        super(skinData);

        this.atlasUrl = skinData.atlasUrl;
        this.skeletonUrl = skinData.skeletonUrl;

        this.defaultAnimName = skinData.defaultAnimName;

        this.defaultTimeScale = skinData.defaultTimeScale;

        if(skinData.invertedSkeletonScale != null){
            scale = 1/ skinData.invertedSkeletonScale;
        }
    }

    /** Methods */
    @Override
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        if(!super.loadOnEntity(entity)) return false;
        if(!(entity instanceof SkindexPlayerAtlasEntity)) return false;

        ((SkindexPlayerAtlasEntity) entity).loadAnimation(atlasUrl, skeletonUrl, resourceDirectory, 1 / scale);
        ((SkindexPlayerAtlasEntity) entity).getState().setAnimation(0, defaultAnimName, true);
        ((SkindexPlayerAtlasEntity) entity).getState().setTimeScale(defaultTimeScale);
        return true;
    }
    @Override
    public boolean loadOnPlayer() {
        if(!super.loadOnPlayer()) return false;

        PlayerLoadAnimationPatcher.LoadAnimationConsumer.resourceDirectoryUrl = resourceDirectory;
        Reflection.invokeMethod("loadAnimation", AbstractDungeon.player, atlasUrl, skeletonUrl, 1 / scale);
        AbstractDungeon.player.state.setAnimation(0, defaultAnimName, true);
        AbstractDungeon.player.state.setTimeScale(defaultTimeScale);

        return true;
    }

    public static class PlayerAtlasSkinData extends PlayerSkinData {
        /** Variables */
        @SerializedName("atlasUrl")
        public String atlasUrl;
        @SerializedName("skeletonUrl")
        public String skeletonUrl;
        @SerializedName("defaultAnimName")
        public String defaultAnimName = "Idle";

        @SerializedName("defaultTimeScale")
        public Float defaultTimeScale = 1.f;

        @SerializedName("invertedSkeletonScale")
        public Float invertedSkeletonScale = null;

    }
}
