package skindex.skins.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.GifDecoder;
import dLib.util.Reflection;
import dLib.util.helpers.TextureHelpers;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.entities.player.SkindexPlayerImageEntity;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class PlayerGifSkin extends PlayerImageSkin {
    /** Variables */
    private GifDecoder gifDecoder;
    private Texture gifTexture;

    private int currentFrameIndex = 0;
    private float elapsedTime = 0;

    /** Constructors */
    public PlayerGifSkin(PlayerGifSkinData skinData) {
        super(skinData);

        gifDecoder = new GifDecoder();
        FileHandle fileHandle = Gdx.files.internal(skinData.gifUrl);
        InputStream inputStream = fileHandle.read();
        gifDecoder.read(inputStream);
        BufferedImage bufferedImage = gifDecoder.getFrame(0);
        gifTexture = TextureHelpers.convertBufferedImageToTexture(bufferedImage);

        try{
            inputStream.close();
        }catch (Exception ignored){}
    }

    /** Update */
    @Override
    public void update(AbstractCreature updateSource) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        float frameDelay = gifDecoder.getDelay(currentFrameIndex) / 1000f;

        if (elapsedTime >= frameDelay) {
            elapsedTime -= frameDelay;
            currentFrameIndex = (currentFrameIndex + 1) % gifDecoder.getFrameCount();
        }

        BufferedImage bufferedImage = gifDecoder.getFrame(currentFrameIndex);
        if(gifTexture != null){
            gifTexture.dispose();
        }
        gifTexture = TextureHelpers.convertBufferedImageToTexture(bufferedImage);
        super.update(updateSource);
    }

    /** Image */
    @Override
    protected Texture getImageToRender() {
        return gifTexture;
    }
}
