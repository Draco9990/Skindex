package skindex.skins.player.watcher;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.DevaForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.JuzuBracelet;
import com.megacrit.cardcrawl.relics.SingingBowl;
import com.megacrit.cardcrawl.relics.Vajra;
import skindex.unlockmethods.AchievementUnlockMethod;

public class WatcherFormSkin extends WatcherAtlasSkin {

    public WatcherFormSkin() {
        super(new SkinData());
    }

    public static class SkinData extends WatcherAtlasSkinData {
        public static String ID = "FORM";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/watcher/form/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/watcher/form/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/watcher/form/";

            eyeAtlasUrl = "skindexResources/images/skins/player/watcher/form/eye/skeleton.atlas";
            eyeSkeletonUrl = "skindexResources/images/skins/player/watcher/form/eye/skeleton.json";
            eyeResourceDirectoryUrl = "skindexResources/images/skins/player/watcher/form/eye/";

            id = ID;
            name = "One True Form";

            icon = "skindexResources/images/skins/player/watcher/form/icon.png";

            unlockDescription = "This skin is unlocked by playing Deva Form with Vajra, Singing Bowl, and Juzu Bracelet.";
            unlockMethod = AchievementUnlockMethod.methodId;

            playerClass = AbstractPlayer.PlayerClass.WATCHER.name();

            credits.add("YawgmothHS");
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
    public static class UnlockSkinAchievement{
        public static void Postfix(AbstractCard c){
            if(c instanceof DevaForm){
                if(AbstractDungeon.player.hasRelic(Vajra.ID) && AbstractDungeon.player.hasRelic(SingingBowl.ID) && AbstractDungeon.player.hasRelic(JuzuBracelet.ID)){
                    unlockSkin(new SkinData());
                }
            }
        }
    }
}
