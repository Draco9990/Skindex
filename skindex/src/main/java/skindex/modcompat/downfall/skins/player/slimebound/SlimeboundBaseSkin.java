package skindex.modcompat.downfall.skins.player.slimebound;

import reskinContent.skinCharacter.skins.Slimebound.SlimeBoundOriginal;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import slimebound.patches.SlimeboundEnum;

public class SlimeboundBaseSkin extends DownfallSkinWrapper {
    /** Constructors */
    public SlimeboundBaseSkin() {
        super(new DownfallSkinWrapperData(new SlimeBoundOriginal()));

        playerClass = SlimeboundEnum.SLIMEBOUND;

        defaultAnimName = "idle";
        isOriginal = true;

        isDefault = true;
    }
}
