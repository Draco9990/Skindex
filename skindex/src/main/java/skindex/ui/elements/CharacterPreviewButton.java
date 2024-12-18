package skindex.ui.elements;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.ui.Alignment;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.Button;
import dLib.ui.elements.prefabs.Image;
import dLib.ui.elements.prefabs.TextBox;
import dLib.ui.resources.UICommonResources;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.position.Pos;

public class CharacterPreviewButton extends UIElement {
    //region Variables

    private AbstractPlayer.PlayerClass playerClass;

    //endregion Variables

    //region Constructors
    public CharacterPreviewButton(AbstractPlayer.PlayerClass playerClass) {
        super(Pos.px(0), Pos.px(0), Dim.px(1724), Dim.px(92));

        this.playerClass = playerClass;
        loadPlayerIconButton();
        loadPlayerButton();
    }
    //endregion Constructors

    //region Class Methods
    private void loadPlayerIconButton(){
        AbstractPlayer player = CardCrawlGame.characterManager.getCharacter(playerClass);
        if(player != null){
            addChildNCS(new Image(Tex.stat(player.getCustomModeCharacterButtonImage()), Pos.px(-10), Pos.px(-30), Dim.px(152), Dim.px(152)));
        }
    }

    private void loadPlayerButton(){
        AbstractPlayer player = CardCrawlGame.characterManager.getCharacter(playerClass);
        if(player != null){
            TextBox textBox = new TextBox(player.getLocalizedCharacterName(), Pos.px(135), Pos.px(6), Dim.px(1589), Dim.px(81));
            textBox.setImage(Tex.stat(UICommonResources.itembox_itembg_horizontal));
            textBox.setHorizontalAlignment(Alignment.HorizontalAlignment.LEFT);
            addChildNCS(textBox);
        }
    }
    //endregion Class Methods
}
