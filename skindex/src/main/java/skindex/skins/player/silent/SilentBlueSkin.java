package skindex.skins.player.silent;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class SilentBlueSkin extends PlayerAtlasSkin {
    /** Constructors */
    public SilentBlueSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BLUE";
        public SkinData(){
            atlasUrl = "images/characters/theSilent/idle/skeleton.atlas";
            skeletonUrl = "images/characters/theSilent/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/blue/";

            shoulderIMG = "skindexResources/images/skins/player/silent/blue/shoulder.png";
            shoulder2IMG = "skindexResources/images/skins/player/silent/blue/shoulder2.png";
            corpseIMG = "skindexResources/images/skins/player/silent/blue/corpse.png";

            id = ID;
            name = "Blue";

            icon = "skindexResources/images/skins/player/silent/blue/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }
}
