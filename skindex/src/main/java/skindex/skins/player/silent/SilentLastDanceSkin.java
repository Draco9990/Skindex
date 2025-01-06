package skindex.skins.player.silent;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

public class SilentLastDanceSkin extends PlayerAtlasSkin {
    public static final int THORN_REQUIREMENT = 15;

    /** Constructors */
    public SilentLastDanceSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "LASTDANCE";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/silent/lastdance/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/silent/lastdance/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/silent/lastdance/";

            id = ID;
            name = "The Last Dance";

            icon = "skindexResources/images/skins/player/silent/lastdance/icon.png";

            this.unlockDescription = "This skin is unlocked by gaining " + THORN_REQUIREMENT + " or more thorns as The Silent.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.THE_SILENT.name();
        }
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = ThornsPower.class, method = "stackPower")
        public static class UnlockSkinPatch{
            public static void Postfix(ThornsPower __instance){
                if(__instance.owner == AbstractDungeon.player){
                    if(__instance.amount >= THORN_REQUIREMENT){
                        unlockSkin(new SkinData());
                    }
                }
            }
        }
    }
}
