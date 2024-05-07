package skindex.skins.orb.rebelai;

import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.OrbSkinData;

import java.util.Arrays;
import java.util.List;

public class DefectRebelAIOrbSkinSet {
    public static List<OrbSkin> collectOrbSkins(){
        return Arrays.asList(
                new OrbSkin(new Lightning()),
                new OrbSkin(new Frost()),
                new OrbSkin(new Dark()),
                new OrbSkin(new Plasma())
        );
    }

    public static List<String> collectOrbSkinIds(){
        return Arrays.asList(
                Lightning.skinID,
                Frost.skinID,
                Dark.skinID,
                Plasma.skinID
        );
    }

    public static class Lightning extends OrbSkinData {
        public static String skinID = "LIGHTNING_REBELAI";

        public Lightning(){
            this.id = skinID;

            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/lightning.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Lightning.ORB_ID;
        }
    }

    public static class Frost extends OrbSkinData{
        public static String skinID = "FROST_REBELAI";

        public Frost(){
            this.id = skinID;

            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/frost.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Frost.ORB_ID;
        }
    }

    public static class Dark extends OrbSkinData{
        public static String skinID = "DARK_REBELAI";

        public Dark(){
            this.id = skinID;

            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/dark.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Dark.ORB_ID;
        }
    }

    public static class Plasma extends OrbSkinData{
        public static String skinID = "PLASMA_REBELAI";

        public Plasma(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/plasma.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Plasma.ORB_ID;
        }
    }
}
