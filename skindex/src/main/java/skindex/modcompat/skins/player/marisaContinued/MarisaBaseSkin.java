package skindex.modcompat.skins.player.marisaContinued;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dLib.util.Reflection;
import marisa.patches.ThModClassEnum;
import skindex.skins.entity.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class MarisaBaseSkin extends PlayerAtlasSkin {
    public static AbstractPlayer.PlayerClass MARISA_ENUM = Reflection.getFieldValue("MARISA", ThModClassEnum.class);

    /** Constructors */
    public MarisaBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            atlasUrl = "marisa/img/char/Marisa/MarisaModelv3.atlas";
            skeletonUrl = "marisa/img/char/Marisa/MarisaModelv3.json";
            resourceDirectoryUrl = "marisa/img/char/Marisa/";

            id = ID;
            name = "Base";
            invertedSkeletonScale = 2f;

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = MARISA_ENUM.name();

            isDefault = true;
        }
    }
}
