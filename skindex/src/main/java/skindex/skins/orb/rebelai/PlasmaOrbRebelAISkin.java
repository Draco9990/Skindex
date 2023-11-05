package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Plasma;
import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.OrbSkinData;

public class PlasmaOrbRebelAISkin extends OrbSkin {
    /** Constructors */
    public PlasmaOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends OrbSkinData{
        public static String skinID = "PLASMA_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/plasma.png";
            this.orbId = Plasma.ORB_ID;
        }
    }
}
