package skindex.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowRoom;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.implementations.Interactable;
import dLib.ui.elements.prefabs.TextBox;
import dLib.ui.elements.prefabs.Toggle;
import dLib.ui.themes.UIThemeManager;
import dLib.util.TextureManager;
import skindex.SkindexGame;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerSkin;

public class NeowWardrobePatches {
    public static Toggle wardrobe;

    public static UIElement skinController;

    public static void initializeElements(){
        if(wardrobe != null) return;

        wardrobe = new Toggle(
                TextureManager.getTexture("skindexResources/images/ui/wardrobe.png"),
                TextureManager.getTexture("skindexResources/images/ui/wardrobe.png"),
                27, 1080-726,
                203, 374
        ){
            @Override
            public boolean isActive() {
                return super.isActive() && SkindexRegistry.getSkinsForClass(AbstractDungeon.player.chosenClass, true).size() > 1;
            }
        };
        wardrobe.setHoveredTexture(TextureManager.getTexture("skindexResources/images/ui/wardrobe_hovered.png"));
        wardrobe.setToggledHoveredTexture(TextureManager.getTexture("skindexResources/images/ui/wardrobe_hovered.png"));


        TextBox textBox = (TextBox) new TextBox(
                SkindexGame.getActivePlayerSkin() != null ? SkindexGame.getActivePlayerSkin().getName() : "",
                62, 0,
                265, 56,
                0.07F, 0.20F){
            @Override
            public boolean isActive() {
                return super.isActive() && wardrobe.isToggled();
            }
        }
        .setRenderColor(Color.WHITE);

        //todo replace with one element
        skinController = new UIElement(286, 1080-828, 55, 56);
        skinController.addChildNCS(textBox);
        skinController.addChildCS(new Interactable(UIThemeManager.getDefaultTheme().arrow_left, 0, 0, 55, 56){
            @Override
            public boolean isActive() {
                return super.isActive() && wardrobe.isToggled();
            }

            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                PlayerSkin previousSkin = SkindexGame.getActivePlayerSkin();
                PlayerSkin playerSkin = SkindexRegistry.getPreviousSkin(previousSkin, true, true);
                if(previousSkin != null){
                    previousSkin.dispose();
                }
                if(playerSkin != null) {
                    playerSkin.loadOnPlayer();
                    textBox.setText(playerSkin.getName());
                }
            }
        });
        skinController.addChildCS(new Interactable(UIThemeManager.getDefaultTheme().arrow_right, 334, 0, 55, 56){
            @Override
            public boolean isActive() {
                return super.isActive() && wardrobe.isToggled();
            }

            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                PlayerSkin previousSkin = SkindexGame.getActivePlayerSkin();
                PlayerSkin playerSkin = SkindexRegistry.getNextSkin(previousSkin, true, true);
                if(previousSkin != null) {
                    previousSkin.dispose();
                }
                if(playerSkin != null){
                    playerSkin.loadOnPlayer();
                    textBox.setText(playerSkin.getName());
                }
            }
        });
    }

    @SpirePatch2(clz = NeowRoom.class, method = "update")
    public static class NeowRoomUpdater{
        public static void Postfix(){
            if(shouldShowWardrobe()){
                initializeElements();

                wardrobe.update();
                skinController.update();
            }
        }
    }

    @SpirePatch2(clz = NeowRoom.class, method = "render")
    public static class NeowRoomRenderer{
        public static void Postfix(SpriteBatch sb){
            if(shouldShowWardrobe()){
                initializeElements();

                wardrobe.render(sb);
                skinController.render(sb);
            }
        }
    }

    public static boolean shouldShowWardrobe(){
        return true;
    }
}
