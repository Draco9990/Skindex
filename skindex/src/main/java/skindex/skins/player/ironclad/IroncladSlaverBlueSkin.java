package skindex.skins.player.ironclad;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import dLib.util.Reflection;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;

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
            name = "Slaver (Blue)";

            icon = "skindexResources/images/skins/player/ironclad/slaver_blue/icon.png";

            //unlockDescription = "This skin is unlocked by beating A20 with a score of " + SCORE_TRESHOLD + " or higher.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
