package skindex.skins.player.ironclad;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.PlayerAtlasSkinData;
import skindex.unlockmethods.AchievementUnlockMethod;

public class IroncladSlaverRedSkin extends PlayerAtlasSkin {
    public IroncladSlaverRedSkin() {
        super(new SkinData());
    }

    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "SLAVER_RED";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/ironclad/slaver_red/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/ironclad/slaver_red/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/ironclad/slaver_red/";

            id = ID;
            name = "Slaver (Red)";

            icon = "skindexResources/images/skins/player/ironclad/slaver_red/icon.png";

            //unlockDescription = "This skin is unlocked by beating A20 with a score of " + SCORE_TRESHOLD + " or higher.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();
        }
    }
}
