package skindex.modcompat.downfall.skins.player.snecko;

import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.SneckoSkin;
import reskinContent.skinCharacter.skins.Snecko.SSSSnecko;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import sneckomod.TheSnecko;

public class SneckoChanSkin extends DownfallSkinWrapper {
    /** Constructors */
    public SneckoChanSkin() {
        super(new DownfallSkinWrapperData(new SSSSnecko()));

        playerClass = TheSnecko.Enums.THE_SNECKO;
    }

    /** Misc Methods */
    @Override
    public boolean hasUnlocked() {
        for(AbstractSkinCharacter character : CharacterSelectScreenPatches.characters){
            if(character instanceof SneckoSkin){
                return character.reskinUnlock;
            }
        }

        return super.hasUnlocked();
    }
}
