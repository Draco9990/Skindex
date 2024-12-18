package skindex.screens.preview;

import com.badlogic.gdx.graphics.Color;
import dLib.DLib;
import dLib.ui.Alignment;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.*;
import dLib.ui.resources.UICommonResources;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.padding.Padd;
import dLib.util.ui.position.Pos;
import skindex.itemtypes.CustomizableItem;
import skindex.itemtypes.OwnableItem;
import skindex.ui.elements.CustomizableItemPreview;
import skindex.unlockmethods.UnlockMethod;
import skindex.util.SkindexUI;

import java.util.ArrayList;

public abstract class AbstractItemPreviewScreen<Item extends CustomizableItem> extends UIElement {
    //region Variables
    GridItemBox<Item> itemBox;

    TextBox itemNameTextBox;
    TextBox itemCreditsTextBox;
    TextBox itemUnlockDescriptionTextBox;

    TextButton favouriteButton;

    UIElement unlockButton;

    protected Item previewingItem = null;

    private boolean supportsItemFavourites;
    //endregion

    //region Constructors
    public AbstractItemPreviewScreen(boolean supportsItemFavourites){
        super();

        AbstractItemPreviewScreen self = this;

        addChildNCS(new Image(Tex.stat(UICommonResources.white_pixel), Dim.fill(), Dim.fill()));

        Button main;
        addChildCS(main = new Button(Pos.px(1788), Pos.px(1080-121), Dim.px(95), Dim.px(95)){
            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                self.close();
            }
        });
        main.setImage(Tex.stat(UICommonResources.xButton));

        addChildNCS(new Image(Tex.stat(SkindexUI.PreviewScreen.previewScreenBG()), Pos.px(43), Pos.px(1080-1058), Dim.px(867), Dim.px(1012)));

        itemUnlockDescriptionTextBox = new TextBox("", Pos.px(891), Pos.px(1080-1013), Dim.px(1029), Dim.px(252));
        itemUnlockDescriptionTextBox.setImage(Tex.stat(SkindexUI.PreviewScreen.previewItemUnlockDescriptionBackground()));
        itemUnlockDescriptionTextBox.setWrap(true);
        itemUnlockDescriptionTextBox.setTextRenderColor(Color.WHITE.cpy());
        addChildNCS(itemUnlockDescriptionTextBox);

        Color indentColor = Color.BLACK.cpy();
        indentColor.a = 0.4f;

        itemBox = new GridItemBox<Item>(Pos.px(93), Pos.px(1080-796-162), Dim.px(787), Dim.px(856)){
            @Override
            public UIElement makeUIForItem(Item item) {
                return new CustomizableItemPreview<>(item, isItemFavourite(item));
            }

            @Override
            public void onItemSelectionChanged(ArrayList<Item> item) {
                super.onItemSelectionChanged(item);

                if(item.size() != 1){
                    return;
                }

                onPreviewItemChanged(item.get(0));
            }
        };
        itemBox.setItemSpacing(20);
        itemBox.setImage(Tex.stat(UICommonResources.white_pixel));
        itemBox.setRenderColor(indentColor.cpy());
        itemBox.setPadding(Padd.px(30));
        addChildCS(itemBox);

        itemNameTextBox = new TextBox("", Pos.px(1145), Pos.px(1080-136), Dim.px(570), Dim.px(104));
        addChildNCS(itemNameTextBox);

        itemCreditsTextBox = new TextBox("", Pos.px(921), Pos.px(1080-753), Dim.px(130), Dim.px(705));
        itemCreditsTextBox.setTextRenderColor(Color.WHITE.cpy());
        itemCreditsTextBox.setAlignment(Alignment.HorizontalAlignment.LEFT, Alignment.VerticalAlignment.TOP);
        itemCreditsTextBox.setMarginPercY(0.01f);
        addChildNCS(itemCreditsTextBox);

        this.supportsItemFavourites = supportsItemFavourites;
        favouriteButton = new TextButton("Set Default", Pos.px(1169), Pos.px(1080-986), Dim.px(522), Dim.px(101));
        favouriteButton.getButton().onLeftClickEvent.subscribe(favouriteButton, () -> {
            if(previewingItem != null){
                onSetItemFavourite(previewingItem);
            }
        });
        favouriteButton.hideAndDisable();
        addChildCS(favouriteButton);
    }

    @Override
    protected void setVisibility(boolean visible) {
        super.setVisibility(visible);

        if(visible){
            ArrayList<Item> items = getItems();
            itemBox.setItems(items);
            if(!items.isEmpty()) onPreviewItemChanged(items.get(0));
            //TODO Rf
        }
    }

    //endregion

    //region Methods

    //region Preview Item
    protected void onPreviewItemChanged(Item item){
        if(item == null){
            return;
        }

        previewingItem = item;

        if(!(item instanceof OwnableItem) || ((OwnableItem) item).canUse()){
            itemUnlockDescriptionTextBox.setText("");
            itemUnlockDescriptionTextBox.hide();

            if(supportsItemFavourites && !isItemFavourite(item)){
                favouriteButton.showAndEnable();
            }
            else{
                favouriteButton.hideAndDisable();
            }
        }
        else{
            itemUnlockDescriptionTextBox.setText(((OwnableItem) item).getUnlockDescription());
            itemUnlockDescriptionTextBox.show();

            favouriteButton.hideAndDisable();
        }

        itemNameTextBox.setText(item.getName());

        if(!item.getCredits().isEmpty()){
            String creditsText = "Credits:\n";
            for(String credit : item.getCredits()){
                creditsText += credit + "\n";
            }
            itemCreditsTextBox.setText(creditsText);
        }
        else{
            itemCreditsTextBox.setText("");
        }

        loadUnlockButton(item);
    }

    private void loadUnlockButton(Item item){
        if(unlockButton != null){
            removeChild(unlockButton);
        }

        if(item instanceof OwnableItem){
            UnlockMethod unlockMethod = ((OwnableItem) item).unlockMethod;

            UIElement unlockMethodButton = unlockMethod.makeUnlockButton(1232, 1080-1022, 374, 75);
            if(unlockMethodButton != null){
                unlockButton = unlockMethodButton;
                addChildNCS(unlockButton);
            }
        }
    }
    //endregion

    //region Item Favouriting

    protected boolean isItemFavourite(Item item){
        return false;
    }

    protected void onSetItemFavourite(Item item){
        favouriteButton.hideAndDisable();
        refreshFavouritedItems();
    }

    private void refreshFavouritedItems(){
        for(UIElement element : itemBox.getChildren()){
            if(element instanceof CustomizableItemPreview){
                CustomizableItemPreview<Item> preview = (CustomizableItemPreview<Item>) element;
                preview.loadFavourited(isItemFavourite(preview.previewItem));
            }
        }
    }

    //endregion

    protected abstract ArrayList<Item> getItems();

    //endregion
}
