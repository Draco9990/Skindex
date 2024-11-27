package skindex.screens.preview;

import com.badlogic.gdx.graphics.Color;
import dLib.DLib;
import dLib.ui.Alignment;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.*;
import dLib.ui.themes.UITheme;
import dLib.ui.themes.UIThemeManager;
import skindex.itemtypes.CustomizableItem;
import skindex.itemtypes.OwnableItem;
import skindex.ui.elements.CustomizableItemPreview;
import skindex.unlockmethods.UnlockMethod;
import skindex.util.SkindexUI;

import java.util.ArrayList;

public abstract class AbstractItemPreviewScreen<Item extends CustomizableItem> extends UIElement {
    //region Variables
    VerticalGridBox<Item> itemBox;

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
        super(0, 0, 1920, 1080);

        AbstractItemPreviewScreen self = this;

        addChildNCS(new Image(UIThemeManager.getDefaultTheme().background, 0, 0, getWidth(), getHeight()));

        addChildCS(new Button(1788, 1080-121, 95, 95){
            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                self.close();
            }
        }.setImage(UIThemeManager.getDefaultTheme().button_small_decline));

        addChildNCS(new Image(SkindexUI.PreviewScreen.previewScreenBG(), 43, 1080-1058, 867, 1012));

        itemUnlockDescriptionTextBox = new TextBox("", 891, 1080-1013, 1029, 252);
        itemUnlockDescriptionTextBox.setImage(SkindexUI.PreviewScreen.previewItemUnlockDescriptionBackground());
        itemUnlockDescriptionTextBox.setWrap(true);
        itemUnlockDescriptionTextBox.setTextRenderColor(Color.WHITE.cpy());
        addChildNCS(itemUnlockDescriptionTextBox);

        Color indentColor = Color.BLACK.cpy();
        indentColor.a = 0.4f;

        itemBox = new VerticalGridBox<Item>(93, 1080-796-162, 787, 856){
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
        itemBox.getBackground().setImage(UITheme.whitePixel);
        itemBox.getBackground().setRenderColor(indentColor.cpy());
        itemBox.setPadding(30, 30);
        addChildCS(itemBox);

        itemNameTextBox = new TextBox("", 1145, 1080-136, 570, 104);
        addChildNCS(itemNameTextBox);

        itemCreditsTextBox = new TextBox("", 921, 1080-753, 130, 705);
        itemCreditsTextBox.setTextRenderColor(Color.WHITE.cpy());
        itemCreditsTextBox.setAlignment(Alignment.HorizontalAlignment.LEFT, Alignment.VerticalAlignment.TOP);
        itemCreditsTextBox.setMarginPercY(0.01f);
        addChildNCS(itemCreditsTextBox);

        this.supportsItemFavourites = supportsItemFavourites;
        favouriteButton = new TextButton("Set Default", 1169, 1080-986, 522, 101);
        favouriteButton.getButton().addOnLeftClickConsumer(() -> {
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
