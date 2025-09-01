package skindex.skins.entity.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class IroncladGreenSkin extends PlayerAtlasSkin {
    /** Constructors */
    public IroncladGreenSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "GREEN";
        public SkinData(){
            atlasUrl = "images/characters/ironclad/idle/skeleton.atlas";
            skeletonUrl = "images/characters/ironclad/idle/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/green/";

            id = ID;
            name = "Green";

            icon = "skindexResources/images/skins/player/ironclad/green/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
