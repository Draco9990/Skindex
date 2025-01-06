package skindex.modcompat.downfall.skins.player.collector;

import collector.CollectorChar;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GlowyFireEyesEffect;
import com.megacrit.cardcrawl.vfx.StaffFireEffect;
import dLib.modcompat.ModManager;
import dLib.util.Reflection;
import skindex.SkindexGame;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.AbstractPlayerSkin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CollectorAtlasSkin extends PlayerAtlasSkin {
    /** Variables */
    private ArrayList<Texture> fireImages = new ArrayList<>();
    private ArrayList<Color> fireColors = new ArrayList<>();
    private boolean noFire = false;

    private static Random rand = new Random();

    /** Constructors */
    public CollectorAtlasSkin(CollectorAtlasSkinData skinData) {
        super(skinData);

        defaultAnimName = "idle";

        for(String fireTexture : skinData.fireImageUrls){
            Texture fire = loadImageIfExists(fireTexture);
            if(fire != null) fireImages.add(fire);
        }

        for(String fireColor : skinData.fireColors){
            fireColors.add(Color.valueOf(fireColor));
        }

        this.noFire = skinData.noFire;
    }

    /** Misc Methods */
    private static void patchFire(Object o){
        if(Patches.FireRenderPatches.renderingPlayer){
            AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
            if(currentSkin instanceof CollectorAtlasSkin){
                CollectorAtlasSkin collectorAtlasSkin = (CollectorAtlasSkin) currentSkin;

                if(collectorAtlasSkin.noFire){
                    Reflection.setFieldValue("duration", o, 0);
                }

                if(!collectorAtlasSkin.fireImages.isEmpty()){
                    int indx = rand.nextInt(collectorAtlasSkin.fireImages.size());
                    Texture fireImage = collectorAtlasSkin.fireImages.get(indx);
                    Reflection.setFieldValue("img", o, fireImage);
                }

                if(!collectorAtlasSkin.fireColors.isEmpty()){
                    int indx = rand.nextInt(collectorAtlasSkin.fireColors.size());
                    Color fireColor = collectorAtlasSkin.fireColors.get(indx);
                    fireColor.a = 0.f;
                    Reflection.setFieldValue("color", o, fireColor);
                }
            }
        }
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = CollectorChar.class, method = "update", requiredModId = ModManager.Downfall.modId)
        public static class FireRenderPatches{
            private static boolean renderingPlayer = false;

            public static void Prefix(CollectorChar __instance){
                if(Objects.equals(__instance, AbstractDungeon.player)){
                    renderingPlayer = true;
                }
            }

            public static void Postfix(CollectorChar __instance){
                if(Objects.equals(__instance, AbstractDungeon.player)){
                    renderingPlayer = false;
                }
            }
        }

        @SpirePatch2(clz = GlowyFireEyesEffect.class, method = SpirePatch.CONSTRUCTOR, requiredModId = ModManager.Downfall.modId)
        public static class GFImageReplacer{
            public static void Postfix(GlowyFireEyesEffect __instance){
                patchFire(__instance);
            }
        }

        @SpirePatch2(clz = StaffFireEffect.class, method = SpirePatch.CONSTRUCTOR, requiredModId = ModManager.Downfall.modId)
        public static class STImageReplacer{
            public static void Postfix(StaffFireEffect __instance){
                patchFire(__instance);
            }
        }
    }
}
