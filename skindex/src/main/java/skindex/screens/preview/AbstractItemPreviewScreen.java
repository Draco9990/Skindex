package skindex.screens.preview;

import com.badlogic.gdx.graphics.Color;
import dLib.ui.Alignment;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.items.Image;
import dLib.ui.elements.items.buttons.Button;
import dLib.ui.elements.items.itembox.GridItemBox;
import dLib.ui.elements.items.text.ImageTextBox;
import dLib.ui.elements.items.text.TextBox;
import dLib.ui.elements.items.text.TextButton;
import dLib.ui.resources.UICommonResources;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.padding.Padd;
import dLib.util.ui.position.Pos;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.ui.elements.CustomizableItemPreview;
import skindex.ui.elements.SkindexUICommonResources;
import skindex.unlockmethods.AbstractUnlockMethod;

import java.util.ArrayList;

public abstract class AbstractItemPreviewScreen<Item extends AbstractCustomizableItem> extends UIElement {
    //region Variables
    GridItemBox<Item> itemBox;

    TextBox itemNameTextBox;
    TextBox itemCreditsTextBox;
    ImageTextBox itemUnlockDescriptionTextBox;

    TextButton favouriteButton;

    UIElement unlockButton;

    protected Item previewingItem = null;

    private boolean supportsItemFavourites;
    //endregion

    //region Constructors
    public AbstractItemPreviewScreen(boolean supportsItemFavourites){
        super();

        AbstractItemPreviewScreen self = this;

        addChild(new Image(Tex.stat(UICommonResources.white_pixel), Dim.fill(), Dim.fill()));

        Button main;
        addChild(main = new Button(Pos.px(1788), Pos.px(1080-121), Dim.px(95), Dim.px(95)){
            @Override
            protected void onLeftClick(boolean byProxy) {
                super.onLeftClick(byProxy);

                self.close();
            }
        });
        main.setTexture(Tex.stat(UICommonResources.xButton));

        addChild(new Image(Tex.stat(SkindexUICommonResources.itemPreviewBg), Pos.px(43), Pos.px(1080-1058), Dim.px(867), Dim.px(1012)));

        itemUnlockDescriptionTextBox = new ImageTextBox("", Pos.px(891), Pos.px(1080-1013), Dim.px(1029), Dim.px(252));
        itemUnlockDescriptionTextBox.setTexture(Tex.stat(SkindexUICommonResources.itemPreviewUnlockDescriptionBackground));
        itemUnlockDescriptionTextBox.textBox.setWrap(true);
        itemUnlockDescriptionTextBox.textBox.setTextRenderColor(Color.WHITE.cpy());
        addChild(itemUnlockDescriptionTextBox);

        Color indentColor = Color.BLACK.cpy();
        indentColor.a = 0.4f;

        itemBox = new GridItemBox<Item>(Pos.px(93), Pos.px(1080-796-162), Dim.px(787), Dim.px(856)){
            @Override
            public UIElement makeUIForItem(Item item) {
                return new CustomizableItemPreview<>(item, Dim.px(130), isItemFavourite(item));
            }

            @Override
            public void onItemSelectionChanged() {
                super.onItemSelectionChanged();

                ArrayList<Item> items = getCurrentlySelectedItems();
                if(items.size() != 1){
                    return;
                }

                onPreviewItemChanged(items.get(0));
            }
        };
        itemBox.setItemSpacing(20);
        itemBox.setTexture(Tex.stat(UICommonResources.white_pixel));
        itemBox.setRenderColor(indentColor.cpy());
        itemBox.setPadding(Padd.px(30));
        addChild(itemBox);

        itemNameTextBox = new TextBox("", Pos.px(1145), Pos.px(1080-136), Dim.px(570), Dim.px(104));
        addChild(itemNameTextBox);

        itemCreditsTextBox = new TextBox("", Pos.px(921), Pos.px(1080-753), Dim.px(130), Dim.px(705));
        itemCreditsTextBox.setTextRenderColor(Color.WHITE.cpy());
        itemCreditsTextBox.setAlignment(Alignment.HorizontalAlignment.LEFT, Alignment.VerticalAlignment.TOP);
        addChild(itemCreditsTextBox);

        this.supportsItemFavourites = supportsItemFavourites;
        favouriteButton = new TextButton("Set Default", Pos.px(1169), Pos.px(1080-986), Dim.px(522), Dim.px(101));
        favouriteButton.onLeftClickEvent.subscribe(favouriteButton, () -> {
            if(previewingItem != null){
                onSetItemFavourite(previewingItem);
            }
        });
        favouriteButton.hideAndDisable();
        addChild(favouriteButton);
    }

    @Override
    protected void setVisibility(boolean visible) {
        super.setVisibility(visible);

        if(visible){
            ArrayList<Item> items = getItems();
            itemBox.setChildren(items);
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

        if(!(item instanceof AbstractOwnableItem) || ((AbstractOwnableItem) item).canUse()){
            itemUnlockDescriptionTextBox.textBox.setText("");
            itemUnlockDescriptionTextBox.hide();

            if(supportsItemFavourites && !isItemFavourite(item)){
                favouriteButton.showAndEnable();
            }
            else{
                favouriteButton.hideAndDisable();
            }
        }
        else{
            itemUnlockDescriptionTextBox.textBox.setText(((AbstractOwnableItem) item).getUnlockDescription());
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
            removeChildByInstance(unlockButton);
        }

        if(item instanceof AbstractOwnableItem){
            AbstractUnlockMethod unlockMethod = ((AbstractOwnableItem) item).unlockMethod;

            UIElement unlockMethodButton = unlockMethod.makeUnlockButton(1232, 1080-1022, 374, 75);
            if(unlockMethodButton != null){
                unlockButton = unlockMethodButton;
                addChild(unlockButton);
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
