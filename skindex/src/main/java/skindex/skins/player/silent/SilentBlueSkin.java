package skindex.skins.player.silent;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
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

            shoulderIMG = "spireTogetherResources/images/charSkins/Silent/blue/shoulder.png";
            shoulder2IMG = "spireTogetherResources/images/charSkins/Silent/blue/shoulder2.png";
            corpseIMG = "spireTogetherResources/images/charSkins/Silent/blue/corpse.png";

            id = ID;
            name = "Blue";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }
}
