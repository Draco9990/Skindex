package skindex.modcompat.duelistmod.skins.player;

import duelistmod.DuelistMod;
import duelistmod.patches.TheDuelistEnum;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.FreeUnlockMethod;

public class DuelistAnimatedYugiSkin extends PlayerAtlasSkin {
    /** Constructors */
    public DuelistAnimatedYugiSkin() {
        super(new SkinData());
    }

    @Override
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        this.defaultTimeScale = DuelistMod.persistentDuelistData.VisualSettings.getAnimationSpeed();
        return super.loadOnEntity(entity);
    }

    @Override
    public boolean loadOnPlayer() {
        this.defaultTimeScale = DuelistMod.persistentDuelistData.VisualSettings.getAnimationSpeed();
        return super.loadOnPlayer();
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "ANIMATEDYUGI";
        public SkinData(){
            atlasUrl = "duelistModResources/images/char/duelistCharacter/Spine/yugi/nyoxide.atlas";
            skeletonUrl = "duelistModResources/images/char/duelistCharacter/Spine/yugi/nyoxide.json";
            resourceDirectoryUrl = "duelistModResources/images/char/duelistCharacter/Spine/yugi";

            id = ID;
            name = "Animated Yugi";

            defaultAnimName = "animation";
            invertedSkeletonScale = 8.5F;

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = TheDuelistEnum.THE_DUELIST.toString();
        }
    }
}
