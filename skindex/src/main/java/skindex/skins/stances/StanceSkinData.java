package skindex.skins.stances;

import com.google.gson.annotations.SerializedName;
import skindex.itemtypes.CustomizableItemData;
import skindex.skins.misc.particle.ParticleData;

import java.io.Serializable;
import java.util.HashMap;

public class StanceSkinData extends CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    @SerializedName("stanceId")
    public String stanceId;

    @SerializedName("particles")
    public HashMap<float[], ParticleData> particleData = new HashMap<>();
}
