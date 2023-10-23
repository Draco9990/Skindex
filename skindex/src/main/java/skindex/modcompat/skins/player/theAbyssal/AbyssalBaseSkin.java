package skindex.modcompat.skins.player.theAbyssal;

import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;
import thePirate.characters.ThePirate;

public class AbyssalBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public AbyssalBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "thePirateResources/images/char/defaultCharacter/skeleton.atlas";
            skeletonUrl = "thePirateResources/images/char/defaultCharacter/skeleton.json";
            resourceDirectoryUrl = "thePirateResources/images/char/defaultCharacter/";

            id = ID;
            name = "Base";

            defaultAnimName = "animtion0";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = ThePirate.Enums.THE_PIRATE.name();
        }
    }
}
