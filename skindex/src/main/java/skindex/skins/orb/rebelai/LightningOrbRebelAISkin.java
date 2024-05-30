package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Lightning;
import skindex.skins.orb.OrbImageSkin;
import skindex.skins.orb.OrbImageSkinData;

public class LightningOrbRebelAISkin extends OrbImageSkin {
    /** Constructors */
    public LightningOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends OrbImageSkinData {
        public static String skinID = "LIGHTNING_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/lightning.png";
            this.orbId = Lightning.ORB_ID;
        }
    }
}
