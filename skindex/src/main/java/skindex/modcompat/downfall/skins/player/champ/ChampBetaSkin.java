package skindex.modcompat.downfall.skins.player.champ;

import champ.ChampChar;
import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.ChampSkin;
import reskinContent.skinCharacter.skins.Champ.BetaChamp;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class ChampBetaSkin extends DownfallSkinWrapper {
    /** Constructors */
    public ChampBetaSkin() {
        super(new DownfallSkinWrapperData(new BetaChamp()));

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
