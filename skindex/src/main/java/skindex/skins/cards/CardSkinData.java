package skindex.skins.cards;

import com.google.gson.annotations.SerializedName;
import skindex.itemtypes.AbstractCustomizableItem;

import java.io.Serializable;

public class CardSkinData extends AbstractCustomizableItem.CustomizableItemData implements Serializable {
    static final long serialVersionUID = 1L;

    @SerializedName("cardType")
    public String cardType;
    @SerializedName("cardColor")
    public String cardColor;

    @SerializedName("cardBg")
    public String cardBg;
}
