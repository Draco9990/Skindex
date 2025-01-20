package skindex.modcompat.downfall.skins.player.hexaghost;

import reskinContent.skinCharacter.skins.Hexaghost.HexaghostOriginal;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import theHexaghost.TheHexaghost;

public class HexaghostBaseSkin extends HexaghostAtlasSkin {
    /** Constructors */
    public HexaghostBaseSkin() {
        super(new DownfallSkinWrapper.DownfallSkinWrapperData(new HexaghostOriginal()));

        playerClass = TheHexaghost.Enums.THE_SPIRIT;
        defaultAnimName = "idle2";
        isOriginal = true;

        isDefault = true;
    }
}
