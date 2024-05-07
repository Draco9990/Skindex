package skindex.screens.preview.playerskins;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.registering.SkindexRegistry;
import skindex.screens.preview.AbstractItemPreviewScreen;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;

public class PlayerSkinPreviewScreen extends AbstractItemPreviewScreen<PlayerSkin> {
    //region Variables
    private AbstractPlayer.PlayerClass playerClass;
    //endregion Variables

    //region Constructors
    public PlayerSkinPreviewScreen(AbstractPlayer.PlayerClass playerClass){
        super();

        this.playerClass = playerClass;
    }
    //endregion Constructors

    //region Class Methods
    //endregion Class Methods

    @Override
    protected ArrayList<PlayerSkin> getItems() {
        return SkindexRegistry.getSkinsForClass(playerClass, false);
    }
}
