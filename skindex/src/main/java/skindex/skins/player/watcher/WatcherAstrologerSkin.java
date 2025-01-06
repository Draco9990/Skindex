package skindex.skins.player.watcher;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import dLib.util.Reflection;
import skindex.unlockmethods.AchievementUnlockMethod;

public class WatcherAstrologerSkin extends WatcherAtlasSkin {
    private static int SCORE_TRESHOLD = 3500;

    public WatcherAstrologerSkin() {
        super(new SkinData());
    }

    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "ASTROLOGER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/watcher/astrologer/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/watcher/astrologer/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/watcher/astrologer/";

            id = ID;
            name = "Spirit of the Dragon";

            icon = "skindexResources/images/skins/player/watcher/astrologer/icon.png";

            unlockDescription = "This skin is unlocked by beating A20 with a score of " + SCORE_TRESHOLD + " or higher as the Watcher.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }

    @SpirePatch2(clz = GameOverScreen.class, method = "calculateUnlockProgress")
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(GameOverScreen __instance){
            if(AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.WATCHER){
                if(GameOverScreen.isVictory && CardCrawlGame.dungeon instanceof TheEnding){
                    int score = Reflection.getFieldValue("score", __instance);

                    if(score > SCORE_TRESHOLD){
                        unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.WATCHER);
                    }
                }
            }
        }
    }
}
