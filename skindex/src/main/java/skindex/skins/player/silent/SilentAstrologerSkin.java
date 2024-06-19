package skindex.skins.player.silent;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import dLib.util.Reflection;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;

public class SilentAstrologerSkin extends PlayerAtlasSkin {
    private static int SCORE_TRESHOLD = 3500;

    public SilentAstrologerSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "ASTROLOGER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/silent/astrologer/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/silent/astrologer/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/astrologer/";

            id = ID;
            name = "Spirit of the Snake";

            icon = "skindexResources/images/skins/player/silent/astrologer/icon.png";

            unlockDescription = "This skin is unlocked by beating A20 with a score of " + SCORE_TRESHOLD + " or higher as the Silent.";
            unlockMethod = FreeUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }

    @SpirePatch2(clz = GameOverScreen.class, method = "calculateUnlockProgress")
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(GameOverScreen __instance){
            if(AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT){
                if(GameOverScreen.isVictory && CardCrawlGame.dungeon instanceof TheEnding){
                    int score = Reflection.getFieldValue("score", __instance);

                    if(score > SCORE_TRESHOLD){
                        unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.THE_SILENT);
                    }
                }
            }
        }
    }
}
