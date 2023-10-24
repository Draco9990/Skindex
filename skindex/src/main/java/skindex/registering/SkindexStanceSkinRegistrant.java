package skindex.registering;

import skindex.skins.orb.OrbSkin;
import skindex.skins.stances.StanceSkin;

import java.util.List;

public interface SkindexStanceSkinRegistrant extends ISkindexSubscriber{
    List<StanceSkin> getStanceSkinsToRegister();
}
