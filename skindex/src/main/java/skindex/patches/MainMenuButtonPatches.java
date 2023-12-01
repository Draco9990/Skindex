package skindex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import dLib.ui.screens.ScreenManager;
import dLib.util.Reflection;

import java.util.ArrayList;

public class MainMenuButtonPatches {

    @SpirePatch2(clz = MainMenuScreen.class, method = "setMainMenuButtons")
    public static class AddMultiplayerButton{
        public static void Postfix(MainMenuScreen __instance){
            ArrayList<MenuButton> buttons = (ArrayList<MenuButton>)__instance.buttons.clone();
            __instance.buttons.clear();
            int indx = 0;
            for(MenuButton b : buttons){
                if(b.result == MenuButton.ClickResult.INFO){
                    //__instance.buttons.add(new MenuButton(Enums.SKINDEX, indx++));
                }
                __instance.buttons.add(new MenuButton(b.result, indx++));
            }
        }
    }

    @SpirePatch(clz = MenuButton.class, method = "setLabel")
    public static class LabelPatcher{
        public static void Postfix(MenuButton __instance, String ___label){
            if(__instance.result == Enums.SKINDEX) Reflection.setFieldValue("label", __instance, "Skindex");
        }
    }

    //Patches functionality to new buttons
    @SpirePatch(clz = MenuButton.class, method = "buttonEffect")
    public static class EffectPatcher{
        public static SpireReturn Prefix(MenuButton __instance){
            if(__instance.result == Enums.SKINDEX){
                // TODO - EFFECT
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    public static class Enums {
        @SpireEnum
        public static MenuButton.ClickResult SKINDEX;
    }
}
