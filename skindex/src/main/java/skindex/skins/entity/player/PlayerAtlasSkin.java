package skindex.skins.entity.player;

import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dLib.util.Reflection;
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

    @Override
    public boolean loadOnPlayer(AbstractPlayer player) {
        if(!super.loadOnPlayer(player)) return false;

        PlayerLoadAnimationPatcher.LoadAnimationConsumer.resourceDirectoryUrl = resourceDirectory;
        Reflection.invokeMethod("loadAnimation", player, atlasUrl, skeletonUrl, 1 / scale);
        player.state.setAnimation(0, defaultAnimName, true);
        player.state.setTimeScale(defaultTimeScale);

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
