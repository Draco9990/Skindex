package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Dark;
import skindex.skins.orb.OrbImageSkin;
import skindex.skins.orb.OrbImageSkinData;

public class DarkOrbRebelAISkin extends OrbImageSkin {
    /** Constructors */
    public DarkOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends OrbImageSkinData {
        public static String skinID = "DARK_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/dark.png";
            this.orbId = Dark.ORB_ID;
        }
    }
}
