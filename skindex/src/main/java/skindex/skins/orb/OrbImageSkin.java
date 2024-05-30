package skindex.skins.orb;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.BobEffect;

public class OrbImageSkin extends OrbSkin {
    //region Variables

    public Texture orbImage;

    //endregion Variables

    public OrbImageSkin(OrbImageSkinData orbSkinData){
        super(orbSkinData);

        orbImage = loadImageIfExists(orbSkinData.orbImageUrl);
    }

    //region Constructors

    //endregion Constructors

    //region Methods

    @Override
    public void render(AbstractOrb instance, SpriteBatch sb, BobEffect bobEffect, float angle, float scale) {
        super.render(instance, sb, bobEffect, angle, scale);
        sb.setColor(Color.WHITE);
        sb.draw(getTexture(), instance.cX - 48.0F, instance.cY - 48.0F + bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, scale * 1.2f, scale * 1.2f, 0, 0, 0, 96, 96, false, false);
    }

    protected Texture getTexture(){
        return orbImage;
    }

    //endregion Methods
}
