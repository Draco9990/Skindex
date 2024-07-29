package skindex.util;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import dLib.util.TextureManager;

public class SkindexUI {
    //region Preview Screen
    public static class PreviewScreen{
        //TODO enusre cleanup of textures
        public static Texture previewScreenBG() { return ImageMaster.loadImage("skindexResources/images/ui/preview/PreviewListBg.png"); }

        public static Texture previewItemUnlockedBorder() { return ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemUnlockedBorder.png"); }
        public static Texture previewItemLockedBorder() { return ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemLockedBorder.png"); }

        public static Texture previewItemLockedOverlay() { return ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemLockedOverlay.png"); }

        public static Texture previewItemOriginalUnlockGem() { return ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemOriginalUnlockGem.png"); }
        public static Texture previewItemReReleaseUnlockGem() { return ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemReReleaseUnlockGem.png"); }

        public static Texture previewItemFavouritedGem() { return ImageMaster.loadImage("skindexResources/images/ui/preview/favouriteItem.png"); }

        public static Texture previewItemUnlockDescriptionBackground() { return ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemUnlockDescriptionBackground.png"); }
    }
    //endregion
}
