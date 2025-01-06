package skindex.skins.player.silent;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.WraithForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

public class SilentFormSkin extends PlayerAtlasSkin {

    public SilentFormSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "FORM";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/silent/form/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/silent/form/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/form/";

            id = ID;
            name = "One True Form";

            icon = "skindexResources/images/skins/player/silent/form/icon.png";

            unlockDescription = "This skin is unlocked by playing Wraith Form when you'd take lethal damage from all enemies this turn.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();

            credits.add("YawgmothHS");
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
    public static class UnlockSkinAchievement{
        public static void Postfix(AbstractCard c){
            if(c instanceof WraithForm){
                int damage = 0;
                for(AbstractMonster m : AbstractDungeon.getMonsters().monsters){
                    for(DamageInfo dmg : m.damage){
                        DamageInfo copy = new DamageInfo(dmg.owner, dmg.base, dmg.type);
                        copy.applyPowers(AbstractDungeon.player, m);
                        damage += copy.output;
                    }
                }

                if(damage > AbstractDungeon.player.currentBlock + AbstractDungeon.player.currentHealth + TempHPField.tempHp.get(AbstractDungeon.player)){
                    unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.THE_SILENT);
                }
            }
        }
    }
}
