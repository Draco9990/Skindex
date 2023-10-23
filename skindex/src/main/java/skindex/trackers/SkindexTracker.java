package skindex.trackers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import skindex.bundles.Bundle;
import skindex.skins.player.PlayerSkin;

public interface SkindexTracker {
    String getId();

    boolean hasSkin(PlayerSkin s);
    boolean unlockSkin(PlayerSkin s);

    boolean hasBundle(Bundle b);
    boolean unlockBundle(Bundle b);
}
