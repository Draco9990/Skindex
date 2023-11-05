package skindex.screens;

import dLib.ui.screens.AbstractScreen;
import skindex.Skindex;

public class SkindexMainMenuScreen extends AbstractScreen {
    /** Variables */

    /** Constructors */
    public SkindexMainMenuScreen(){

    }

    @Override
    public String getModId() {
        return Skindex.getModID();
    }
}
