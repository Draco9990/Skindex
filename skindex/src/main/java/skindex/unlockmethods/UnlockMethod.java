package skindex.unlockmethods;

import java.io.Serializable;

public class UnlockMethod implements Serializable {
    static final long serialVersionUID = 1L;

    /** Variables */
    public String id;
    public String itemUnlockDescription;

    /** Constructors */
    public UnlockMethod(String id, String itemUnlockDescription){
        this.id = id;
        this.itemUnlockDescription = itemUnlockDescription;
    }

    /** Methods */
    public String getItemUnlockDescription(){
        return itemUnlockDescription;
    }
}
