package skindex.modcompat.downfall.skins.player.champ;

import champ.ChampChar;
import reskinContent.skinCharacter.skins.Champ.ChampOriginal;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class ChampBaseSkin extends DownfallSkinWrapper {
    /** Constructors */
    public ChampBaseSkin() {
        super(new DownfallSkinWrapperData(new ChampOriginal()));

        playerClass = ChampChar.Enums.THE_CHAMP;
        isOriginal = true;
    }
}
