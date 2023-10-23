package skindex.modcompat.downfall.skins.player.guardian;

import guardian.patches.GuardianEnum;
import reskinContent.skinCharacter.skins.Guardian.GuardianOriginal;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class GuardianBaseSkin extends DownfallSkinWrapper {
    /** Constructors */
    public GuardianBaseSkin() {
        super(new DownfallSkinWrapperData(new GuardianOriginal()));

        playerClass = GuardianEnum.GUARDIAN;
        defaultAnimName = "idle";
        isOriginal = true;
    }
}
