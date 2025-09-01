package skindex.modcompat.downfall.skins.player.gremlins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import dLib.modcompat.ModManager;
import dLib.util.AssetLoader;
import dLib.util.Reflection;
import gremlin.GremlinMod;
import gremlin.characters.GremlinCharacter;
import gremlin.orbs.GremlinStandby;
import skindex.SkindexGame;
import skindex.patches.PlayerLoadAnimationPatcher;
import skindex.skins.entity.player.AbstractPlayerSkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GremlinsAtlasSkin extends AbstractPlayerSkin {
    /** Variables */
    private static String[] gremlinList = new String[]{
            "angry",
            "fat",
            "shield",
            "sneak",
            "wizard",
            "nob"
    };

    private HashMap<String, GremlinSkin> gremlinSkinMap = new HashMap<>();
    private HashMap<String, Skeleton> gremlinSkeletonCache = new HashMap<>();

    /** Constructors */
    public GremlinsAtlasSkin(GremlinsAtlasSkinData skinData) {
        super(skinData);

        for(GremlinsAtlasSkinData.GremlinAtlasSkinData gremlinSkinData : skinData.gremlins){
            gremlinSkinMap.put(gremlinSkinData.gremlinId, new GremlinSkin(gremlinSkinData));
        }
    }

    /** Getters and Setters */
    public GremlinSkin getSkinForGremlin(String gremlinId){
        return gremlinSkinMap.get(gremlinId);
    }

    @Override
    public boolean loadOnPlayer(AbstractPlayer player){
        if (!(player instanceof GremlinCharacter)) {
            return false;
        }

        GremlinCharacter gremlinChar = (GremlinCharacter) player;

        String gremlin = gremlinChar.currentGremlin == null ? gremlinChar.mobState.getFrontGremlin() : gremlinChar.currentGremlin;
        return loadOnPlayer(player, gremlin, getGremlinAnimationName(gremlin));
    }

    public boolean loadOnPlayer(AbstractPlayer player, String gremlin, String animation){
        if(!super.loadOnPlayer(player)) {
            return false;
        }
        if (!(player instanceof GremlinCharacter)) {
            return false;
        }

        GremlinCharacter gremlinChar = (GremlinCharacter) player;
        if(gremlinChar.nob){
            gremlin = "nob";
        }

        GremlinSkin gremlinSkin = getSkinForGremlin(gremlin);
        String atlasLoc = gremlinSkin.atlasUrl;
        String jsonLoc = gremlinSkin.skeletonUrl;

        PlayerLoadAnimationPatcher.LoadAnimationConsumer.resourceDirectoryUrl = gremlinSkin.resourceDirectory;
        Reflection.invokeMethod("loadAnimation", gremlinChar, atlasLoc, jsonLoc, 1 / scale);
        AnimationState.TrackEntry trackEntry = gremlinChar.state.setAnimation(0, animation, true);
        trackEntry.setTime(trackEntry.getEndTime() * MathUtils.random());

        return true;
    }

    /** Misc methods */
    public static String getGremlinAnimationName(String gremlin){
        if(Objects.equals(gremlin, "nob")) return "animation";
        else return GremlinMod.getGremlinOrb(gremlin, 0).animationName;
    }

    private Skeleton getStandbyGremlinSkeleton(String gremlin) {
        if(gremlinSkeletonCache == null) gremlinSkeletonCache = new HashMap<>();
        if (!gremlinSkeletonCache.containsKey(gremlin)) {
            GremlinSkin gremlinSkin = getSkinForGremlin(gremlin);
            String atlasString = gremlinSkin.atlasUrl;
            String jsonString = gremlinSkin.skeletonUrl;

            TextureAtlas texture = AssetLoader.loadTextureAtlas(atlasString, gremlinSkin.resourceDirectory);
            SkeletonJson json = new SkeletonJson(texture);
            json.setScale(Settings.scale * scale);
            SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(jsonString));

            gremlinSkeletonCache.put(gremlin, new Skeleton(skeletonData));
        }

        return gremlinSkeletonCache.get(gremlin);
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = GremlinStandby.class, method = SpirePatch.CONSTRUCTOR, requiredModId = ModManager.Downfall.modId, optional = true)
        public static class StandbyGremlinSkinLoader{
            public static void Postfix(GremlinStandby __instance, String assetFolder){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin instanceof GremlinsAtlasSkin){
                    __instance.skeleton = ((GremlinsAtlasSkin) currentSkin).getStandbyGremlinSkeleton(assetFolder);
                }
            }
        }

        @SpirePatch2(clz = GremlinCharacter.class, method = "swapBody", requiredModId = ModManager.Downfall.modId, optional = true)
        public static class ReloadAnimPatcher{
            public static void Postfix(GremlinCharacter __instance){
                AbstractPlayerSkin activeSkin = SkindexGame.getActivePlayerSkin(__instance);
                if(activeSkin != null){
                    activeSkin.loadOnPlayer();
                }
            }
        }

        @SpirePatch(clz = GremlinCharacter.class, method = "becomeNob", requiredModId = ModManager.Downfall.modId, optional = true)
        public static class BecomeNobPatcher{
            public static void Postfix(GremlinCharacter __instance){
                AbstractPlayerSkin activeSkin = SkindexGame.getActivePlayerSkin(__instance);
                if(activeSkin != null){
                    activeSkin.loadOnPlayer();
                }
            }
        }
    }

    /** Subclasses */
    public static class GremlinSkin{
        /** Variables */
        public String gremlinId;

        public String atlasUrl;
        public String skeletonUrl;
        public String resourceDirectory;

        /** Constructors */
        public GremlinSkin(GremlinsAtlasSkinData.GremlinAtlasSkinData skinData){
            gremlinId = skinData.gremlinId;
            atlasUrl = skinData.atlasUrl;
            skeletonUrl = skinData.skeletonUrl;
            resourceDirectory = skinData.resourceDirectoryUrl;
        }
    }

    public static class GremlinsAtlasSkinData extends PlayerSkinData {
        /** Variables */
        @SerializedName("gremlins")
        public ArrayList<GremlinAtlasSkinData> gremlins = new ArrayList<>();

        public static class GremlinAtlasSkinData{
            /** Variables */
            @SerializedName("gremlin")
            public String gremlinId;

            @SerializedName("atlasUrl")
            public String atlasUrl;
            @SerializedName("skeletonUrl")
            public String skeletonUrl;
            @SerializedName("resourceDirectoryUrl")
            public String resourceDirectoryUrl;
        }
    }
}
