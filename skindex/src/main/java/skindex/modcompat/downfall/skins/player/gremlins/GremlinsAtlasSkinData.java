package skindex.modcompat.downfall.skins.player.gremlins;

import com.google.gson.annotations.SerializedName;
import skindex.skins.player.PlayerSkin;
import skindex.skins.player.PlayerSkinData;

import java.util.ArrayList;

public class GremlinsAtlasSkinData extends PlayerSkinData {
    /** Variables */
    @SerializedName("gremlins")
    public ArrayList<GremlinAtlasSkinData> gremlins = new ArrayList<>();

    /** Generate player skin */
    @Override
    public PlayerSkin createPlayerSkin() {
        return new GremlinsAtlasSkin(this);
    }

    public static class GremlinAtlasSkinData{
        /** Variables */
        @SerializedName("gremlin")
        public String gremlinId;

        @SerializedName("atlasUrl")
        public String atlasUrl;
        @SerializedName("skeletonUrl")
        public String skeletonUrl;
        @SerializedName("resourceDirectoryUrl")
        public String resourceDirectoryUrl;
    }
}
