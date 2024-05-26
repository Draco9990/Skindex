package skindex.skins.player.ironclad;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import dLib.util.Reflection;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.skins.player.silent.SilentCultistSkin;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;

public class IroncladAstrologerSkin extends PlayerAtlasSkin {
    private static int SCORE_TRESHOLD = 3500;

    public IroncladAstrologerSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "ASTROLOGER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/astrologer/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/astrologer/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/astrologer/";

            id = ID;
            name = "Spirit of the Goat";

            icon = "skindexResources/images/skins/player/ironclad/astrologer/icon.png";

            unlockDescription = "This skin is unlocked by beating A20 with a score of " + SCORE_TRESHOLD + " or higher.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }

    @SpirePatch2(clz = GameOverScreen.class, method = "calculateUnlockProgress")
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(GameOverScreen __instance){
            if(AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD){
                if(GameOverScreen.isVictory && CardCrawlGame.dungeon instanceof TheEnding){
                    int score = Reflection.getFieldValue("score", __instance);

                    if(score > SCORE_TRESHOLD){
                        unlockSkin(SkinData.ID, AbstractPlayer.PlayerClass.IRONCLAD);
                    }
                }
            }
        }
    }
}
