package skindex.modcompat.downfall.skins.player.collector;

import com.google.gson.annotations.SerializedName;
import skindex.skins.player.PlayerAtlasSkin;

import java.util.ArrayList;

public class CollectorAtlasSkinData extends PlayerAtlasSkin.PlayerAtlasSkinData {
    /** Variables */
    @SerializedName("fireImageUrls")
    public ArrayList<String> fireImageUrls = new ArrayList<>();
    @SerializedName("fireColors")
    public ArrayList<String> fireColors = new ArrayList<>();
    @SerializedName("noFire")
    public boolean noFire;

}
