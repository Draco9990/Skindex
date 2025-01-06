package skindex.skins.player.defect;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.unlockmethods.AchievementUnlockMethod;

import java.util.Iterator;

public class DefectClawGodSkin extends PlayerAtlasSkin {
    private static int DAMAGE_REQUIRED = 50;

    /** Constructors */
    public DefectClawGodSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends PlayerAtlasSkinData {
        public static String ID = "CLAW_GOD";
        public SkinData(){
            atlasUrl = "skindexResources/images/skins/player/defect/clawgod/skeleton.atlas";
            skeletonUrl = "skindexResources/images/skins/player/defect/clawgod/skeleton.json";
            resourceDirectoryUrl = "skindexResources/images/skins/player/defect/clawgod/";

            id = ID;
            name = "Claw God";

            this.unlockDescription = "This skin is unlocked by reaching 50 or more damage on a Claw card.";
            icon = "skindexResources/images/skins/player/defect/clawgod/icon.png";

            unlockMethod = AchievementUnlockMethod.methodId;
            playerClass = AbstractPlayer.PlayerClass.DEFECT.name();
        }
    }

    /** Patches */
    public static class Patches{
        public static class UnlockPatches{
            @SpirePatch2(clz = GashAction.class, method = "update")
            public static class UnlockSkinAchievement{
                public static void Postfix(GashAction __instance){
                    Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

                    AbstractCard c;
                    while(var1.hasNext()) {
                        c = (AbstractCard)var1.next();
                        if (c instanceof Claw) {
                            if(c.baseDamage >= DAMAGE_REQUIRED){
                                SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.DEFECT, SkinData.ID).unlock();
                                return;
                            }
                        }
                    }

                    var1 = AbstractDungeon.player.drawPile.group.iterator();

                    while(var1.hasNext()) {
                        c = (AbstractCard)var1.next();
                        if (c instanceof Claw) {
                            if(c.baseDamage >= DAMAGE_REQUIRED){
                                SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.DEFECT, SkinData.ID).unlock();
                                return;
                            }
                        }
                    }

                    var1 = AbstractDungeon.player.hand.group.iterator();

                    while(var1.hasNext()) {
                        c = (AbstractCard)var1.next();
                        if (c instanceof Claw) {
                            if(c.baseDamage >= DAMAGE_REQUIRED){
                                SkindexRegistry.getPlayerSkinByClassAndId(AbstractPlayer.PlayerClass.DEFECT, SkinData.ID).unlock();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
