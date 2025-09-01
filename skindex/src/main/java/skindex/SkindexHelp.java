package skindex;

import skindex.patches.NeowWardrobePatches;

public class SkindexHelp {
    public static void resetMod(){
        NeowWardrobePatches.skinController = null;
        NeowWardrobePatches.wardrobe = null;
    }
}
