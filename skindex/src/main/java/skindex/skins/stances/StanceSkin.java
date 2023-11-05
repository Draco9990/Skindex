package skindex.skins.stances;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.stances.WrathStance;
import dLib.util.Timer;
import skindex.SkindexGame;
import skindex.itemtypes.CustomizableItem;
import skindex.itemtypes.CustomizableItemData;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;

public class StanceSkin extends CustomizableItem {
    /** Variables */
    private AbstractCreature target;
    private ArrayList<Timer> effectTimers = new ArrayList<>();

    /** Constructors */
    public StanceSkin(String id, String name){
        super(id, name);
    }

    /** Getters and Setters */
    public void setTarget(AbstractCreature target){
        this.target = target;
    }
    public AbstractCreature getTarget(){
        return target;
    }

    /** Update and Render */
    public void update(){
        for(Timer t : effectTimers){
            t.update();
        }
    }

    /** Methods */
    public void addTimer(Timer timer){
        effectTimers.add(timer);
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = WrathStance.class, method = "updateAnimation")
        public static class WrathReplacer{
            public static void Postfix(WrathStance __instance){
                PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    if(currentSkin.stanceSkinMap.containsKey(__instance.ID)){
                        currentSkin.stanceSkinMap.get(__instance.ID).update();
                        currentSkin.stanceSkinMap.get(__instance.ID).update();
                    }
                }
            }
        }
    }
}
