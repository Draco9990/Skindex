package skindex.modcompat.downfall.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import dLib.modcompat.ModManager;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import skindex.SkindexGame;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import skindex.modcompat.downfall.skins.player.hexaghost.HexaghostAtlasSkin;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.AbstractPlayerSkin;

public class DownfallSkinPatches {
    @SpirePatch2(clz = AbstractSkinCharacter.class, method = "isOriginal", requiredModId = ModManager.Downfall.modId, optional = true)
    public static class IsOriginalPatch{
        public static SpireReturn<Boolean> Prefix(){
            AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
            if(currentSkin == null) return SpireReturn.Return(true);
            else if(currentSkin instanceof DownfallSkinWrapper) return SpireReturn.Return(((DownfallSkinWrapper) currentSkin).isOriginal);
            else if(currentSkin instanceof HexaghostAtlasSkin) return SpireReturn.Return(((HexaghostAtlasSkin) currentSkin).isOriginal);
            else if(currentSkin instanceof PlayerAtlasSkin) return SpireReturn.Return(true);

            return SpireReturn.Continue();
        }
    }
}
