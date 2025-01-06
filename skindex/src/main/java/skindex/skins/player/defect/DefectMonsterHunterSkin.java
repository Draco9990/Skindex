package skindex.skins.player.defect;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Chaos;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skindex.registering.SkindexRegistry;
import skindex.skins.orb.monsterhunter.MonsterHunterOrbSkinSet;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

public class DefectMonsterHunterSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DefectMonsterHunterSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "MONSTER_HUNTER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/defect/monsterhunter/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/defect/monsterhunter/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/monsterhunter/";

            id = ID;
            name = "Monster Hunter";

            icon = "skindexResources/images/skins/player/defect/monsterhunter/icon.png";

            orbSkins.addAll(MonsterHunterOrbSkinSet.collectOrbSkinIds());

            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }

    /** Patches */
    public static class Patches{
        public static class UnlockPatches{
            @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
            public static class UnlockSkinAchievement{
                public static void Postfix(AbstractCard c){
                    if(c instanceof Chaos){
                        if(AbstractDungeon.player.maxOrbs == 0){
                            SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.DEFECT, SkinData.ID).unlock();
                        }
                    }
                }
            }
        }
    }
}
