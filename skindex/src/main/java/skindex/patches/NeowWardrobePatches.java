package skindex.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowRoom;
import dLib.ui.elements.UIElement;
import dLib.ui.elements.items.Toggle;
import dLib.ui.elements.items.buttons.Button;
import dLib.ui.elements.items.text.TextBox;
import dLib.ui.resources.UICommonResources;
import dLib.util.TextureManager;
import dLib.util.bindings.texture.Tex;
import dLib.util.ui.dimensions.Dim;
import dLib.util.ui.position.Pos;
import skindex.SkindexGame;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.AbstractPlayerSkin;

public class NeowWardrobePatches {
    public static Toggle wardrobe;

    public static UIElement skinController;

    public static void initializeElements(){
        if(wardrobe != null) return;

        wardrobe = new Toggle(
                Tex.stat(TextureManager.getTexture("skindexResources/images/ui/wardrobe.png")),
                Tex.stat(TextureManager.getTexture("skindexResources/images/ui/wardrobe.png")),
                Pos.px(27), Pos.px(1080-726),
                Dim.px(203), Dim.px(374)
        ){
            @Override
            public boolean isActive() {
                return super.isActive() && SkindexRegistry.getSkinsForClass(AbstractDungeon.player.chosenClass, true).size() > 1;
            }
        };
        wardrobe.setHoveredTexture(Tex.stat(TextureManager.getTexture("skindexResources/images/ui/wardrobe_hovered.png")));
        wardrobe.setToggledHoveredTexture(Tex.stat(TextureManager.getTexture("skindexResources/images/ui/wardrobe_hovered.png")));

        TextBox textBox = new TextBox(
                SkindexGame.getActivePlayerSkin() != null ? SkindexGame.getActivePlayerSkin().getName() : "",
                Pos.px(62), Pos.px(0),
                Dim.px(265), Dim.px(56)){
            @Override
            public boolean isActive() {
                return super.isActive() && wardrobe.isToggled();
            }
        };

        //todo replace with one element
        skinController = new UIElement(Pos.px(286), Pos.px(1080-828), Dim.px(55), Dim.px(56));
        skinController.addChild(textBox);
        /*skinController.addChild(new Button(Tex.stat(UICommonResources.arrow_left), Dim.px(55), Dim.px(56)){
            @Override
            public boolean isActive() {
                return super.isActive() && wardrobe.isToggled();
            }

            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                AbstractPlayerSkin previousSkin = SkindexGame.getActivePlayerSkin();
                AbstractPlayerSkin playerSkin = SkindexRegistry.getPreviousSkin(previousSkin, true, true);
                if(playerSkin != null) {
                    playerSkin.loadOnPlayer();
                    textBox.setText(playerSkin.getName());
                }
            }
        });
        skinController.addChild(new Interactable(Tex.stat(UICommonResources.arrow_right), Pos.px(334), Pos.px(0), Dim.px(55), Dim.px(56)){
            @Override
            public boolean isActive() {
                return super.isActive() && wardrobe.isToggled();
            }

            @Override
            protected void onLeftClick() {
                super.onLeftClick();

                AbstractPlayerSkin previousSkin = SkindexGame.getActivePlayerSkin();
                AbstractPlayerSkin playerSkin = SkindexRegistry.getNextSkin(previousSkin, true, true);
                if(playerSkin != null){
                    playerSkin.loadOnPlayer();
                    textBox.setText(playerSkin.getName());
                }
            }
        });*/
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
