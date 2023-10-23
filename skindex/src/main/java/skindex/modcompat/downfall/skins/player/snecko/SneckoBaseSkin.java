package skindex.modcompat.downfall.skins.player.snecko;

import reskinContent.skinCharacter.skins.Snecko.SneckoOriginal;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import sneckomod.TheSnecko;

public class SneckoBaseSkin extends DownfallSkinWrapper {
    /** Constructors */
    public SneckoBaseSkin() {
        super(new DownfallSkinWrapperData(new SneckoOriginal()));

        playerClass = TheSnecko.Enums.THE_SNECKO;
        isOriginal = true;
    }
}
