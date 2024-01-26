package skindex.skins.player;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.util.Reflection;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import skindex.SkindexGame;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.entities.player.SkindexPlayerImageEntity;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneAtlasSkin;
import skulmod.character.LittleBone;

public class PlayerImageSkin extends PlayerSkin{
    /** Variables */
    private Texture image;

    /** Constructors */
    public PlayerImageSkin(PlayerImageSkinData skinData) {
        super(skinData);

        image = loadImageIfExists(skinData.imageUrl);
    }

    /** Methods */
    @Override
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        if(!super.loadOnEntity(entity)) return false;
        if(image == null) return false;
        if(!(entity instanceof SkindexPlayerImageEntity)) return false;

        ((SkindexPlayerImageEntity) entity).setRenderImage(image);

        return true;
    }

    @Override
    public boolean loadOnPlayer() {
        if(!super.loadOnPlayer()) return false;
        if(image == null) return false;

        AbstractDungeon.player.img = image;
        Reflection.setFieldValue("atlas", AbstractDungeon.player, null);

        return true;
    }
}
