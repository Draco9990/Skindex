package skindex.modcompat.skulHeroSlayer.skins.player;

import com.google.gson.annotations.SerializedName;
import skindex.skins.player.PlayerSkin;
import skindex.skins.player.PlayerSkinData;

public class LittleBoneAtlasSkinData extends PlayerSkinData {
    /** Variables */
    @SerializedName("atlasUrl")
    public String atlasDirectoryUrl;
    @SerializedName("skeletonUrl")
    public String skeletonDirectoryUrl;
    @SerializedName("defaultAnimName")
    public String defaultAnimName = "IDLE";

    /** Create player skin */
    @Override
    public PlayerSkin createPlayerSkin() {
        return new LittleBoneAtlasSkin(this);
    }
}
