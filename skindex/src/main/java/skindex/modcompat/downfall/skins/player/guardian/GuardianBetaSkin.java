package skindex.modcompat.downfall.skins.player.guardian;

import guardian.patches.GuardianEnum;
import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.GuardianSkin;
import reskinContent.skinCharacter.skins.Guardian.BetaGuardian;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class GuardianBetaSkin extends DownfallSkinWrapper {
    /** Constructors */
    public GuardianBetaSkin() {
        super(new DownfallSkinWrapperData(new BetaGuardian()));

        playerClass = GuardianEnum.GUARDIAN;
        defaultAnimName = "idle";
    }

    /** Misc Methods */
    @Override
    public boolean hasUnlocked() {
        for(AbstractSkinCharacter character : CharacterSelectScreenPatches.characters){
            if(character instanceof GuardianSkin){
                return character.reskinUnlock;
            }
        }

        return super.hasUnlocked();
    }
}
