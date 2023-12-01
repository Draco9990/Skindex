package skindex.modcompat.packmaster.skins.player;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dLib.util.Reflection;
import marisa.patches.ThModClassEnum;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.FreeUnlockMethod;
import thePackmaster.ThePackmaster;

public class PackmasterBaseSkin extends PlayerAtlasSkin {
    /** Constructors */
    public PackmasterBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/PackmasterAnim.atlas";
            skeletonUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/PackmasterAnim.json";
            resourceDirectoryUrl = "anniv5Resources/images/char/mainChar/skins/packmaster/";

            id = ID;
            name = "Packmaster";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = ThePackmaster.Enums.THE_PACKMASTER.name();
        }
    }
}
