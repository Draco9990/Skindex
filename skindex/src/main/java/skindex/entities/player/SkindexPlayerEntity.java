package skindex.entities.player;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.AbstractPlayerSkin;

public interface SkindexPlayerEntity {
    AbstractPlayer.PlayerClass getPlayerClass();

    void setPlayerSkin(AbstractPlayerSkin newSkin);
    AbstractPlayerSkin getPlayerSkin();
}
