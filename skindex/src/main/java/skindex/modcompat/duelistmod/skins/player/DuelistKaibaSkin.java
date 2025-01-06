package skindex.modcompat.duelistmod.skins.player;

import duelistmod.patches.TheDuelistEnum;
import skindex.skins.player.PlayerSpriterSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class DuelistKaibaSkin extends PlayerSpriterSkin {
    /** Constructors */
    public DuelistKaibaSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerSpriterSkinData {
        public static String ID = "KAIBA";
        public SkinData(){
            scmlUrl = "duelistModResources/images/char/duelistCharacterUpdate/KaibaPlayer.scml";

            id = ID;
            name = "Kaiba";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheDuelistEnum.THE_DUELIST.toString();
        }
    }
}
