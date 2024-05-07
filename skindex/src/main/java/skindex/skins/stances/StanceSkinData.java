package skindex.skins.stances;

import com.google.gson.annotations.SerializedName;
import skindex.itemtypes.CustomizableItemData;

import java.io.Serializable;

public class StanceSkinData extends CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    @SerializedName("stanceId")
    public String stanceId;
}
