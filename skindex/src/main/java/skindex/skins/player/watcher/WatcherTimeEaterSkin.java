package skindex.skins.player.watcher;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Vault;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.powers.TimeWarpPower;
import skindex.unlockmethods.AchievementUnlockMethod;

public class WatcherTimeEaterSkin extends WatcherAtlasSkin {
    /** Constructors */
    public WatcherTimeEaterSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "TIME_EATER";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/watcher/timeeater/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/watcher/timeeater/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/watcher/timeeater/";

            eyeAtlasUrl = "skindexResources/images/skins/player/watcher/timeeater/eye/skeleton.atlas";
            eyeSkeletonUrl = "skindexResources/images/skins/player/watcher/timeeater/eye/skeleton.json";
            eyeResourceDirectoryUrl = "skindexResources/images/skins/player/watcher/timeeater/eye/";

            id = ID;
            name = "Thread of Time";

            this.unlockDescription = "This skin is unlocked by playing Vault on the final turn of the Time Eater fight, and by killing the Time Eater in the following turn.";
            icon = "skindexResources/images/skins/player/watcher/timeeater/icon.png";

            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();
        }
    }

    public static class UnlockPatches{
        //Yeh i know its ugly idc
        //it's 1 am and i want to sleep

        public static boolean readyToKill = false;
        public static boolean readyREADYToKill = false;

        @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
        public static class UnlockSkinAchievement{
            public static void Postfix(AbstractCard c){
                if(c instanceof Vault){
                    TimeEater timeEater = null;
                    for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
                        if(monster instanceof TimeEater){
                            timeEater = (TimeEater) monster;
                        }
                    }

                    if(timeEater == null){
                        return;
                    }

                    if(timeEater.hasPower(TimeWarpPower.POWER_ID) && timeEater.getPower(TimeWarpPower.POWER_ID).amount == 11){
                        readyToKill = true;
                        readyREADYToKill = true;
                    }
                }
            }
        }

        @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnRelics")
        public static class DeathCheck{
            public static void Postfix(){
                if(readyREADYToKill){
                    readyREADYToKill = false;
                }
                if(readyToKill){
                    readyToKill = false;
                    readyREADYToKill = true;
                }
            }
        }

        @SpirePatch2(clz = TimeEater.class, method = "die")
        public static class Death{
            public static void Postfix(){
                if(readyREADYToKill){
                    readyREADYToKill = false;
                    readyToKill = false;

                    unlockSkin(new SkinData());
                }
            }
        }
    }
}
