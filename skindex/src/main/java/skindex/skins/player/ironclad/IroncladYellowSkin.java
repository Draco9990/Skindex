package skindex.skins.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class IroncladYellowSkin extends PlayerAtlasSkin {
    /** Constructors */
    public IroncladYellowSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "YELLOW";
        public SkinData(){
            atlasUrl = "images/characters/ironclad/idle/skeleton.atlas";
            skeletonUrl = "images/characters/ironclad/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/yellow/";

            id = ID;
            name = "Yellow";

            icon = "skindexResources/images/skins/player/ironclad/yellow/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
