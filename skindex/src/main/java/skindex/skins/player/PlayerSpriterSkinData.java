package skindex.skins.player;

import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class PlayerSpriterSkinData extends PlayerSkinData {
    /** Variables */
    @SerializedName("scmlUrl")
    public String scmlUrl;

    /** Create Player Skin */
    @Override
    public PlayerSkin createPlayerSkin() {
        return new PlayerSpriterSkin(this);
    }
}
