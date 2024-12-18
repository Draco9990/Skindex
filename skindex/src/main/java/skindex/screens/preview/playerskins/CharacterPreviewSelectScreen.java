package skindex.screens.preview.playerskins;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dLib.DLib;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.Button;
import dLib.ui.elements.prefabs.Image;
import dLib.ui.elements.prefabs.VerticalBox;
import dLib.ui.elements.prefabs.VerticalListBox;
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

        addChildNCS(new Image(Tex.stat(UICommonResources.white_pixel), Dim.fill(), Dim.fill()));

        Button child;
        addChildCS(child = new Button(Pos.px(1788), Pos.px(1080-121), Dim.px(95), Dim.px(95)){
            @Override
            protected void onLeftClick() {
                super.onLeftClick();
                self.close();
            }
        });
        child.setImage(Tex.stat(UICommonResources.xButton));

        VerticalListBox<AbstractPlayer.PlayerClass> listBox = new VerticalListBox<AbstractPlayer.PlayerClass>(Pos.px(70), Pos.px(1080-1020), Dim.px(1724), Dim.px(840)){
            @Override
            public UIElement makeUIForItem(AbstractPlayer.PlayerClass item) {
                return new CharacterPreviewButton(item);
            }

            @Override
            public void onItemSelectionChanged(ArrayList<AbstractPlayer.PlayerClass> item) {
                super.onItemSelectionChanged(item);

                if(item.size() != 1){
                    return;
                }

                PlayerSkinPreviewScreen screen = new PlayerSkinPreviewScreen(item.get(0));
                screen.open();
            }
        };
        Color transparent = Color.WHITE.cpy();
        transparent.a = 0.0f;
        listBox.setRenderColor(transparent);
        listBox.setItems(SkindexRegistry.getRegisteredPlayerClasses());
        addChildNCS(listBox);
    }
    //endregion Constructors
}
