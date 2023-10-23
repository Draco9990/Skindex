package skindex.skins.orb;

import com.google.gson.annotations.SerializedName;
import skindex.itemtypes.CustomizableItemData;

import java.io.Serializable;

public class OrbSkinData extends CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    /** Resources */
    @SerializedName("orbId")
    public String orbId;
    @SerializedName("texureUrl")
    public String textureUrl;

    @SerializedName("overlay")
    public boolean overlay = false;
}
