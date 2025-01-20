package skindex.ui.elements;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import dLib.util.TextureManager;
import dLib.util.bindings.texture.textureresource.ITextureSource;

public class SkindexUICommonResources implements ITextureSource {
    //region Item Preview

    public static Texture itemPreviewBg = ImageMaster.loadImage("skindexResources/images/ui/preview/PreviewListBg.png");

    public static Texture itemPreviewDefaultIcon = ImageMaster.loadImage("skindexResources/images/ui/preview/defaultPreviewIcon.png");
    public static Texture itemPreviewLockedItemOverlay = ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemLockedOverlay.png");

    public static Texture itemPreviewBorderLocked = ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemLockedBorder.png");
    public static Texture itemPreviewBorderUnlocked = ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemUnlockedBorder.png");

    public static Texture itemPreviewUnlockGemOriginal = ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemOriginalUnlockGem.png");
    public static Texture itemPreviewUnlockGemReRelease = ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemReReleaseUnlockGem.png");

    public static Texture itemPreviewFavourite = ImageMaster.loadImage("skindexResources/images/ui/preview/favouriteItem.png");

    public static Texture itemPreviewUnlockDescriptionBackground = ImageMaster.loadImage("skindexResources/images/ui/preview/previewItemUnlockDescriptionBackground.png");
}
