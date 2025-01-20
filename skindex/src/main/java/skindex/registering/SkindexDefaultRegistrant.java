package skindex.registering;

import skindex.skins.orb.monsterhunter.MonsterHunterOrbSkinSet;
import skindex.skins.orb.rebelai.*;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.skins.player.defect.*;
import skindex.skins.player.ironclad.*;
import skindex.skins.player.silent.*;
import skindex.skins.player.watcher.*;
import skindex.skins.stance.WatcherMusicStanceSkinSet;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.NonUnlockableUnlockMethod;

import java.util.List;

public class SkindexDefaultRegistrant {
    public static void registerAll(){
        registerUnlockMethods();

        registerOrbSkins();
        registerStanceSkins();

        registerDefaultPlayerSkins();
        registerPlayerSkins();
    }

    public static void registerUnlockMethods() {
        SkindexRegistry.registerUnlockMethod(new FreeUnlockMethod());
        SkindexRegistry.registerUnlockMethod(new AchievementUnlockMethod());
        SkindexRegistry.registerUnlockMethod(new NonUnlockableUnlockMethod());
    }

    public static void registerOrbSkins() {
        RebelAIOrbSkinSet.registerOrbSkins();
        MonsterHunterOrbSkinSet.registerOrbSkins();
    }

    public static void registerStanceSkins() {
        WatcherMusicStanceSkinSet.registerStanceSkins();
    }

    public static void registerDefaultPlayerSkins() {
        SkindexRegistry.registerPlayerSkin(new IroncladBaseSkin());
        SkindexRegistry.registerPlayerSkin(new SilentBaseSkin());
        SkindexRegistry.registerPlayerSkin(new DefectBaseSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherBaseSkin());
    }
    public static void registerPlayerSkins() {
        SkindexRegistry.registerPlayerSkin(new IroncladGreenSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladBlueSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladYellowSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladPaperSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladSlaverBlueSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladSlaverRedSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladCyberSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladAstrologerSkin());
        SkindexRegistry.registerPlayerSkin(new IroncladFormSkin());

        SkindexRegistry.registerPlayerSkin(new SilentRedSkin());
        SkindexRegistry.registerPlayerSkin(new SilentBlueSkin());
        SkindexRegistry.registerPlayerSkin(new SilentYellowSkin());
        SkindexRegistry.registerPlayerSkin(new SilentPaperSkin());
        SkindexRegistry.registerPlayerSkin(new SilentCultistSkin());
        SkindexRegistry.registerPlayerSkin(new SilentLastDanceSkin());
        SkindexRegistry.registerPlayerSkin(new SilentAstrologerSkin());
        SkindexRegistry.registerPlayerSkin(new SilentFormSkin());

        SkindexRegistry.registerPlayerSkin(new DefectRedSkin());
        SkindexRegistry.registerPlayerSkin(new DefectGreenSkin());
        SkindexRegistry.registerPlayerSkin(new DefectYellowSkin());
        SkindexRegistry.registerPlayerSkin(new DefectPaperSkin());
        SkindexRegistry.registerPlayerSkin(new DefectClawGodSkin());
        SkindexRegistry.registerPlayerSkin(new DefectRebelAISkin());
        SkindexRegistry.registerPlayerSkin(new DefectMonsterHunterSkin());
        SkindexRegistry.registerPlayerSkin(new DefectFormSkin());
        SkindexRegistry.registerPlayerSkin(new DefectAstrologerSkin());

        SkindexRegistry.registerPlayerSkin(new WatcherRedSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherGreenSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherBlueSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherYellowSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherPaperSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherTimeEaterSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherMusicSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherFormSkin());
        SkindexRegistry.registerPlayerSkin(new WatcherAstrologerSkin());
    }
}
