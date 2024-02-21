package skindex.skins.player.silent;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.powers.RitualPower;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.trackers.SkindexDefaultTracker;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.NonUnlockableUnlockMethod;

import java.util.ArrayList;

public class SilentCultistSkin extends PlayerAtlasSkin {
    /** Constructors */
    public SilentCultistSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "CULTIST";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/silent/cultist/cultist.atlas";
            skeletonUrl = "skindexResources/images/skins/player/silent/cultist/cultist.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/cultist/";

            id = ID;
            name = "Cultist";

            unlockMethod = AchievementUnlockMethod.methodId;
            tracker = SkindexDefaultTracker.get().getId();

            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = AbstractMonster.class, method = "damage")
        public static class UnlockSkinPatch{
            public static void Postfix(AbstractMonster __instance){
                if(__instance instanceof Cultist){
                    if(__instance.isDying || __instance.isDead){
                        ArrayList<AbstractCard> cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn;
                        if(!cardsPlayed.isEmpty()){
                            if(cardsPlayed.get(cardsPlayed.size() - 1) instanceof RitualDagger){
                                if(AbstractDungeon.player != null && AbstractDungeon.player.hasPower(RitualPower.POWER_ID)){
                                    SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.THE_SILENT, SkinData.ID).unlock();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}