package skindex.skins.player.defect;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Melter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.monsters.city.SphericGuardian;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import dLib.util.Reflection;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.skins.player.ironclad.IroncladAstrologerSkin;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;

public class DefectAstrologerSkin extends PlayerAtlasSkin {
    private static int SCORE_TRESHOLD = 3500;

    /** Constructors */
    public DefectAstrologerSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "ASTROLOGER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/defect/astrologer/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/defect/astrologer/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/astrologer/";

            id = ID;
            name = "Spirit of the Rat";

            icon = "skindexResources/images/skins/player/defect/astrologer/icon.png";

            unlockDescription = "This skin is unlocked by beating A20 with a score of " + SCORE_TRESHOLD + " or higher as the Defect.";

            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }

    @SpirePatch2(clz = GameOverScreen.class, method = "calculateUnlockProgress")
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(GameOverScreen __instance){
            if(AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.DEFECT){
                if(GameOverScreen.isVictory && CardCrawlGame.dungeon instanceof TheEnding){
                    int score = Reflection.getFieldValue("score", __instance);

                    if(score > SCORE_TRESHOLD){
                        unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.DEFECT);
                    }
                }
            }
        }
    }
}
