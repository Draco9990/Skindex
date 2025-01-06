package skindex.modcompat.downfall.patches;

import automaton.AutomatonChar;
import champ.ChampChar;
import collector.CollectorChar;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.modcompat.ModManager;
import guardian.characters.GuardianCharacter;
import skindex.SkindexGame;
import skindex.skins.player.AbstractPlayerSkin;
import slimebound.characters.SlimeboundCharacter;
import sneckomod.TheSnecko;
import theHexaghost.TheHexaghost;

import java.util.Objects;

public class DownfallReloadAnimationPatches {
    @SpirePatch(clz = TheSnecko.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class SneckoPatch{
        public static SpireReturn Prefix(TheSnecko __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = SlimeboundCharacter.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class SlimeboundPatch{
        public static SpireReturn Prefix(SlimeboundCharacter __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = TheHexaghost.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class HexaghostPatch{
        public static SpireReturn Prefix(TheHexaghost __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = GuardianCharacter.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class GuardianPatch{
        public static SpireReturn Prefix(GuardianCharacter __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = CollectorChar.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class CollectorPatch{
        public static SpireReturn Prefix(CollectorChar __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = ChampChar.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class ChampPatch{
        public static SpireReturn Prefix(ChampChar __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AutomatonChar.class, method = "reloadAnimation", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class AutomatonPatch{
        public static SpireReturn Prefix(AutomatonChar __instance){
            if(Objects.equals(__instance, AbstractDungeon.player)){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    currentSkin.loadOnPlayer();
                    return SpireReturn.Return();
                }
            }

            return SpireReturn.Continue();
        }
    }
}