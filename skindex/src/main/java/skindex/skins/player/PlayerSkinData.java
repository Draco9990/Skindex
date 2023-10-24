package skindex.skins.player;

import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.itemtypes.CustomizableItemData;
import skindex.itemtypes.OwnableItemData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSkinData extends OwnableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    /** Resources */
    @SerializedName("playerClass")
    public String playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();

    @SerializedName("resourceDirectoryUrl")
    public String resourceDirectoryUrl;
    @SerializedName("scale")
    public Float scale = 1.f;

    /** Shoulder & Death */
    @SerializedName("shoulderImg")
    public String shoulderIMG;
    @SerializedName("shoulder2Img")
    public String shoulder2IMG;
    @SerializedName("corpseIms")
    public String corpseIMG;

    /** Card stuff */
    @SerializedName("cardSkins")
    public ArrayList<String> cardSkins = new ArrayList<>();
    @SerializedName("cardTrailColors")
    public ArrayList<String> cardTrailColors = new ArrayList<>();

    @SerializedName("orbSkins")
    public HashMap<String, String> orbSkins = new HashMap<>();
    @SerializedName("stanceSkins")
    public HashMap<String, String> stanceSkins = new HashMap<>();

    /** Generate Player Skin */
    public PlayerSkin createPlayerSkin(){
        return new PlayerSkin(this);
    }
}
