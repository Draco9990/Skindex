package skindex.modcompat.duelistmod.skins.player;

import duelistmod.patches.TheDuelistEnum;
import skindex.skins.player.PlayerSpriterSkin;
import skindex.skins.player.PlayerSpriterSkinData;
import skindex.unlockmethods.FreeUnlockMethod;

public class DuelistClassicYugiSkin extends PlayerSpriterSkin {
    /** Constructors */
    public DuelistClassicYugiSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerSpriterSkinData {
        public static String ID = "CLASSIC";
        public SkinData(){
            scmlUrl = "duelistModResources/images/char/duelistCharacter/theDuelistAnimation.scml";

            id = ID;
            name = "Classic Yugi";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheDuelistEnum.THE_DUELIST.toString();
        }
    }
}
