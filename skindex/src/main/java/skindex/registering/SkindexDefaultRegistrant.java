package skindex.registering;

import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.rebelai.*;
import skindex.skins.player.PlayerSkin;
import skindex.skins.player.defect.*;
import skindex.skins.player.ironclad.*;
import skindex.skins.player.silent.*;
import skindex.skins.player.watcher.*;
import skindex.trackers.SkindexDefaultTracker;
import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkindexDefaultRegistrant implements SkindexTrackerRegistrant, SkindexUnlockMethodRegistrant, SkindexPlayerSkinRegistrant, SkindexOrbSkinRegistrant {
    /** Registrants */
    @Override
    public List<SkindexTracker> getTrackersToRegister(int saveSlot) {
        return Arrays.asList(
                SkindexDefaultTracker.get(saveSlot)
        );
    }

    @Override
    public List<UnlockMethod> getUnlockMethodsToRegister() {
        return Arrays.asList(
                new FreeUnlockMethod(),
                new AchievementUnlockMethod(),
                new NonUnlockableUnlockMethod()
        );
    }

    @Override
    public List<OrbSkin> getOrbSkinsToRegister() {
        List<OrbSkin> orbSkins = new ArrayList<>();
        orbSkins.addAll(DefectRebelAIOrbSkinSet.collectOrbSkins());
        return orbSkins;
    }

    @Override
    public List<PlayerSkin> getDefaultPlayerSkinsToRegister() {
        return Arrays.asList(
                new IroncladBaseSkin(),
                new SilentBaseSkin(),
                new DefectBaseSkin(),
                new WatcherBaseSkin()
        );
    }
    @Override
    public List<PlayerSkin> getPlayerSkinsToRegister() {
        return Arrays.asList(
                new IroncladGreenSkin(),
                new IroncladBlueSkin(),
                new IroncladYellowSkin(),
                new IroncladPaperSkin(),
                new IroncladSlaverBlueSkin(),
                new IroncladSlaverRedSkin(),
                new IroncladAstrologerSkin(),

                new SilentRedSkin(),
                new SilentBlueSkin(),
                new SilentYellowSkin(),
                new SilentPaperSkin(),
                new SilentCultistSkin(),

                new DefectRedSkin(),
                new DefectGreenSkin(),
                new DefectYellowSkin(),
                new DefectPaperSkin(),
                new DefectRebelAISkin(),

                new WatcherRedSkin(),
                new WatcherGreenSkin(),
                new WatcherBlueSkin(),
                new WatcherYellowSkin(),
                new WatcherPaperSkin()
        );
    }
}
