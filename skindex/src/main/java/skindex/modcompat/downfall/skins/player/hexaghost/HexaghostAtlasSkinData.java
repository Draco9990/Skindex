package skindex.modcompat.downfall.skins.player.hexaghost;

import com.google.gson.annotations.SerializedName;
import skindex.skins.entity.player.PlayerAtlasSkin;

public class HexaghostAtlasSkinData extends PlayerAtlasSkin.PlayerAtlasSkinData {
    /** Variables */
    @SerializedName("plasma1Url")
    public String plasma1Url;
    @SerializedName("plasma2Url")
    public String plasma2Url;
    @SerializedName("plasma3Url")
    public String plasma3Url;
    @SerializedName("shadowUrl")
    public String shadowUrl;

    @SerializedName("disablePlasma")
    public boolean disablePlasma = false;

    @SerializedName("standardRotationSpeed")
    public Float standardRotationSpeed = 20.f;
    @SerializedName("fastRotationSpeed")
    public Float fastRotationSpeed = 100.f;

}
