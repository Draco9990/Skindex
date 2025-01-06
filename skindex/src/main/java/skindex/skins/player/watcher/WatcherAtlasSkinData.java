package skindex.skins.player.watcher;

import com.google.gson.annotations.SerializedName;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerSkin;

public class WatcherAtlasSkinData extends PlayerAtlasSkin.PlayerAtlasSkinData {
    /** Variables */
    @SerializedName("eyeAtlasUrl")
    public String eyeAtlasUrl;
    @SerializedName("eyeSkeletonUrl")
    public String eyeSkeletonUrl;
    @SerializedName("eyeResourceDirectoryUrl")
    public String eyeResourceDirectoryUrl;

    /** Generate Player Skin */
    @Override
    public PlayerSkin createPlayerSkin() {
        return new WatcherAtlasSkin(this);
    }
}
