package skindex.skins.player;

import com.google.gson.annotations.SerializedName;

public class PlayerAtlasSkinData extends PlayerSkinData{
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

    /** Create Player Skin */
    @Override
    public PlayerSkin createPlayerSkin() {
        return new PlayerAtlasSkin(this);
    }
}
