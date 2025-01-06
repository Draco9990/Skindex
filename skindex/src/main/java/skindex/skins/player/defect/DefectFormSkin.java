package skindex.skins.player.defect;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.EchoForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import com.megacrit.cardcrawl.powers.EchoPower;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

public class DefectFormSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DefectFormSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "FORM_DEFECT";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/defect/form/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/defect/form/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/form/";

            id = ID;
            name = "One True Form";

            icon = "skindexResources/images/skins/player/defect/form/icon.png";

            this.unlockDescription = "This skin is unlocked by Echo Forming an Echo Form while under the effects of a Duplication Potion.";
            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();

            credits.add("YawgmothHS");
        }
    }

    /** Patches */
    public static class Patches{
        public static class UnlockPatches{
            @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
            public static class UnlockSkinAchievement{
                public static void Postfix(AbstractCard c){
                    if(c instanceof EchoForm){
                        if(AbstractDungeon.player.hasPower(EchoPower.POWER_ID) && AbstractDungeon.player.hasPower(DuplicationPower.POWER_ID)){
                            SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.DEFECT, SkinData.ID).unlock();
                        }
                    }
                }
            }
        }
    }
}
