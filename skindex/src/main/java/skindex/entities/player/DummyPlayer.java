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
import skindex.skins.player.PlayerImageSkin;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.skins.player.PlayerSpriterSkin;

public class DummyPlayer extends AbstractMonster implements SkindexPlayerImageEntity, SkindexPlayerAtlasEntity {
    //region Variables
    protected AbstractPlayerSkin currentSkin;

    protected float renderScale = 1;

    public AbstractPlayer source;
    //endregion Variables

    //region Constructors
    public DummyPlayer(AbstractPlayer.PlayerClass playerClass){
        super("Dummy", "SKINDEX_DUMMY", 9999, 0, 0, 0, 0, null, 9999, 9999);

        source = CardCrawlGame.characterManager.getCharacter(playerClass);
        SkindexRegistry.getDefaultPlayerSkinByClass(playerClass, true).loadOnEntity(this);
    }
    //endregion Constructors

    //region Class Methods

    @Override
    public AbstractPlayer.PlayerClass getPlayerClass() {
        return source.chosenClass;
    }

    //region Update & Render

    @Override
    public void update() {
        if(this.currentSkin != null) this.currentSkin.update(this);
        if(this.hb != null) this.hb.update();

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

    //region Player Skin implementation

    @Override
    public void setPlayerSkin(AbstractPlayerSkin newSkin) {
        this.currentSkin = newSkin;
    }

    @Override
    public AbstractPlayerSkin getPlayerSkin() {
        return currentSkin;
    }

    //endregion

    //region Render Scale

    public DummyPlayer setRenderScale(float renderScale){
        this.renderScale = renderScale;
        currentSkin.loadOnEntity(this);
        return this;
    }

    @Override
    public float getScale() {
        return renderScale;
    }

    //endregion

    //region Atlas Skin Implementation

    @Override
    public void loadAnimation(String atlasUrl, String skeletonUrl, String resourceDirectory, float scale) {
        TextureAtlas atlas = AssetLoader.loadTextureAtlas(atlasUrl, resourceDirectory);
        Reflection.setFieldValue("atlas", source, atlas);

        SkeletonJson json = new SkeletonJson(atlas);
        json.setScale(Settings.renderScale * getScale() / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
        Skeleton skeleton = new Skeleton(skeletonData);
        skeleton.setColor(Color.WHITE);
        Reflection.setFieldValue("skeleton", source, skeleton);

        AnimationStateData stateData = new AnimationStateData(skeletonData);
        Reflection.setFieldValue("stateData", source, stateData);
        source.state = new AnimationState(stateData);
    }

    @Override
    public TextureAtlas getAtlas(){
        return Reflection.getFieldValue("atlas", source);
    }
    @Override
    public Skeleton getSkeleton(){
        return Reflection.getFieldValue("skeleton", source);
    }
    @Override
    public AnimationState getState(){
        return source.state;
    }
    @Override
    public AnimationStateData getStateData(){
        return Reflection.getFieldValue("stateData", source);
    }

    //endregion

    //region Player Image Skin Implementation

    @Override
    public void setRenderImage(Texture texture) {
        this.source.img = texture;
        Reflection.setFieldValue("atlas", source, null);
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
