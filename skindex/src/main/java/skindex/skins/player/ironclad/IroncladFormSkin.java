package skindex.skins.player.ironclad;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.unique.LimitBreakAction;
import com.megacrit.cardcrawl.cards.red.LimitBreak;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DemonFormPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;

public class IroncladFormSkin extends PlayerAtlasSkin {

    public IroncladFormSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "FORM";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/form/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/form/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/form/";

            id = ID;
            name = "One True Form";

            icon = "skindexResources/images/skins/player/ironclad/form/icon.png";

            unlockDescription = "This skin is unlocked by playing Limit Break while having Demon Form applied and with more than 666 Strength.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();

            credits.add("YawgmothHS");
        }
    }

    @SpirePatch2(clz = LimitBreakAction.class, method = SpirePatch.CONSTRUCTOR)
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(){
            if(AbstractDungeon.player.hasPower(DemonFormPower.POWER_ID) && AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
                if(AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount >= 666){
                    unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.IRONCLAD);
                }
            }
        }
    }
}
