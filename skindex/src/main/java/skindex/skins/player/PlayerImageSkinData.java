package skindex.skins.player;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayerImageSkinData extends PlayerSkinData implements Serializable {
    static final long serialVersionUID = 1L;

    /** Variables */
    @SerializedName("imageUrl")
    public String imageUrl;
}
