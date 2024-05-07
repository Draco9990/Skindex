package skindex.skins.misc.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AbstractParticleEffect extends AbstractGameEffect {
    protected float x;
    protected float y;
    protected float vX;
    protected float vY;
    protected float dur_div2;
    protected float dvy;
    protected float dvx;

    protected Texture texture;

    public AbstractParticleEffect(ParticleData data, int xPos, int yPos){

    }

    public AbstractParticleEffect SetScaleMult(float scaleMult){
        this.scale *= scaleMult;
        return this;
    }

    public void update() {
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        this.vY += Gdx.graphics.getDeltaTime() * this.dvy;
        this.vX -= Gdx.graphics.getDeltaTime() * this.dvx;
        this.rotation = -(57.295776F * MathUtils.atan2(this.vX, this.vY)) - 0.0F;
        if (this.duration > this.dur_div2) {
            this.color.a = Interpolation.fade.apply(0.7F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration / this.dur_div2);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(texture, this.x, this.y, (float) texture.getWidth() / 2, (float) texture.getHeight() / 2, (float) texture.getWidth(), (float) texture.getHeight(), this.scale, this.scale, this.rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
