package skindex.modcompat.downfall.skins.player.hexaghost;

import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.HexaghostSkin;
import reskinContent.skinCharacter.skins.Hexaghost.Hexago;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import theHexaghost.TheHexaghost;

public class HexaghostChanSkin extends HexaghostAtlasSkin {
    /** Constructors */
    public HexaghostChanSkin() {
        super(new DownfallSkinWrapper.DownfallSkinWrapperData(new Hexago()));

        playerClass = TheHexaghost.Enums.THE_SPIRIT;
        defaultAnimName = "idle2";
        shadow = null;
    }

    /** Misc Methods */
    @Override
    public boolean hasUnlocked() {
        for(AbstractSkinCharacter character : CharacterSelectScreenPatches.characters){
            if(character instanceof HexaghostSkin){
                return character.reskinUnlock;
            }
        }

        return super.hasUnlocked();
    }
}
