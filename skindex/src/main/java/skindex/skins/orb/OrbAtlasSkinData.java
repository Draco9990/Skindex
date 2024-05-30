package skindex.skins.orb;

import com.google.gson.annotations.SerializedName;

public class OrbAtlasSkinData extends OrbSkinData {
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
