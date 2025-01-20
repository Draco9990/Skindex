package skindex.skins.orb.monsterhunter;

import skindex.registering.SkindexRegistry;
import skindex.skins.orb.OrbAtlasSkin;
import skindex.skins.orb.OrbAtlasSkinData;
import skindex.skins.orb.OrbSkin;

import java.util.Arrays;
import java.util.List;

public class MonsterHunterOrbSkinSet {
    public static void registerOrbSkins(){
        SkindexRegistry.registerItem(new OrbAtlasSkin(new Lightning()));
        SkindexRegistry.registerItem(new OrbAtlasSkin(new Frost()));
        SkindexRegistry.registerItem(new OrbAtlasSkin(new Dark()));
        SkindexRegistry.registerItem(new OrbAtlasSkin(new Plasma()));
    }

    public static List<String> collectOrbSkinIds(){
        return Arrays.asList(
                Lightning.skinID,
                Frost.skinID,
                Dark.skinID,
                Plasma.skinID
        );
    }

    public static class Lightning extends OrbAtlasSkinData{
        public static String skinID = "LIGHTNING_MONSTERHUNTER";

        public Lightning(){
            this.id = skinID;

            this.atlasUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/lightning/skeleton.atlas";
            this.skeletonUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/lightning/skeleton.json";
            this.scale = 0.3f;

            idleAnimVariants.add("Idle");
            idleAnimVariants.add("Idle1");
            idleAnimVariants.add("Idle2");
            idleAnimVariants.add("Idle3");
            idleAnimVariants.add("Idle4");

            evokeAnimVariants.add("Evoke");

            this.orbId = com.megacrit.cardcrawl.orbs.Lightning.ORB_ID;
        }
    }

    public static class Frost extends OrbAtlasSkinData{
        public static String skinID = "FROST_MONSTERHUNTER";

        public Frost(){
            this.id = skinID;

            this.atlasUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/frost/skeleton.atlas";
            this.skeletonUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/frost/skeleton.json";
            this.scale = 0.3f;

            idleAnimVariants.add("Idle");
            idleAnimVariants.add("Idle1");
            idleAnimVariants.add("Idle2");
            idleAnimVariants.add("Idle3");
            idleAnimVariants.add("Idle4");
            idleAnimVariants.add("Idle5");

            evokeAnimVariants.add("Evoke");

            this.orbId = com.megacrit.cardcrawl.orbs.Frost.ORB_ID;
        }
    }

    public static class Dark extends OrbAtlasSkinData{
        public static String skinID = "DARK_MONSTERHUNTER";

        public Dark(){
            this.id = skinID;

            this.atlasUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/dark/skeleton.atlas";
            this.skeletonUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/dark/skeleton.json";
            this.scale = 0.3f;

            idleAnimVariants.add("Idle");
            idleAnimVariants.add("Idle1");
            idleAnimVariants.add("Idle2");
            idleAnimVariants.add("Idle3");
            idleAnimVariants.add("Idle4");

            evokeAnimVariants.add("Evoke");

            this.orbId = com.megacrit.cardcrawl.orbs.Dark.ORB_ID;
        }
    }

    public static class Plasma extends OrbAtlasSkinData{
        public static String skinID = "PLASMA_MONSTERHUNTER";

        public Plasma(){
            this.id = skinID;

            this.atlasUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/plasma/skeleton.atlas";
            this.skeletonUrl = "skindexResources/images/skins/player/defect/monsterhunter/orbs/plasma/skeleton.json";
            this.scale = 0.3f;

            idleAnimVariants.add("Idle");
            idleAnimVariants.add("Idle1");
            idleAnimVariants.add("Idle2");
            idleAnimVariants.add("Idle3");
            idleAnimVariants.add("Idle4");

            evokeAnimVariants.add("Evoke");

            this.orbId = com.megacrit.cardcrawl.orbs.Plasma.ORB_ID;
        }
    }
}
