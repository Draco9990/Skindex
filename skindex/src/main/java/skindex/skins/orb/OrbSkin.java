package skindex.skins.orb;

import com.badlogic.gdx.graphics.Texture;
import skindex.itemtypes.CustomizableItem;

public class OrbSkin extends CustomizableItem {
    /** Variables */
    public String orbId;
    public Texture texture;

    public boolean overlay = false;

    /** Constructors */
    public OrbSkin(OrbSkinData orbSkinData){
        super(orbSkinData);

        //texture = loadImageIfExists()
    }
}
