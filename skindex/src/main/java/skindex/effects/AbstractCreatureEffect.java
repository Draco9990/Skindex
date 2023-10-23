package skindex.effects;

import com.megacrit.cardcrawl.core.AbstractCreature;

import java.sql.Time;
import java.util.ArrayList;
import dLib.util.Timer;

public class AbstractCreatureEffect {
    /** Variables */
    private AbstractCreature target;
    private ArrayList<Timer> effectTimers = new ArrayList<>();

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
}
