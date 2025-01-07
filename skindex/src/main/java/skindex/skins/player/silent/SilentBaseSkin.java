package skindex.skins.player.silent;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class SilentBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public SilentBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "images/characters/theSilent/idle/skeleton.atlas";
            skeletonUrl = "images/characters/theSilent/idle/skeleton.json";
            resourceDirectoryUrl = "images/characters/theSilent/idle/";

            id = ID;
            name = "Base";

            icon = "skindexResources/images/skins/player/silent/base/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();

            isDefault = true;
        }
    }
}
