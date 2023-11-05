package skindex;

import basemod.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skindex.modcompat.SkindexModCompat;
import skindex.patches.SkinApplierPatches;
import skindex.registering.*;

@SpireInitializer
public class Skindex{
    /** Variables */
    public static final Logger logger = LogManager.getLogger(Skindex.class.getName());
    private static String modID;

    /** Constructors */
    public Skindex() {
        BaseMod.subscribe(new SkinApplierPatches());

        SkindexRegistry.subscribe(new SkindexDefaultRegistrant());
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

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @SpirePatch2(clz = BaseMod.class, method = "publishPostInitialize")
    public static class PostPostInitialize{
        public static void Postfix(){
            SkindexRegistry.processRegistrants();
        }
    }
}
