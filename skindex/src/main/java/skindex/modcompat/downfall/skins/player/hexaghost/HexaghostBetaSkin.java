package skindex.modcompat.downfall.skins.player.hexaghost;

import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.HexaghostSkin;
import reskinContent.skinCharacter.skins.Hexaghost.BetaHexaghost;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import theHexaghost.TheHexaghost;

public class HexaghostBetaSkin extends HexaghostAtlasSkin {
    /** Constructors */
    public HexaghostBetaSkin() {
        super(new DownfallSkinWrapper.DownfallSkinWrapperData(new BetaHexaghost()));

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
