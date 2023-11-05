package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Frost;
import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.OrbSkinData;

public class FrostOrbRebelAISkin extends OrbSkin {
    /** Constructors */
    public FrostOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends OrbSkinData{
        public static String skinID = "FROST_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/frost.png";
            this.orbId = Frost.ORB_ID;
        }
    }
}
