package skindex.skins.entity.player.watcher;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.purple.Conclude;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.Reflection;
import skindex.skins.stance.WatcherMusicStanceSkinSet;
import skindex.unlockmethods.AchievementUnlockMethod;

public class WatcherMusicSkin extends WatcherAtlasSkin {

    public WatcherMusicSkin() {
        super(new SkinData());
    }

    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "MUSIC";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/watcher/music/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/watcher/music/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/watcher/music/";

            id = ID;
            name = "The Conductor";

            icon = "skindexResources/images/skins/player/watcher/music/icon.png";

            unlockDescription = "This skin is unlocked by dealing more than 100 damage with Conclude.";
            unlockMethod = AchievementUnlockMethod.methodId;

            stanceSkins.addAll(WatcherMusicStanceSkinSet.collectStanceSkinIds());

            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }

    private static boolean playingConclude = false;

    @SpirePatch2(clz = Conclude.class, method = "use")
    public static class UnlockPatch{
        @SpirePrefixPatch
        public static void setPlayConclude(Conclude __instance){
            playingConclude = true;
        }
    }

    @SpirePatch2(clz = DamageAllEnemiesAction.class, method = "update")
    public static class UnlockPatch2{
        private static int totalMonsterHp = 0;

        @SpirePrefixPatch
        public static void unlock(DamageAllEnemiesAction __instance){
            if(playingConclude && ((boolean)Reflection.getFieldValue("firstFrame", __instance))){
                if(__instance.source instanceof AbstractPlayer){
                    AbstractPlayer player = (AbstractPlayer)__instance.source;
                    if(player.chosenClass == AbstractPlayer.PlayerClass.WATCHER){
                        //Store the HP of all monsters before the damage is dealt
                        for(int i = 0; i < AbstractDungeon.getMonsters().monsters.size(); i++){
                            totalMonsterHp += AbstractDungeon.getMonsters().monsters.get(i).currentHealth;
                        }
                    }
                }
            }
        }

        @SpirePostfixPatch
        public static void resetPlayConclude(DamageAllEnemiesAction __instance){
            if(playingConclude){
                playingConclude = false;
            }

            //Calculate the monster hp again and check if the damage dealt is more than 100
            int totalMonsterHpAfter = 0;
            for(int i = 0; i < AbstractDungeon.getMonsters().monsters.size(); i++){
                totalMonsterHpAfter += AbstractDungeon.getMonsters().monsters.get(i).currentHealth;
            }

            if(totalMonsterHp - totalMonsterHpAfter > 100 && __instance.source != null){
                AbstractPlayer player = (AbstractPlayer)__instance.source;
                if(player.chosenClass == AbstractPlayer.PlayerClass.WATCHER){
                    unlockSkin(new SkinData());
                }
            }
        }
    }
}
