package skindex.skins.player.silent;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class SilentRedSkin extends PlayerAtlasSkin {
    /** Constructors */
    public SilentRedSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "RED";
        public SkinData(){
            atlasUrl = "images/characters/theSilent/idle/skeleton.atlas";
            skeletonUrl = "images/characters/theSilent/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/red/";

            shoulderIMG = "spireTogetherResources/images/charSkins/Silent/red/shoulder.png";
            shoulder2IMG = "spireTogetherResources/images/charSkins/Silent/red/shoulder2.png";
            corpseIMG = "spireTogetherResources/images/charSkins/Silent/red/corpse.png";

            id = ID;
            name = "Red";

            icon = "skindexResources/images/skins/player/silent/red/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }
}
