package skindex.modcompat.duelistmod.skins.player;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import duelistmod.DuelistMod;
import duelistmod.patches.TheDuelistEnum;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class DuelistAnimatedKaibaSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DuelistAnimatedKaibaSkin() {
        super(new SkinData());
    }

    @Override
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        this.defaultTimeScale = DuelistMod.persistentDuelistData.VisualSettings.getAnimationSpeed();
        return super.loadOnEntity(entity);
    }

    @Override
    public boolean loadOnPlayer(AbstractPlayer player) {
        this.defaultTimeScale = DuelistMod.persistentDuelistData.VisualSettings.getAnimationSpeed();
        return super.loadOnPlayer(player);
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "ANIMATEDKAIBA";
        public SkinData(){
            atlasUrl = "duelistModResources/images/char/duelistCharacter/Spine/kaiba/nyoxide_seto akiba.atlas";
            skeletonUrl = "duelistModResources/images/char/duelistCharacter/Spine/kaiba/nyoxide_seto akiba.json";
            resourceDirectoryUrl = "duelistModResources/images/char/duelistCharacter/Spine/kaiba";

            id = ID;
            name = "Animated Kaiba";

            defaultAnimName = "idle";
            invertedSkeletonScale = 9.5f;

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheDuelistEnum.THE_DUELIST.toString();
        }
    }
}
