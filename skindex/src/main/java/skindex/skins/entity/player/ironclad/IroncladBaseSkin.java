package skindex.skins.entity.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class IroncladBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public IroncladBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "images/characters/ironclad/idle/skeleton.atlas";
            skeletonUrl = "images/characters/ironclad/idle/skeleton.json";
            resourceDirectoryUrl = "images/characters/ironclad/idle/";

            id = ID;
            name = "Base";

            icon = "skindexResources/images/skins/player/ironclad/base/icon.png";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();

            isDefault = true;
        }
    }
}
