package skindex.registering;

import skindex.skins.cards.CardSkin;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;
import java.util.List;

public interface SkindexPlayerSkinRegistrant extends ISkindexSubscriber{
    List<PlayerSkin> getDefaultPlayerSkinsToRegister();
    List<PlayerSkin> getPlayerSkinsToRegister();
}
