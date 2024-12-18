package skindex.screens.preview.playerskins;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.*;
import dLib.ui.elements.prefabs.Image;
import dLib.util.TextureManager;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.position.Pos;
import skindex.entities.player.DummyPlayer;
import skindex.files.SkindexUserConfig;
import skindex.registering.SkindexRegistry;
import skindex.screens.preview.AbstractItemPreviewScreen;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerSkinPreviewScreen extends AbstractItemPreviewScreen<PlayerSkin> {
    //region Variables
    private AbstractPlayer.PlayerClass playerClass;

    private DummyPlayer previewEntity;

    //endregion Variables

    //region Constructors
    public PlayerSkinPreviewScreen(AbstractPlayer.PlayerClass playerClass){
        super(true);

        this.playerClass = playerClass;

        this.previewEntity = new DummyPlayer(playerClass);
        this.previewEntity.setRenderScale(2f);
        this.previewEntity.setDrawPosition(((1920 * 0.5f) + 470) * Settings.xScale, ((1080 * 0.5f) - 240) * Settings.yScale);
    }
    //endregion Constructors

    //region Class Methods

    @Override
    protected void updateChildren() {
        super.updateChildren();

        this.previewEntity.update();
    }

    @Override
    protected void renderChildren(SpriteBatch sb) {
        super.renderChildren(sb);

        this.previewEntity.render(sb);
    }

    @Override
    protected ArrayList<PlayerSkin> getItems() {
        return SkindexRegistry.getSkinsForClass(playerClass, false);
    }

    @Override
    protected void onPreviewItemChanged(PlayerSkin item) {
        super.onPreviewItemChanged(item);

        item.loadOnEntity(previewEntity);

        addChildNCS(new Image(Tex.stat(TextureManager.getTexture("skindexResources/images/ui/preview/customorbskins.png")), Pos.px(1596), Pos.px(1080-173), Dim.px(291), Dim.px(27)){
            @Override
            public boolean isVisible() {
                return previewingItem != null && !previewingItem.orbsSkinMap.isEmpty();
            }
        });
    }

    @Override
    protected boolean isItemFavourite(PlayerSkin item) {
        return Objects.equals(SkindexUserConfig.get().getFavouritedSkin(playerClass), item);
    }

    @Override
    protected void onSetItemFavourite(PlayerSkin item) {
        super.onSetItemFavourite(item);
        SkindexUserConfig.get().setFavouritedSkin(playerClass, item);
    }

    //endregion Class Methods
}
