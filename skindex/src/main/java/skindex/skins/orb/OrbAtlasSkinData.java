package skindex.skins.orb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrbAtlasSkinData extends OrbSkinData {
    @SerializedName("atlasUrl")
    public String atlasUrl;
    @SerializedName("skeletonUrl")
    public String skeletonUrl;

    @SerializedName("defaultAnimName")
    public String defaultAnimName = "Idle";
    @SerializedName("idleAnimVariants")
    public ArrayList<String> idleAnimVariants = new ArrayList<>();

    @SerializedName("evokeAnimVariants")
    public ArrayList<String> evokeAnimVariants = new ArrayList<>();

    @SerializedName("defaultTimeScale")
    public Float defaultTimeScale = 1.f;

    @SerializedName("invertedSkeletonScale")
    public Float scale = 1.0f;
}
