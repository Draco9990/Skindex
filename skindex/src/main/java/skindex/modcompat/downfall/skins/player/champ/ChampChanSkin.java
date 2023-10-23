package skindex.modcompat.downfall.skins.player.champ;

import champ.ChampChar;
import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.ChampSkin;
import reskinContent.skinCharacter.skins.Champ.ChampSister;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class ChampChanSkin extends DownfallSkinWrapper {
    /** Constructors */
    public ChampChanSkin() {
        super(new DownfallSkinWrapperData(new ChampSister()));

        playerClass = ChampChar.Enums.THE_CHAMP;
    }

    /** Misc Methods */
    @Override
    public boolean hasUnlocked() {
        for(AbstractSkinCharacter character : CharacterSelectScreenPatches.characters){
            if(character instanceof ChampSkin){
                return character.reskinUnlock;
            }
        }

        return super.hasUnlocked();
    }
}
