package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Frost;
import skindex.skins.orb.OrbImageSkin;
import skindex.skins.orb.OrbImageSkinData;

public class FrostOrbRebelAISkin extends OrbImageSkin {
    /** Constructors */
    public FrostOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends OrbImageSkinData {
        public static String skinID = "FROST_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/frost.png";
            this.orbId = Frost.ORB_ID;
        }
    }
}
