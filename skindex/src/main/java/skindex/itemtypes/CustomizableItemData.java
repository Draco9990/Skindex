package skindex.itemtypes;

import com.google.gson.annotations.SerializedName;
import skindex.unlockmethods.FreeUnlockMethod;

import java.io.Serializable;
import java.util.UUID;

public class CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    /** Variables */
    @SerializedName("ID")
    public String id = "playerSkin_" + UUID.randomUUID();
    @SerializedName("name")
    public String name;
    @SerializedName("unlockMethod")
    public String unlockMethod = FreeUnlockMethod.methodId;
}
