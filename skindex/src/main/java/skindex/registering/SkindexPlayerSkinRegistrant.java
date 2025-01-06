package skindex.registering;

import skindex.skins.player.AbstractPlayerSkin;

import java.util.List;

public interface SkindexPlayerSkinRegistrant extends ISkindexSubscriber{
    List<AbstractPlayerSkin> getDefaultPlayerSkinsToRegister();
    List<AbstractPlayerSkin> getPlayerSkinsToRegister();
}
