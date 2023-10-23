package skindex;

import basemod.*;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skindex.modcompat.SkindexModCompat;
import skindex.patches.SkinApplierPatches;
import skindex.registering.SkindexPlayerSkinRegistrant;
import skindex.registering.SkindexRegistry;
import skindex.registering.SkindexUnlockMethodRegistrant;
import skindex.skins.player.PlayerSkin;
import skindex.skins.player.defect.DefectBaseSkin;
import skindex.skins.player.defect.DefectGreenSkin;
import skindex.skins.player.defect.DefectRedSkin;
import skindex.skins.player.defect.DefectYellowSkin;
import skindex.skins.player.ironclad.IroncladBaseSkin;
import skindex.skins.player.ironclad.IroncladBlueSkin;
import skindex.skins.player.ironclad.IroncladGreenSkin;
import skindex.skins.player.ironclad.IroncladYellowSkin;
import skindex.skins.player.silent.SilentBaseSkin;
import skindex.skins.player.silent.SilentBlueSkin;
import skindex.skins.player.silent.SilentRedSkin;
import skindex.skins.player.silent.SilentYellowSkin;
import skindex.skins.player.watcher.*;
import skindex.unlockmethods.AchievementUnlockMethod;
import skindex.unlockmethods.FreeUnlockMethod;
import skindex.unlockmethods.NonUnlockableUnlockMethod;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpireInitializer
public class Skindex implements PostInitializeSubscriber, SkindexUnlockMethodRegistrant, SkindexPlayerSkinRegistrant {
    /** Variables */
    public static final Logger logger = LogManager.getLogger(Skindex.class.getName());
    private static String modID;

    /** Constructors */
    public Skindex() {
        BaseMod.subscribe(this);
        BaseMod.subscribe(new SkinApplierPatches());

        SkindexRegistry.subscribe(this);
        SkindexRegistry.subscribe(new SkindexModCompat());
        setModID("skindex");
    }

    /** Getters and Setters */
    public static void setModID(String ID) {
        modID = ID;
    }
    public static String getModID() {
        return modID;
    }

    /** Methods */
    public static void initialize() {
        logger.info("=========================> Initializing Skindex");
        Skindex defaultmod = new Skindex();
    }

    /** Initializers */
    @Override
    public List<UnlockMethod> getUnlockMethodsToRegister() {
        return Arrays.asList(
                new FreeUnlockMethod(),
                new AchievementUnlockMethod(),
                new NonUnlockableUnlockMethod()
        );
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

                new SilentRedSkin(),
                new SilentBlueSkin(),
                new SilentYellowSkin(),

                new DefectRedSkin(),
                new DefectGreenSkin(),
                new DefectYellowSkin(),

                new WatcherRedSkin(),
                new WatcherGreenSkin(),
                new WatcherBlueSkin(),
                new WatcherYellowSkin()
        );
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receivePostInitialize() {
        SkindexRegistry.processRegistrants();
    }
}
