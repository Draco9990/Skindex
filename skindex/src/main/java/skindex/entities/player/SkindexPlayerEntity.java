package skindex.entities.player;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.skins.player.PlayerSkin;

public interface SkindexPlayerEntity {
    AbstractPlayer.PlayerClass getPlayerClass();

    void setPlayerSkin(PlayerSkin newSkin);
    PlayerSkin getPlayerSkin();
}
