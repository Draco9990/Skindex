package skindex;

import basemod.*;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.files.JsonDataFileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skindex.files.SkindexUserConfig;
import skindex.modcompat.SkindexModCompat;
import skindex.patches.SkinApplierPatches;
import skindex.registering.*;
import skindex.skins.entity.player.AbstractPlayerSkin;
import skindex.trackers.SkindexUnlockTracker;

@SpireInitializer
public class Skindex implements PostInitializeSubscriber {
    /** Variables */
    public static final Logger logger = LogManager.getLogger(Skindex.class.getName());
    private static String modID;

    /** Constructors */
    public Skindex() {
        BaseMod.subscribe(this);
        BaseMod.subscribe(new SkinApplierPatches());

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

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receivePostInitialize() {
        JsonDataFileManager.registerFileRules(SkindexUnlockTracker.class, SkindexUnlockTracker.makeRules());
        JsonDataFileManager.registerFileRules(SkindexUserConfig.class, SkindexUserConfig.makeRules());

        SkindexDefaultRegistrant.registerAll();
        SkindexModCompat.registerAll();

        SkindexVersionManager.init();
    }

    @SpirePatch2(clz = BaseMod.class, method = "publishPostInitialize")
    public static class PostPostInitialize{
        public static void Postfix(){
            SkindexRegistry.processRegistrants();

            BaseMod.addSaveField(makeID("player_skin"), new CustomSavable<String>() {
                @Override
                public String onSave() {
                    AbstractPlayerSkin playerSkin = SkindexGame.getActivePlayerSkin();
                    if(playerSkin != null){
                        return playerSkin.getId();
                    }

                    return "";
                }

                @Override
                public void onLoad(String s) {
                    if(s != null){
                        AbstractPlayerSkin skinToLoad = SkindexRegistry.getPlayerSkinByClassAndId(AbstractDungeon.player.chosenClass, s);
                        if(skinToLoad != null){
                            SkindexGame.setPlayerSkinRaw(AbstractDungeon.player, skinToLoad);
                        }
                    }
                }
            });
        }
    }
}
