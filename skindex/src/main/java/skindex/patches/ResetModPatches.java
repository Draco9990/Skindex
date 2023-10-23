package skindex.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.SystemStats;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.ui.panels.SeedPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dLib.util.Reflection;
import skindex.SkindexHelp;

public class ResetModPatches {
    @SpirePatch2(clz = CardCrawlGame.class, method = "updateFade")
    public static class ResetModPatch{
        public static void Prefix(CardCrawlGame __instance){
            if (CardCrawlGame.screenTimer != 0.0F) {
                if (!((boolean)Reflection.getFieldValue("fadeIn", __instance))) {
                    if (CardCrawlGame.startOver && CardCrawlGame.screenTimer - Gdx.graphics.getDeltaTime() <= 0.0F) {
                        SkindexHelp.resetMod();
                    }
                }
            }
        }
    }
}
