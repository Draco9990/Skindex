package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Lightning;
import skindex.skins.orb.ImageOrbSkin;
import skindex.skins.orb.ImageOrbSkinData;
import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.OrbSkinData;

public class LightningOrbRebelAISkin extends ImageOrbSkin {
    /** Constructors */
    public LightningOrbRebelAISkin() {
        super(new SkinData());
    }

    /** Patches */
    public static class SkinData extends ImageOrbSkinData {
        public static String skinID = "LIGHTNING_REBELAI";

        public SkinData(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/lightning.png";
            this.orbId = Lightning.ORB_ID;
        }
    }
}
