package skindex.ui.elements;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import dLib.ui.Alignment;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.prefabs.Button;
import dLib.ui.elements.prefabs.Image;
import dLib.ui.elements.prefabs.TextBox;
import dLib.ui.elements.prefabs.TextButton;
import dLib.ui.themes.UITheme;
import dLib.ui.themes.UIThemeManager;
import skindex.screens.preview.playerskins.PlayerSkinPreviewScreen;

public class CharacterPreviewButton extends UIElement {
    //region Variables

    private AbstractPlayer.PlayerClass playerClass;

    //endregion Variables

    //region Constructors
    public CharacterPreviewButton(AbstractPlayer.PlayerClass playerClass) {
        super(0, 0, 1724, 92);

        this.playerClass = playerClass;
        loadPlayerIconButton();
        loadPlayerButton();
    }
    //endregion Constructors

    //region Class Methods
    private void loadPlayerIconButton(){
        AbstractPlayer player = CardCrawlGame.characterManager.getCharacter(playerClass);
        if(player != null){
            addChildNCS(new Image(player.getCustomModeCharacterButtonImage(), -10, -30, 152, 152));
        }
    }

    private void loadPlayerButton(){
        AbstractPlayer player = CardCrawlGame.characterManager.getCharacter(playerClass);
        if(player != null){
            TextBox textBox = new TextBox(player.getLocalizedCharacterName(), 135, 6, 1589, 81);
            textBox.setImage(UIThemeManager.getDefaultTheme().button_large);
            textBox.setHorizontalAlignment(Alignment.HorizontalAlignment.LEFT);
            addChildNCS(textBox);
        }
    }
    //endregion Class Methods
}
