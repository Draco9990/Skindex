package skindex.modcompat.skulHeroSlayer.skins.player;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.modcompat.ModManager;
import dLib.util.Reflection;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import skindex.SkindexGame;
import skindex.entities.player.SkindexPlayerAtlasEntity;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.modcompat.skulHeroSlayer.entities.player.SkindexLittleBoneAtlasEntity;
import skindex.patches.PlayerLoadAnimationPatcher;
import skindex.skins.player.PlayerSkin;
import skulmod.SkulMod;
import skulmod.character.LittleBone;

public class LittleBoneAtlasSkin extends PlayerSkin {
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
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        if(!super.loadOnEntity(entity)) return false;

        String currentSkul = null;
        if(entity instanceof SkindexLittleBoneAtlasEntity){
            currentSkul = ((SkindexLittleBoneAtlasEntity) entity).getCurrentSkull();
        }
        if(currentSkul == null || currentSkul.equals("None")) currentSkul = "LittleBone";
        ((SkindexPlayerAtlasEntity) entity).loadAnimation(getAtlasUrl(currentSkul), getJsonUrl(currentSkul), resourceDirectory, 1 / scale);
        ((SkindexPlayerAtlasEntity) entity).getState().setAnimation(0, defaultAnimName, true);

        return true;
    }

    @Override
    public boolean loadOnPlayer() {
        if(!super.loadOnPlayer()) return false;
        if(!(AbstractDungeon.player instanceof LittleBone)) return false;

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
            public static void Insert(){
                PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
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
}
