package skindex.ui.elements;

import com.badlogic.gdx.graphics.Texture;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.items.Image;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.position.Pos;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.util.SkindexUI;

public class CustomizableItemPreview<Item extends AbstractCustomizableItem> extends UIElement {
    //region Variables
    public Item previewItem;

    private UIElement favouritedStar;

    //endregion Variables

    //region Constructors
    public CustomizableItemPreview(Item previewItem, boolean isFavourited){
        super(Pos.px(0), Pos.px(0), Dim.px(160), Dim.px(175));

        this.previewItem = previewItem;

        loadPreviewIcon();
        loadBorder();
        loadUnlockGem();
        loadFavourited(isFavourited);

        setDimensions(130, 142);
    }
    //endregion Constructors

    //region Class Methods
    //region Preview Icon
    private void loadPreviewIcon(){
        Texture previewTexture = previewItem.getPreviewIcon();
        addChild(new Image(Tex.stat(previewTexture), Pos.px(11), Pos.px(25), Dim.px(140), Dim.px(140)));

        if(previewItem instanceof AbstractOwnableItem && !((AbstractOwnableItem) previewItem).canUse()){
            addChild(new Image(Tex.stat(SkindexUI.PreviewScreen.previewItemLockedOverlay()), Pos.px(0), Pos.px(15), Dim.px(160), Dim.px(160)));
        }
    }
    //endregion

    //region Border
    private void loadBorder(){
        Texture borderTexture;
        if(!(previewItem instanceof AbstractOwnableItem) || ((AbstractOwnableItem) previewItem).canUse()){
            borderTexture = SkindexUI.PreviewScreen.previewItemUnlockedBorder();
        }
        else{
            borderTexture = SkindexUI.PreviewScreen.previewItemLockedBorder();
        }

        addChild(new Image(Tex.stat(borderTexture), Pos.px(0), Pos.px(15), Dim.px(160), Dim.px(160)));
    }
    //endregion

    //region Unlock Gem
    private void loadUnlockGem(){
        if(!(previewItem instanceof AbstractOwnableItem) || !((AbstractOwnableItem) previewItem).hasUnlocked()){
            //The user doesn't own the item or the item isn't locked ever.
            return;
        }

        if(((AbstractOwnableItem) previewItem).canUse()){ // TODO replace with an 'is original unlock' check
            addChild(new Image(Tex.stat(SkindexUI.PreviewScreen.previewItemOriginalUnlockGem()), Pos.px(62), Pos.px(3), Dim.px(38), Dim.px(38)));
        }
        else{
            addChild(new Image(Tex.stat(SkindexUI.PreviewScreen.previewItemReReleaseUnlockGem()), Pos.px(68), Pos.px(9), Dim.px(26), Dim.px(28)));
        }
    }

    public void loadFavourited(boolean isFavourited){
        if(isFavourited){
            favouritedStar = new Image(Tex.stat(SkindexUI.PreviewScreen.previewItemFavouritedGem()), Pos.px(130), Pos.px(146), Dim.px(57), Dim.px(57));
            addChild(favouritedStar);
        }
        else if(favouritedStar != null){
            removeChild(favouritedStar);
        }

    }
    //endregion
    //endregion Class Methods
}
