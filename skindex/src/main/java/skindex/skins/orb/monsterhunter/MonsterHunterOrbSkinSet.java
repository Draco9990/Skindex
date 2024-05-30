package skindex.skins.orb.monsterhunter;

import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import skindex.skins.orb.OrbAtlasSkin;
import skindex.skins.orb.OrbAtlasSkinData;
import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.rebelai.DefectRebelAIOrbSkinSet;

import java.util.Arrays;
import java.util.List;

public class MonsterHunterOrbSkinSet {
    /*public static List<OrbSkin> collectOrbSkins(){
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
    }*/

    public static class Lightning extends OrbAtlasSkinData{
        public static String skinID = "LIGHTNING_MONSTERHUNTER";

        public Lightning(){
            this.id = skinID;

            this.atlasUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/lightning/skeleton.atlas";
            this.skeletonUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/lightning/skeleton.json";
            this.scale = 0.3f;

            idleAnimVariants.add("Idle1");
            idleAnimVariants.add("Idle2");
            idleAnimVariants.add("Idle3");
            idleAnimVariants.add("Idle4");

            this.orbId = com.megacrit.cardcrawl.orbs.Lightning.ORB_ID;
        }
    }
}
