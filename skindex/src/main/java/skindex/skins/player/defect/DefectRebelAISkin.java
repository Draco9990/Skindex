package skindex.skins.player.defect;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Melter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.SphericGuardian;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import skindex.registering.SkindexRegistry;
import skindex.skins.orb.rebelai.DarkOrbRebelAISkin;
import skindex.skins.orb.rebelai.FrostOrbRebelAISkin;
import skindex.skins.orb.rebelai.LightningOrbRebelAISkin;
import skindex.skins.orb.rebelai.PlasmaOrbRebelAISkin;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.trackers.SkindexDefaultTracker;
import skindex.unlockmethods.AchievementUnlockMethod;

public class DefectRebelAISkin extends PlayerAtlasSkin {
    /** Constructors */
    public DefectRebelAISkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "REBEL_AI";
        public SkinData(){
            atlasUrl = "images/characters/defect/idle/skeleton.atlas";
            skeletonUrl = "images/characters/defect/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/rebel_ai/";

            id = ID;
            name = "Rebel AI";

            orbSkins.put(Lightning.ORB_ID, LightningOrbRebelAISkin.SkinData.skinID);
            orbSkins.put(Frost.ORB_ID, FrostOrbRebelAISkin.SkinData.skinID);
            orbSkins.put(Dark.ORB_ID, DarkOrbRebelAISkin.SkinData.skinID);
            orbSkins.put(Plasma.ORB_ID, PlasmaOrbRebelAISkin.SkinData.skinID);

            unlockMethod = AchievementUnlockMethod.methodId;
            tracker = SkindexDefaultTracker.get().getId();
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }

    /** Patches */
    public static class Patches{
        public static class UnlockPatches{
            @SpirePatch2(clz = SphericGuardian.class, method = "<class>")
            public static class SphericGuardianDamagedTracker{
                public static SpireField<Boolean> wasDamaged = new SpireField<>(() -> false);
            }

            @SpirePatch2(clz = SphericGuardian.class, method = "damage")
            public static class UnlockSkinAchievement{
                public static void Postfix(SphericGuardian __instance){
                    if(__instance.isDying && !SphericGuardianDamagedTracker.wasDamaged.get(__instance)){
                        if(!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()){
                            AbstractCard lastCardUsed = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
                            if(lastCardUsed instanceof Melter){
                                SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.DEFECT, SkinData.ID).unlock();
                            }
                        }
                    }
                    else{
                        SphericGuardianDamagedTracker.wasDamaged.set(__instance, true);
                    }
                }
            }
        }
    }
}