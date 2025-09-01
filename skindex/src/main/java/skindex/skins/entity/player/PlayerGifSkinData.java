package skindex.skins.entity.player;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayerGifSkinData extends PlayerImageSkin.PlayerImageSkinData implements Serializable {
    static final long serialVersionUID = 1L;

    /** Variables */
    @SerializedName("gifUrl")
    public String gifUrl;
}
