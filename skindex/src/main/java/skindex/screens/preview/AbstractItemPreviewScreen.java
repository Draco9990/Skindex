package skindex.screens.preview;

import com.badlogic.gdx.graphics.Color;
import dLib.DLib;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.Button;
import dLib.ui.elements.prefabs.Image;
import dLib.ui.elements.prefabs.TextBox;
import dLib.ui.elements.prefabs.VerticalGridBox;
import dLib.ui.screens.AbstractScreen;
import dLib.ui.screens.ScreenManager;
import dLib.ui.themes.UITheme;
import dLib.ui.themes.UIThemeManager;
import skindex.itemtypes.CustomizableItem;
import skindex.itemtypes.OwnableItem;
import skindex.ui.elements.CustomizableItemPreview;
import skindex.util.SkindexUI;

import java.util.ArrayList;

public abstract class AbstractItemPreviewScreen<Item extends CustomizableItem> extends AbstractScreen {
    //region Variables
    VerticalGridBox<Item> itemBox;

    TextBox itemUnlockDescriptionTextBox;
    //endregion

    //region Constructors
    public AbstractItemPreviewScreen(){
        addGenericBackground();

        addChildCS(new Button(1788, 1080-121, 95, 95){
            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                ScreenManager.openPreviousScreen();
            }
        }.setImage(UIThemeManager.getDefaultTheme().button_small_decline));

        addChildNCS(new Image(SkindexUI.PreviewScreen.previewScreenBG(), 43, 1080-1058, 867, 1012));

        Color indentColor = Color.BLACK.cpy();
        indentColor.a = 0.4f;

        itemBox = new VerticalGridBox<Item>(93, 1080-796, 787, 694){
            @Override
            public UIElement makeUIForItem(Item item) {
                return new CustomizableItemPreview<>(item);
            }

            @Override
            public void postMakeWrapperForItem(Item item, UIElement itemUI) {
                super.postMakeWrapperForItem(item, itemUI);
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

        itemUnlockDescriptionTextBox = new TextBox("", 93, 1080-946, 787, 136);
        itemUnlockDescriptionTextBox.setImage(UITheme.whitePixel);
        itemUnlockDescriptionTextBox.setRenderColor(indentColor.cpy());
        itemUnlockDescriptionTextBox.setWrap(true);
        itemUnlockDescriptionTextBox.setTextRenderColor(Color.WHITE.cpy());
        addChildNCS(itemUnlockDescriptionTextBox);
    }

    @Override
    public void onOpen() {
        super.onOpen();

        itemBox.setItems(getItems());
    }

    //endregion

    //region Methods

    //region Preview Item
    protected void onPreviewItemChanged(Item item){
        if(item == null){
            return;
        }

        if(!(item instanceof OwnableItem) || ((OwnableItem) item).canUse()){
            itemUnlockDescriptionTextBox.setText("");
        }
        else{
            itemUnlockDescriptionTextBox.setText(((OwnableItem) item).getUnlockDescription());
        }
    }
    //endregion

    protected abstract ArrayList<Item> getItems();

    @Override
    public String getModId() {
        return DLib.getModID();
    }

    //endregion
}