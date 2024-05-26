package skindex.skins.player.ironclad;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;
import dLib.util.Reflection;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;

public class IroncladCyberSkin extends PlayerAtlasSkin {

    public IroncladCyberSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "CYBER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/mech/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/mech/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/mech/";

            id = ID;
            name = "Cyber-ops";

            icon = "skindexResources/images/skins/player/ironclad/mech/icon.png";

            unlockDescription = "This skin is unlocked by healing back to full HP with Reaper while on 1 HP.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }

    @SpirePatch2(clz = VampireDamageAllEnemiesAction.class, method = "update")
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(VampireDamageAllEnemiesAction __instance){
            if(AbstractDungeon.player instanceof Ironclad){
                if(__instance.isDone){
                    if(AbstractDungeon.player.currentHealth == 1){
                        int healAmount = 0;

                        for(int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                            if (!target.isDead && !target.escaped) {
                                if (target.lastDamageTaken > 0) {
                                    healAmount += target.lastDamageTaken;
                                }
                            }
                        }

                        if(healAmount >= AbstractDungeon.player.maxHealth - 1){
                            unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.IRONCLAD);
                        }
                    }
                }
            }
        }
    }
}
