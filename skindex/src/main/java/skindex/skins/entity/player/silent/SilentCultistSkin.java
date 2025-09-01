package skindex.skins.entity.player.silent;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.powers.RitualPower;
import skindex.registering.SkindexRegistry;
import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

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

            icon = "skindexResources/images/skins/player/silent/cultist/icon.png";

            this.unlockDescription = "This skin is unlocked by killing a Cultist with Ritual Dagger while having 1 or more Ritual applied to you.";
            unlockMethod = AchievementUnlockMethod.methodId;

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
