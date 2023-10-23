package skindex.modcompat.downfall.skins.player.hexaghost;

import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.HexaghostSkin;
import reskinContent.skinCharacter.skins.Hexaghost.Lampghost;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import theHexaghost.TheHexaghost;

public class HexaghostLampSkin extends HexaghostAtlasSkin {
    /** Constructors */
    public HexaghostLampSkin() {
        super(new DownfallSkinWrapper.DownfallSkinWrapperData(new Lampghost()));

        disablePlasma = true;
        playerClass = TheHexaghost.Enums.THE_SPIRIT;
        defaultAnimName = "idle2";
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
