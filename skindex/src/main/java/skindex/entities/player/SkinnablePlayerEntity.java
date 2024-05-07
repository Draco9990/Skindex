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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dLib.util.AssetLoader;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerImageSkin;
import skindex.skins.player.PlayerSkin;
import skindex.skins.player.PlayerSpriterSkin;

public class SkinnablePlayerEntity extends AbstractMonster implements SkindexPlayerImageEntity, SkindexPlayerAtlasEntity {
    //region Variables
    protected AbstractPlayer.PlayerClass playerClass;

    protected PlayerSkin currentSkin;

    protected float renderScale = 1;
    //endregion Variables

    //region Constructors
    public SkinnablePlayerEntity(AbstractPlayer.PlayerClass playerClass, float HB_X, float HB_Y, float HB_W, float HB_H){
        this("Dummy", "SKINDEX_DUMMY", playerClass, HB_X, HB_Y, HB_W, HB_H);
    }

    public SkinnablePlayerEntity(String name, String id, AbstractPlayer.PlayerClass playerClass, float HB_X, float HB_Y, float HB_W, float HB_H){
        super(name, id, 9999, HB_X, HB_Y, HB_W, HB_H, null, 9999, 9999);

        this.playerClass = playerClass;
        this.currentSkin = SkindexRegistry.getDefaultPlayerSkinByClass(playerClass, true);
    }
    //endregion Constructors

    //region Class Methods

    @Override
    public AbstractPlayer.PlayerClass getPlayerClass() {
        return playerClass;
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
        if (!this.isDead && !this.escaped) {
            if(currentSkin instanceof PlayerSpriterSkin){
                ((PlayerSpriterSkin) currentSkin).renderModel(sb, (int) (this.drawX + this.animX), (int) (this.drawY + this.animY), flipHorizontal, flipVertical, getScale());
            }
            else if (this.atlas == null) {
                sb.setColor(this.tint.color);
                if (this.img != null) {
                    if(currentSkin instanceof PlayerImageSkin){
                        ((PlayerImageSkin) currentSkin).renderSkinImage(sb, this, img, getScale());
                    }
                    else{
                        sb.draw(this.img, this.drawX - (float)this.img.getWidth() * Settings.xScale / 2.0F + this.animX, this.drawY + this.animY, (float)this.img.getWidth() * Settings.xScale, (float)this.img.getHeight() * Settings.yScale, 0, 0, this.img.getWidth(), this.img.getHeight(), this.flipHorizontal, this.flipVertical);
                    }
                }
            } else {
                this.state.update(Gdx.graphics.getDeltaTime());
                this.state.apply(this.skeleton);
                this.skeleton.updateWorldTransform();
                this.skeleton.setPosition(this.drawX + this.animX, this.drawY + this.animY);
                this.skeleton.setColor((currentSkin != null ? currentSkin.renderColor : this.tint.color));

                this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);

                sb.end();
                CardCrawlGame.psb.begin();
                sr.draw(CardCrawlGame.psb, this.skeleton);
                CardCrawlGame.psb.end();
                sb.begin();
                sb.setBlendFunction(770, 771);
            }

            this.hb.render(sb);
        }
    }

    //endregion

    //region Position

    public void setDrawPosition(float drawX, float drawY){
        this.drawX = drawX;
        this.drawY = drawY;
        if(AbstractDungeon.player != null){
            this.hb.height = AbstractDungeon.player.hb.height;
        }
    }

    //endregion

    //region State Animations

    public AnimationState.TrackEntry addStateAnimation(Integer trackIndex, String animationName, boolean loop, float delay){
        if(stateData == null || state == null) return null;

        Animation animation = stateData.getSkeletonData().findAnimation(animationName);
        if(animation != null){
            return state.addAnimation(trackIndex, animation, loop, delay);
        }

        return null;
    }
    public AnimationState.TrackEntry setStateAnimation(Integer trackIndex, String animationName, boolean loop){
        if(stateData == null || state == null) return null;

        Animation animation = stateData.getSkeletonData().findAnimation(animationName);
        if(animation != null){
            return state.setAnimation(trackIndex, animation, loop);
        }
        return state.getCurrent(trackIndex);
    }

    public void setStateDataMix(String from, String to, Float duration){
        stateData.setMix(from, to, duration);
    }

    //endregion

    //region Player Skin implementation

    @Override
    public void setPlayerSkin(PlayerSkin newSkin) {
        this.currentSkin = newSkin;
    }

    @Override
    public PlayerSkin getPlayerSkin() {
        return currentSkin;
    }

    //endregion

    //region Render Scale

    public SkinnablePlayerEntity setRenderScale(float renderScale){
        this.renderScale = renderScale;
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
        this.atlas = AssetLoader.loadTextureAtlas(atlasUrl, resourceDirectory);
        SkeletonJson json = new SkeletonJson(this.atlas);
        json.setScale(Settings.renderScale * getScale() / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);
    }

    @Override
    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public Skeleton getSkeleton() {
        return skeleton;
    }

    @Override
    public AnimationState getState() {
        return state;
    }

    @Override
    public AnimationStateData getStateData() {
        return stateData;
    }

    //endregion

    //region Player Image Skin Implementation

    @Override
    public void setRenderImage(Texture texture) {
        this.img = texture;
        this.atlas = null;
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
