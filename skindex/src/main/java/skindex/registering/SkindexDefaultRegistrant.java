package skindex.registering;

import skindex.bundles.Bundle;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.skins.orb.OrbSkin;
import skindex.skins.orb.monsterhunter.MonsterHunterOrbSkinSet;
import skindex.skins.orb.rebelai.*;
import skindex.skins.player.AbstractPlayerSkin;
import skindex.skins.player.defect.*;
import skindex.skins.player.ironclad.*;
import skindex.skins.player.silent.*;
import skindex.skins.player.watcher.*;
import skindex.skins.stance.WatcherMusicStanceSkinSet;
import skindex.skins.stances.StanceSkin;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkindexDefaultRegistrant implements ISkindexItemRegistrant {
    @Override
    public List<UnlockMethod> getUnlockMethodsToRegister() {
        return Arrays.asList(
                new FreeUnlockMethod(),
                new AchievementUnlockMethod(),
                new NonUnlockableUnlockMethod()
        );
    }

    @Override
    public List<AbstractCustomizableItem> getItemsToRegister() {
        List<AbstractCustomizableItem> items = new ArrayList<>();

        items.addAll(getOrbSkinsToRegister());

        items.addAll(getStanceSkinsToRegister());

        items.addAll(getDefaultPlayerSkinsToRegister());
        items.addAll(getPlayerSkinsToRegister());

        return items;
    }

    public List<OrbSkin> getOrbSkinsToRegister() {
        List<OrbSkin> orbSkins = new ArrayList<>();
        orbSkins.addAll(RebelAIOrbSkinSet.collectOrbSkins());
        orbSkins.addAll(MonsterHunterOrbSkinSet.collectOrbSkins());
        return orbSkins;
    }

    public List<StanceSkin> getStanceSkinsToRegister() {
        List<StanceSkin> orbSkins = new ArrayList<>();
        orbSkins.addAll(WatcherMusicStanceSkinSet.collectStanceSkins());
        return orbSkins;
    }

    public List<AbstractPlayerSkin> getDefaultPlayerSkinsToRegister() {
        return Arrays.asList(
                new IroncladBaseSkin(),
                new SilentBaseSkin(),
                new DefectBaseSkin(),
                new WatcherBaseSkin()
        );
    }
    public List<AbstractPlayerSkin> getPlayerSkinsToRegister() {
        return Arrays.asList(
                new IroncladGreenSkin(),
                new IroncladBlueSkin(),
                new IroncladYellowSkin(),
                new IroncladPaperSkin(),
                new IroncladSlaverBlueSkin(),
                new IroncladSlaverRedSkin(),
                new IroncladCyberSkin(),
                new IroncladAstrologerSkin(),
                new IroncladFormSkin(),

                new SilentRedSkin(),
                new SilentBlueSkin(),
                new SilentYellowSkin(),
                new SilentPaperSkin(),
                new SilentCultistSkin(),
                new SilentLastDanceSkin(),
                new SilentAstrologerSkin(),
                new SilentFormSkin(),

                new DefectRedSkin(),
                new DefectGreenSkin(),
                new DefectYellowSkin(),
                new DefectPaperSkin(),
                new DefectClawGodSkin(),
                new DefectRebelAISkin(),
                new DefectMonsterHunterSkin(),
                new DefectFormSkin(),
                new DefectAstrologerSkin(),

                new WatcherRedSkin(),
                new WatcherGreenSkin(),
                new WatcherBlueSkin(),
                new WatcherYellowSkin(),
                new WatcherPaperSkin(),
                new WatcherTimeEaterSkin(),
                new WatcherMusicSkin(),
                new WatcherFormSkin(),
                new WatcherAstrologerSkin()
        );
    }
}
