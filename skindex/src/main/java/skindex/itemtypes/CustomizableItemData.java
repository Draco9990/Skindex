package skindex.itemtypes;

import com.google.gson.annotations.SerializedName;
import skindex.unlockmethods.FreeUnlockMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    @SerializedName("ID")
    public String id = "itemSkin_" + UUID.randomUUID();
    @SerializedName("name")
    public String name = "";

    @SerializedName("icon")
    public String icon = "skindexResources/images/ui/preview/defaultPreviewIcon.png";

    @SerializedName("credits")
    public ArrayList<String> credits = new ArrayList<>();
}
