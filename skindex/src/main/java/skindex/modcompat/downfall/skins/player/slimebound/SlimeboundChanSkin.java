package skindex.modcompat.downfall.skins.player.slimebound;

import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.SlimeBoundSkin;
import reskinContent.skinCharacter.skins.Slimebound.Slaifu;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import slimebound.patches.SlimeboundEnum;

public class SlimeboundChanSkin extends DownfallSkinWrapper {
    /** Constructors */
    public SlimeboundChanSkin() {
        super(new DownfallSkinWrapperData(new Slaifu()));

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
