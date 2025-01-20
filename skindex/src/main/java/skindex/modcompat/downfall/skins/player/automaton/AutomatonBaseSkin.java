package skindex.modcompat.downfall.skins.player.automaton;

import automaton.AutomatonChar;
import reskinContent.skinCharacter.skins.Automaton.AutomationOriginal;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;

public class AutomatonBaseSkin extends DownfallSkinWrapper {
    /** Constructors */
    public AutomatonBaseSkin() {
        super(new DownfallSkinWrapperData(new AutomationOriginal()));

        defaultAnimName = "idle";
        playerClass = AutomatonChar.Enums.THE_AUTOMATON;
        isOriginal = true;

        isDefault = true;
    }
}
