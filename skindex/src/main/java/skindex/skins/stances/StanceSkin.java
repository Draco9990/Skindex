package skindex.skins.stances;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.stances.*;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import dLib.util.Reflection;
import dLib.util.Timer;
import skindex.SkindexGame;
import skindex.itemtypes.CustomizableItem;
import skindex.itemtypes.CustomizableItemData;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;

public class StanceSkin extends CustomizableItem {
    //region Variables
    public String stanceId;

    private ArrayList<Timer> effectTimers = new ArrayList<>();

    private AbstractCreature target;
    //endregion Variables

    //region Constructors
    public StanceSkin(StanceSkinData itemData){
        super(itemData);

        this.stanceId = itemData.stanceId;
    }
    //endregion Constructors

    //region Class Methods
    //region Update & Render
    public void update(){
        for(Timer t : effectTimers){
            t.update();
        }
    }
    //endregion

    //region Target
    public void setTarget(AbstractCreature target){
        this.target = target;
    }
    public AbstractCreature getTarget(){
        return target;
    }
    //endregion

    //region Timers
    public void addTimer(Timer timer){
        effectTimers.add(timer);
    }
    //endregion
    //endregion Class Methods

    //region Patches
    public static class Patches{
        public static class RenderPatches{
            @SpirePatch2(clz = WrathStance.class, method = "updateAnimation")
            public static class WrathReplacer{
                public static SpireReturn Prefix(WrathStance __instance){
                    StanceSkin currentSkin = SkindexGame.getActiveStanceSkin(__instance.ID);
                    if(currentSkin != null){
                        currentSkin.update();
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = CalmStance.class, method = "updateAnimation")
            public static class CalmReplacer{
                public static SpireReturn Prefix(CalmStance __instance){
                    StanceSkin currentSkin = SkindexGame.getActiveStanceSkin(__instance.ID);
                    if(currentSkin != null){
                        currentSkin.update();
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = DivinityStance.class, method = "updateAnimation")
            public static class DivinityReplacer{
                public static SpireReturn Prefix(DivinityStance __instance){
                    StanceSkin currentSkin = SkindexGame.getActiveStanceSkin(__instance.ID);
                    if(currentSkin != null){
                        currentSkin.update();
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = AbstractStance.class, method = "updateAnimation")
            public static class GenericReplacer{
                public static SpireReturn Prefix(AbstractStance __instance){
                    StanceSkin currentSkin = SkindexGame.getActiveStanceSkin(__instance.ID);
                    if(currentSkin != null){
                        currentSkin.update();
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }
        }
    }
    //endregion
}
