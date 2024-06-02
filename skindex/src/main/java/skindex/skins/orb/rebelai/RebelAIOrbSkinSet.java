package skindex.skins.orb.rebelai;

import skindex.skins.orb.*;

import java.util.Arrays;
import java.util.List;

public class RebelAIOrbSkinSet {
    public static List<OrbSkin> collectOrbSkins(){
        return Arrays.asList(
                new OrbImageSkin(new Lightning()),
                new OrbImageSkin(new Frost()),
                new OrbImageSkin(new Dark()),
                new OrbImageSkin(new Plasma())
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

    public static class Lightning extends OrbImageSkinData {
        public static String skinID = "LIGHTNING_REBELAI";

        public Lightning(){
            this.id = skinID;

            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/lightning.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Lightning.ORB_ID;
        }
    }

    public static class Frost extends OrbImageSkinData{
        public static String skinID = "FROST_REBELAI";

        public Frost(){
            this.id = skinID;

            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/frost.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Frost.ORB_ID;
        }
    }

    public static class Dark extends OrbImageSkinData{
        public static String skinID = "DARK_REBELAI";

        public Dark(){
            this.id = skinID;

            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/dark.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Dark.ORB_ID;
        }
    }

    public static class Plasma extends OrbImageSkinData{
        public static String skinID = "PLASMA_REBELAI";

        public Plasma(){
            this.id = skinID;
            this.orbImageUrl = "skindexResources/images/skins/player/defect/rebel_ai/plasma.png";
            this.orbId = com.megacrit.cardcrawl.orbs.Plasma.ORB_ID;
        }
    }
}
