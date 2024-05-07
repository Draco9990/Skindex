package skindex.ui.elements;

import com.badlogic.gdx.graphics.Texture;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.Image;
import skindex.Skindex;
import skindex.itemtypes.CustomizableItem;
import skindex.itemtypes.OwnableItem;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.util.SkindexUI;

public class CustomizableItemPreview<Item extends CustomizableItem> extends UIElement {
    //region Variables
    private Item previewItem;

    //endregion Variables

    //region Constructors
    public CustomizableItemPreview(Item previewItem){
        super(0, 0, 160, 175);

        this.previewItem = previewItem;

        loadPreviewIcon();
        loadBorder();
        loadUnlockGem();

        setDimensions(130, 142);
    }
    //endregion Constructors

    //region Class Methods
    //region Preview Icon
    private void loadPreviewIcon(){
        Texture previewTexture = previewItem.makePreviewIcon();
        addChildNCS(new Image(previewTexture, 11, 25, 140, 140));

        if(previewItem instanceof OwnableItem && !((OwnableItem) previewItem).canUse()){
            addChildNCS(new Image(SkindexUI.PreviewScreen.previewItemLockedOverlay(), 0, 15, 160, 160));
        }
    }
    //endregion

    //region Border
    private void loadBorder(){
        Texture borderTexture;
        if(!(previewItem instanceof OwnableItem) || ((OwnableItem) previewItem).canUse()){
            borderTexture = SkindexUI.PreviewScreen.previewItemUnlockedBorder();
        }
        else{
            borderTexture = SkindexUI.PreviewScreen.previewItemLockedBorder();
        }

        addChildNCS(new Image(borderTexture, 0, 15, 160, 160));
    }
    //endregion

    //region Unlock Gem
    private void loadUnlockGem(){
        if(!(previewItem instanceof OwnableItem) || !((OwnableItem) previewItem).hasUnlocked()){
            //The user doesn't own the item or the item isn't locked ever.
            return;
        }

        if(((OwnableItem) previewItem).canUse()){ // TODO replace with an 'is original unlock' check
            addChildNCS(new Image(SkindexUI.PreviewScreen.previewItemOriginalUnlockGem(), 62, 3, 38, 38));
        }
        else{
            addChildNCS(new Image(SkindexUI.PreviewScreen.previewItemReReleaseUnlockGem(), 68, 9, 26, 28));
        }
    }
    //endregion
    //endregion Class Methods
}
