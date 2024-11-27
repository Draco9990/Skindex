package skindex.screens.preview.playerskins;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dLib.DLib;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.Button;
import dLib.ui.elements.prefabs.Image;
import dLib.ui.elements.prefabs.VerticalBox;
import dLib.ui.elements.prefabs.VerticalListBox;
import dLib.ui.themes.UIThemeManager;
import skindex.registering.SkindexRegistry;
import skindex.ui.elements.CharacterPreviewButton;

import java.util.ArrayList;
import java.util.List;

public class CharacterPreviewSelectScreen extends UIElement {
    //region Variables
    //endregion Variables

    //region Constructors
    public CharacterPreviewSelectScreen(){
        super(0, 0, 1920, 1080);

        CharacterPreviewSelectScreen self = this;

        addChildNCS(new Image(UIThemeManager.getDefaultTheme().background, 0, 0, getWidth(), getHeight()));

        addChildCS(new Button(1788, 1080-121, 95, 95){
            @Override
            protected void onLeftClick() {
                super.onLeftClick();
                self.close();
            }
        }.setImage(UIThemeManager.getDefaultTheme().button_small_decline));

        VerticalListBox<AbstractPlayer.PlayerClass> listBox = new VerticalListBox<AbstractPlayer.PlayerClass>(70, 1080-1020, 1724, 840){
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
        listBox.getBackground().setRenderColor(transparent);
        listBox.setItems(SkindexRegistry.getRegisteredPlayerClasses());
        addChildNCS(listBox);
    }
    //endregion Constructors
}
