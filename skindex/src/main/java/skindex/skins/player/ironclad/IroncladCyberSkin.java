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

public class IroncladCyberSkin extends PlayerAtlasSkin {

    public IroncladCyberSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "CYBER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/mech/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/mech/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/mech/";

            id = ID;
            name = "Cyber-ops";

            icon = "skindexResources/images/skins/player/ironclad/mech/icon.png";

            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }

    @SpirePatch2(clz = GameOverScreen.class, method = "calculateUnlockProgress")
    public static class UnlockPatch{
        @SpirePostfixPatch
        public static void checkForUnlock(GameOverScreen __instance){}
    }
}
