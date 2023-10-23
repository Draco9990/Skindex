package skindex.modcompat.downfall.skins.player.slimebound;

import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.SlimeBoundSkin;
import reskinContent.skinCharacter.skins.Slimebound.BetaSlimeBoss;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import slimebound.patches.SlimeboundEnum;

public class SlimeboundBetaSkin extends DownfallSkinWrapper {
    /** Constructors */
    public SlimeboundBetaSkin() {
        super(new DownfallSkinWrapperData(new BetaSlimeBoss()));

        playerClass = SlimeboundEnum.SLIMEBOUND;
        defaultAnimName = "idle";
    }

    /** Misc Methods */
    @Override
    public boolean hasUnlocked() {
        for(AbstractSkinCharacter character : CharacterSelectScreenPatches.characters){
            if(character instanceof SlimeBoundSkin){
                return character.reskinUnlock;
            }
        }

        return super.hasUnlocked();
    }
}
