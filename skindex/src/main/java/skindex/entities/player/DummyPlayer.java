package skindex.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dLib.util.AssetLoader;
import dLib.util.Reflection;
import skindex.registering.SkindexRegistry;
import skindex.skins.entity.player.PlayerImageSkin;
import skindex.skins.entity.player.AbstractPlayerSkin;
import skindex.skins.entity.player.PlayerSpriterSkin;

public class DummyPlayer extends AbstractMonster {
    //region Variables
    protected AbstractPlayerSkin currentSkin;

    protected float renderScale = 1;

    public AbstractPlayer source;
    //endregion Variables

    //region Constructors
    public DummyPlayer(AbstractPlayer.PlayerClass playerClass){
        super("Dummy", "SKINDEX_DUMMY", 9999, 0, 0, 0, 0, null, 9999, 9999);

        source = CardCrawlGame.characterManager.getCharacter(playerClass);
        SkindexRegistry.getDefaultPlayerSkinByClass(playerClass, true).loadOnPlayer(source);
    }
    //endregion Constructors

    //region Class Methods

    //region Update & Render

    @Override
    public void update() {
        if(this.currentSkin != null){
            this.currentSkin.update(source);
        }
        if(this.hb != null) {
            this.hb.update();
        }

        this.updateReticle();
        this.updateAnimations();
        this.tint.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        Color origColor = source.tint.color;

        if(currentSkin instanceof PlayerSpriterSkin){
            ((PlayerSpriterSkin) currentSkin).renderModel(sb, (int) (this.source.drawX + this.source.animX), (int) (this.source.drawY + this.source.animY), flipHorizontal, flipVertical, getScale());
        }
        else if(Reflection.getFieldValue("atlas", source) != null){
            if(currentSkin != null){
                source.tint.color = currentSkin.renderColor;
            }

            source.flipVertical = this.flipVertical;
            source.flipHorizontal = this.flipHorizontal;
            source.renderPlayerImage(sb);
            source.tint.color = origColor;
        }
        else if(source.img != null){
            if(currentSkin instanceof PlayerImageSkin){
                ((PlayerImageSkin) currentSkin).renderSkinImage(sb, this, source.img, getScale());
            }
            else{
                sb.draw(source.img, this.drawX - (float)source.img.getWidth() * Settings.xScale / 2.0F + this.animX, this.drawY + this.animY, (float)source.img.getWidth() * Settings.xScale, (float)source.img.getHeight() * Settings.yScale, 0, 0, source.img.getWidth(), source.img.getHeight(), this.flipHorizontal, this.flipVertical);
            }
        }

        this.hb.render(sb);
        this.healthHb.render(sb);
    }

    //endregion

    //region Position

    public void setDrawPosition(float drawX, float drawY){
        source.drawX = drawX;
        source.drawY = drawY;

        this.drawX = drawX;
        this.drawY = drawY;
    }

    //endregion

    //region State Animations

    public AnimationState.TrackEntry addStateAnimation(Integer trackIndex, String animationName, boolean loop, float delay){
        if(source == null || source.state == null) return null;

        Animation animation = source.state.getData().getSkeletonData().findAnimation(animationName);
        if(animation != null){
            return source.state.addAnimation(trackIndex, animation, loop, delay);
        }

        return null;
    }
    public AnimationState.TrackEntry setStateAnimation(Integer trackIndex, String animationName, boolean loop) {
        Animation animation = source.state.getData().getSkeletonData().findAnimation(animationName);
        if(animation != null){
            return source.state.setAnimation(trackIndex, animation, loop);
        }
        return source.state.getCurrent(trackIndex);
    }

    public void setStateDataMix(String from, String to, Float duration) {
        ((AnimationStateData)Reflection.getFieldValue("stateData", source)).setMix(from, to, duration);
    }

    //endregion

    //region Render Scale

    public DummyPlayer setRenderScale(float renderScale){
        this.renderScale = renderScale;
        currentSkin.loadOnPlayer(source);
        return this;
    }

    public float getScale() {
        return renderScale;
    }

    //endregion

    //region AbstractMonster Overrides

    @Override
    public void takeTurn() {

    }


    @Override
    protected void getMove(int i) {

    }

    //endregion

    //endregion Class Methods
}
