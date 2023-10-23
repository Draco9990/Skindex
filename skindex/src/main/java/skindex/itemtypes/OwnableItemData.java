package skindex.itemtypes;

import com.google.gson.annotations.SerializedName;
import skindex.unlockmethods.FreeUnlockMethod;

import java.io.Serializable;

public class OwnableItemData extends CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    /** Variables */
    @SerializedName("unlockMethod")
    public String unlockMethod = FreeUnlockMethod.methodId;

    @SerializedName("tracker")
    public String tracker;
}
