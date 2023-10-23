package skindex.modcompat.downfall.skins.player.collector;

import collector.CollectorChar;
import skindex.unlockmethods.FreeUnlockMethod;

public class CollectorBaseSkin extends CollectorAtlasSkin {
    /** Constructors */
    public CollectorBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends CollectorAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "collectorResources/images/char/mainChar/skeleton.atlas";
            skeletonUrl = "collectorResources/images/char/mainChar/skeleton.json";
            resourceDirectoryUrl = "collectorResources/images/char/mainChar/";

            id = ID;
            name = "Base";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = CollectorChar.Enums.THE_COLLECTOR.name();
        }
    }
}