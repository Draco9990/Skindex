package skindex.skins.player.ironclad;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Disarm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.SlaverBlue;
import com.megacrit.cardcrawl.relics.SlaversCollar;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

import java.util.Objects;

public class IroncladSlaverBlueSkin extends PlayerAtlasSkin {
    public IroncladSlaverBlueSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "SLAVER_BLUE";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/slaver_blue/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/slaver_blue/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/slaver_blue/";

            id = ID;
            name = "Casal Slaver";

            icon = "skindexResources/images/skins/player/ironclad/slaver_blue/icon.png";

            unlockDescription = "This skin is unlocked by playing Disarm with Slaver's Collar on a Blue Slaver after killing the Red Slaver and the Taskmaster";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }

    public static class UnlockPatches{
        @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
        public static class DisarmCheck{
            @SpirePostfixPatch
            public static void disarmChecker(AbstractCard c, AbstractMonster monster){
                if(AbstractDungeon.player.hasRelic(SlaversCollar.ID)){
                    if(Objects.equals(AbstractDungeon.lastCombatMetricKey, "Slavers")){
                        if(c instanceof Disarm && monster instanceof SlaverBlue){
                            for(AbstractMonster m : AbstractDungeon.getMonsters().monsters){
                                if(!(m instanceof SlaverBlue) && m.currentHealth > 0){
                                    return;
                                }
                            }

                            unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.IRONCLAD);
                        }
                    }
                }
            }
        }
    }
}
