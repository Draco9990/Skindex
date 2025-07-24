package skindex.ui.elements;

import com.badlogic.gdx.graphics.Texture;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.items.Image;
import dLib.ui.elements.items.Interactable;
import dLib.util.bindings.texture.Tex;
import dLib.util.bindings.texture.TextureNoneBinding;
import dLib.util.ui.dimensions.AbstractDimension;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.position.Pos;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.itemtypes.AbstractOwnableItem;

public class CustomizableItemPreview<Item extends AbstractCustomizableItem> extends Interactable {
    //region Variables
    public Item previewItem;

    private UIElement favouritedStar;

    //endregion Variables

    //region Constructors

    public CustomizableItemPreview(Item previewItem, AbstractDimension dim, boolean isFavourited){
        this(previewItem, dim, isFavourited, true, true);
    }

    public CustomizableItemPreview(Item previewItem, AbstractDimension dim, boolean isFavourited, boolean showUnlockGem, boolean showFavouriteGem){
        super(new TextureNoneBinding(), Pos.px(0), Pos.px(0), dim.cpy(), dim.cpy());

        this.previewItem = previewItem;

        loadPreviewIcon();
        loadBorder();
        if(showUnlockGem) loadUnlockGem();
        if(showFavouriteGem) loadFavourited(isFavourited);
    }

    //endregion Constructors

    //region Class Methods

    //region Preview Icon

    private void loadPreviewIcon(){
        Texture previewTexture = previewItem.getIcon();
        addChild(new Image(Tex.stat(previewTexture), Pos.perc(0.0625), Pos.perc(0.0625), Dim.perc(0.875), Dim.perc(0.875)));

        if(previewItem instanceof AbstractOwnableItem && !((AbstractOwnableItem) previewItem).canUse()){
            addChild(new Image(Tex.stat(SkindexUICommonResources.itemPreviewLockedItemOverlay), Dim.fill(), Dim.fill()));
        }
    }

    //endregion

    //region Border
    private void loadBorder(){
        Texture borderTexture;
        if(!(previewItem instanceof AbstractOwnableItem) || ((AbstractOwnableItem) previewItem).canUse()){
            borderTexture = SkindexUICommonResources.itemPreviewBorderUnlocked;
        }
        else{
            borderTexture = SkindexUICommonResources.itemPreviewBorderLocked;
        }

        addChild(new Image(Tex.stat(borderTexture), Dim.fill(), Dim.fill()));
    }
    //endregion

    //region Unlock Gem
    private void loadUnlockGem(){
        if(!(previewItem instanceof AbstractOwnableItem) || !((AbstractOwnableItem) previewItem).hasUnlocked()){
            //The user doesn't own the item or the item isn't locked ever.
            return;
        }

        if(((AbstractOwnableItem) previewItem).canUse()){ // TODO replace with an 'is original unlock' check
            addChild(new Image(Tex.stat(SkindexUICommonResources.itemPreviewUnlockGemOriginal), Pos.perc(0.8125), Pos.perc(0.075), Dim.perc(0.35625), Dim.perc(0.35625)));
        }
        else{
            addChild(new Image(Tex.stat(SkindexUICommonResources.itemPreviewUnlockGemReRelease), Pos.perc(0.8125), Pos.perc(0.075), Dim.perc(0.35625), Dim.perc(0.35625)));
        }
    }

    public void loadFavourited(boolean isFavourited){
        if(isFavourited){
            favouritedStar = new Image(Tex.stat(SkindexUICommonResources.itemPreviewFavourite), Pos.perc(0.8125), Pos.perc(0.8125), Dim.perc(0.35625), Dim.perc( 0.35625));
            addChild(favouritedStar);
        }
        else if(favouritedStar != null){
            removeChildByInstance(favouritedStar);
        }

    }
    //endregion

    //endregion Class Methods
}
