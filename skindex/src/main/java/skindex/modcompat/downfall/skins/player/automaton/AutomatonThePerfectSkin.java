package skindex.modcompat.downfall.skins.player.automaton;

import automaton.AutomatonChar;
import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.AutomatonSkin;
import reskinContent.skinCharacter.skins.Automaton.ThePerfect;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class AutomatonThePerfectSkin extends DownfallSkinWrapper {
    /** Constructors */
    public AutomatonThePerfectSkin() {
        super(new DownfallSkinWrapperData(new ThePerfect()));

        defaultAnimName = "idle";
        playerClass = AutomatonChar.Enums.THE_AUTOMATON;
    }

    /** Misc Methods */
    @Override
    public boolean hasUnlocked() {
        for(AbstractSkinCharacter character : CharacterSelectScreenPatches.characters){
            if(character instanceof AutomatonSkin){
                return character.reskinUnlock;
            }
        }

        return super.hasUnlocked();
    }
}
