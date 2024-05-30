package skindex.skins.orb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.BobEffect;

public class OrbAtlasSkin extends OrbSkin {
    //region Variables

    protected TextureAtlas atlas;
    protected Skeleton skeleton;
    protected AnimationState state;
    protected AnimationStateData stateData;

    protected SkeletonMeshRenderer sr;

    //endregion Variables

    public OrbAtlasSkin(OrbAtlasSkinData orbSkinData){
        super(orbSkinData);

        sr = new SkeletonMeshRenderer();
        sr.setPremultipliedAlpha(true);

        loadAnimation(orbSkinData);
    }

    //region Constructors

    //endregion Constructors

    //region Methods


    @Override
    public void update() {
        super.update();
        this.skeleton.updateWorldTransform();
        this.skeleton.update(Gdx.graphics.getDeltaTime());
        this.state.update(Gdx.graphics.getDeltaTime());
        this.state.apply(this.skeleton);
    }

    @Override
    public void render(AbstractOrb instance, SpriteBatch sb, BobEffect bobEffect, float angle, float scale) {
        this.skeleton.setPosition(instance.cX, instance.cY);
        sb.end();
        CardCrawlGame.psb.begin();
        sr.draw(CardCrawlGame.psb, this.skeleton);
        CardCrawlGame.psb.end();
        sb.begin();
        sb.setBlendFunction(770, 771);
    }

    protected void loadAnimation(OrbAtlasSkinData orbSkinData) {
        this.atlas = new TextureAtlas(Gdx.files.internal(orbSkinData.atlasUrl));
        SkeletonJson json = new SkeletonJson(this.atlas);
        json.setScale(Settings.renderScale * orbSkinData.scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(orbSkinData.skeletonUrl));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);

        state.setAnimation(0, orbSkinData.defaultAnimName, true);
        state.setTimeScale(orbSkinData.defaultTimeScale);
    }

    //endregion Methods
}
