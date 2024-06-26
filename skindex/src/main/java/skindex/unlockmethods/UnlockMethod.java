package skindex.unlockmethods;

import dLib.ui.elements.UIElement;

import java.io.Serializable;

public class UnlockMethod implements Serializable {
    static final long serialVersionUID = 1L;

    //region Variables
    public String id;
    public String itemUnlockDescription;
    //endregion Variables

    //region Constructors
    public UnlockMethod(String id, String itemUnlockDescription){
        this.id = id;
        this.itemUnlockDescription = itemUnlockDescription;
    }
    //endregion Constructors

    //region Class Methods

    //region Item Unlock Description
    public String getItemUnlockDescription(){
        return itemUnlockDescription;
    }
    //endregion

    //region Unlock Button
    public UIElement makeUnlockButton(int xPos, int yPos, int width, int height){
        return null;
    }
    //endregion
    //endregion Class Methods
}
