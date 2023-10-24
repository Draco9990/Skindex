package skindex.saving;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import javassist.CtBehavior;
import skindex.SkindexGame;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerSkin;
import skindex.util.SkindexLogger;

import java.util.HashMap;

public class SkindexSaveData {
    private String SAVE_SKIN;
    private static String SAVE_SKIN_CACHE;
    private static AbstractPlayer.PlayerClass PLAYER_CLASS_CACHE;

    /** Saving */
    @SpirePatch(clz = SaveAndContinue.class, method = "save", paramtypez = {SaveFile.class})
    public static class SaveDataToFile {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"params"}
        )
        public static void Insert(SaveFile save, HashMap<Object, Object> params) {
            PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
            if(currentSkin != null){
                params.put("SAVE_SKIN", currentSkin.getId());
            }
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(GsonBuilder.class, "create");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    /** Loading */
    @SpirePatch(clz = SaveAndContinue.class, method = "loadSaveFile", paramtypez = {AbstractPlayer.PlayerClass.class})
    public static class ClassSaver{
        public static void Prefix(AbstractPlayer.PlayerClass c){
            PLAYER_CLASS_CACHE = c;
        }
    }

    @SpirePatch(clz = SaveAndContinue.class, method = "loadSaveFile", paramtypez = {String.class}
    )
    public static class LoadDataFromFile {
        @SpireInsertPatch(locator = Locator.class, localvars = {"gson", "savestr"})
        public static void loadCustomSaveData(String path, Gson gson, String savestr) {
            try {
                SkindexSaveData data = gson.fromJson(savestr, SkindexSaveData.class);
                SAVE_SKIN_CACHE = data.SAVE_SKIN;
            } catch (Exception var4) {
                SkindexLogger.log("Failed to load Skindex save data.");
                var4.printStackTrace();
            }

        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Gson.class, "fromJson");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(clz = MenuButton.class, method = "buttonEffect")
    public static class QueueSkinOnContinue{
        public static void Postfix(MenuButton __instance){
            if(__instance.result.equals(MenuButton.ClickResult.RESUME_GAME)){
                if(SAVE_SKIN_CACHE != null){
                    SkindexGame.queuePlayerSkin(SkindexRegistry.getPlayerSkinByClassAndId(PLAYER_CLASS_CACHE, SAVE_SKIN_CACHE, true));
                }
            }
        }
    }
}
