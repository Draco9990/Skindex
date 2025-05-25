package skindex.screens.preview.playerskins;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dLib.DLib;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.items.Image;
import dLib.ui.elements.items.buttons.Button;
import dLib.ui.elements.items.itembox.VerticalDataBox;
import dLib.ui.resources.UICommonResources;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.position.Pos;
import skindex.registering.SkindexRegistry;
import skindex.ui.elements.CharacterPreviewButton;

import java.util.ArrayList;
import java.util.List;

public class CharacterPreviewSelectScreen extends UIElement {
    //region Variables
    //endregion Variables

    //region Constructors
    public CharacterPreviewSelectScreen(){
        super();

        CharacterPreviewSelectScreen self = this;

        addChild(new Image(Tex.stat(UICommonResources.white_pixel), Dim.fill(), Dim.fill()));

        Button child;
        addChild(child = new Button(Pos.px(1788), Pos.px(1080-121), Dim.px(95), Dim.px(95)){
            @Override
            protected void onLeftClick() {
                super.onLeftClick();
                self.close();
            }
        });
        child.setTexture(Tex.stat(UICommonResources.xButton));

        VerticalDataBox<AbstractPlayer.PlayerClass> listBox = new VerticalDataBox<AbstractPlayer.PlayerClass>(Pos.px(70), Pos.px(1080-1020), Dim.px(1724), Dim.px(840)){
            @Override
            public UIElement makeUIForItem(AbstractPlayer.PlayerClass item) {
                return new CharacterPreviewButton(item);
            }

            @Override
            public void onItemSelectionChanged() {
                super.onItemSelectionChanged();

                List<AbstractPlayer.PlayerClass> items = getCurrentlySelectedItems();
                if(items.size() != 1){
                    return;
                }

                PlayerSkinPreviewScreen screen = new PlayerSkinPreviewScreen(items.get(0));
                screen.open();
            }
        };
        Color transparent = Color.WHITE.cpy();
        transparent.a = 0.0f;
        listBox.setRenderColor(transparent);
        listBox.setChildren(SkindexRegistry.getRegisteredPlayerClasses());
        addChild(listBox);
    }
    //endregion Constructors
}
