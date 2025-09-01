package skindex.modcompat.skulHeroSlayer.skins.player;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.modcompat.ModManager;
import dLib.util.Reflection;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import skindex.SkindexGame;
import skindex.patches.PlayerLoadAnimationPatcher;
import skindex.skins.entity.player.AbstractPlayerSkin;
import skulmod.SkulMod;
import skulmod.character.LittleBone;

public class LittleBoneAtlasSkin extends AbstractPlayerSkin {
    /** Variables */
    public String atlasDirectoryUrl;
    public String skeletonDirectoryUrl;

    public String defaultAnimName;

    /** Constructors */
    public LittleBoneAtlasSkin(LittleBoneAtlasSkinData skinData) {
        super(skinData);

        this.atlasDirectoryUrl = skinData.atlasDirectoryUrl;
        this.skeletonDirectoryUrl = skinData.skeletonDirectoryUrl;

        this.defaultAnimName = skinData.defaultAnimName;
    }

    @Override
    public boolean loadOnPlayer(AbstractPlayer player) {
        if(!super.loadOnPlayer(player)) return false;
        if(!(player instanceof LittleBone)) return false;

        String currentSkul = SkulMod.CurrentSkull;
        if(currentSkul == null || currentSkul.equals("None")) currentSkul = "LittleBone";
        PlayerLoadAnimationPatcher.LoadAnimationConsumer.resourceDirectoryUrl = resourceDirectory;
        Reflection.invokeMethod("loadAnimation", AbstractDungeon.player, getAtlasUrl(currentSkul), getJsonUrl(currentSkul), 1 / scale);
        AbstractDungeon.player.state.setAnimation(0, defaultAnimName, true);

        return true;
    }

    private String getAtlasUrl(String skul){
        return atlasDirectoryUrl + skul + ".atlas";
    }

    private String getJsonUrl(String skul){
        return skeletonDirectoryUrl + skul + ".json";
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = LittleBone.class, method = "AnimateSkull", requiredModId = ModManager.SkulHeroSlayer.modId, optional = true)
        public static class SkinLoaderPatcher {
            @SpireInsertPatch(locator = Locator.class)
            public static void Insert(LittleBone __instance){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin(__instance);
                if(currentSkin instanceof LittleBoneAtlasSkin){
                    currentSkin.loadOnPlayer();
                }
            }

            private static class Locator extends SpireInsertLocator {
                public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                    Matcher finalMatcher = new Matcher.MethodCallMatcher(LittleBone.class, "loadAnimation");
                    int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                    lines[0]++;
                    return lines;
                }
            }
        }
    }

    public static class LittleBoneAtlasSkinData extends PlayerSkinData {
        /** Variables */
        @SerializedName("atlasUrl")
        public String atlasDirectoryUrl;
        @SerializedName("skeletonUrl")
        public String skeletonDirectoryUrl;
        @SerializedName("defaultAnimName")
        public String defaultAnimName = "IDLE";

    }
}
