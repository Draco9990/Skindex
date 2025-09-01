package skindex.skins.entity.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class IroncladBlueSkin extends PlayerAtlasSkin {
    /** Constructors */
    public IroncladBlueSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BLUE";
        public SkinData(){
            atlasUrl = "images/characters/ironclad/idle/skeleton.atlas";
            skeletonUrl = "images/characters/ironclad/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/blue/";

            id = ID;
            name = "Blue";

            icon = "skindexResources/images/skins/player/ironclad/blue/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
