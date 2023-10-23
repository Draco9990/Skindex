package skindex.modcompat.downfall.skins.player.automaton;

import automaton.AutomatonChar;
import reskinContent.patches.CharacterSelectScreenPatches;
import reskinContent.skinCharacter.AbstractSkinCharacter;
import reskinContent.skinCharacter.AutomatonSkin;
import reskinContent.skinCharacter.skins.Automaton.BetaAutomaton;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class AutomatonBetaSkin extends DownfallSkinWrapper {
    /** Constructors */
    public AutomatonBetaSkin() {
        super(new DownfallSkinWrapperData(new BetaAutomaton()));

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
