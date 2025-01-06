package skindex.skins.stances;

import com.google.gson.annotations.SerializedName;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.skins.misc.particle.ParticleData;

import java.io.Serializable;
import java.util.HashMap;

public class StanceSkinData extends AbstractCustomizableItem.CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    @SerializedName("stanceId")
    public String stanceId;

    @SerializedName("stanceEnterSound")
    public String stanceEnterSound;

    @SerializedName("particles")
    public HashMap<float[], ParticleData> particleData = new HashMap<>();
}
