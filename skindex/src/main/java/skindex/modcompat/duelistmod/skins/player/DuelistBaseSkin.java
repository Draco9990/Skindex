package skindex.modcompat.duelistmod.skins.player;

import duelistmod.patches.TheDuelistEnum;
import skindex.skins.player.PlayerSpriterSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class DuelistBaseSkin extends PlayerSpriterSkin {
    /** Constructors */
    public DuelistBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerSpriterSkinData {
        public static String ID = "BASE";
        public SkinData(){
            scmlUrl = "duelistModResources/images/char/duelistCharacterUpdate/YugiB.scml";

            id = ID;
            name = "Yugi";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheDuelistEnum.THE_DUELIST.toString();

            isDefault = true;
        }
    }
}
