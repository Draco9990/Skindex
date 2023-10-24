package skindex.registering;

import skindex.bundles.Bundle;
import skindex.skins.orb.OrbSkin;

import java.util.List;

public interface SkindexOrbSkinRegistrant extends ISkindexSubscriber{
    List<OrbSkin> getOrbSkinsToRegister();
}
