package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Plasma;
import skindex.skins.orb.OrbImageSkin;
import skindex.skins.orb.OrbImageSkinData;

public class PlasmaOrbRebelAISkin extends OrbImageSkin {
    /** Constructors */
    public PlasmaOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends OrbImageSkinData {
        public static String skinID = "PLASMA_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/plasma.png";
            this.orbId = Plasma.ORB_ID;
        }
    }
}
