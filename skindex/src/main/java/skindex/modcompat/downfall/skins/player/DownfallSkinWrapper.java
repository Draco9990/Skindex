package skindex.modcompat.downfall.skins.player;

import reskinContent.skinCharacter.AbstractSkin;
import skindex.skins.entity.player.PlayerAtlasSkin;

public class DownfallSkinWrapper extends PlayerAtlasSkin {
    public boolean isOriginal;

    /** Constructors */
    public DownfallSkinWrapper(DownfallSkinWrapperData skinData) {
        super(skinData);
    }

    /** Skin Data */
    public static class DownfallSkinWrapperData extends PlayerAtlasSkinData{
        public DownfallSkinWrapperData(AbstractSkin playerSkin){
            this.atlasUrl = playerSkin.atlasURL;
            this.skeletonUrl = playerSkin.jsonURL;

            this.shoulderIMG = playerSkin.getSHOULDER1();
            this.shoulder2IMG = playerSkin.getSHOULDER2();
            this.corpseIMG = playerSkin.getCORPSE();

            this.id = playerSkin.NAME;
            this.name = playerSkin.NAME;

            scale = 1 / playerSkin.renderscale;
        }
    }
}
