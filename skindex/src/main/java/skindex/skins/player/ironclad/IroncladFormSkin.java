package skindex.skins.player.ironclad;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;

public class IroncladFormSkin extends PlayerAtlasSkin {

    public IroncladFormSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "FORM";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/form/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/form/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/form/";

            id = ID;
            name = "Demon Form";

            icon = "skindexResources/images/skins/player/ironclad/form/icon.png";

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
