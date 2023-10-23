package skindex.skins.player.silent;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class SilentYellowSkin extends PlayerAtlasSkin {
    /** Constructors */
    public SilentYellowSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "YELLOW";
        public SkinData(){
            atlasUrl = "images/characters/theSilent/idle/skeleton.atlas";
            skeletonUrl = "images/characters/theSilent/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/yellow/";

            shoulderIMG = "spireTogetherResources/images/charSkins/Silent/yellow/shoulder.png";
            shoulder2IMG = "spireTogetherResources/images/charSkins/Silent/yellow/shoulder2.png";
            corpseIMG = "spireTogetherResources/images/charSkins/Silent/yellow/corpse.png";

            id = ID;
            name = "Yellow";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }
}
