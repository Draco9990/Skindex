package skindex.skins.orb;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dLib.util.Reflection;
import skindex.SkindexGame;
import skindex.itemtypes.CustomizableItem;
import skindex.skins.player.PlayerSkin;

public class OrbSkin extends CustomizableItem {
    /** Variables */
    public String orbId;
    public Texture orbImage;

    public boolean overlay;

    /** Constructors */
    public OrbSkin(OrbSkinData orbSkinData){
        super(orbSkinData);

        orbId = orbSkinData.orbId;
        overlay = orbSkinData.overlay;

        orbImage = loadImageIfExists(orbSkinData.orbImageUrl);
    }

    /** Update and Render */
    public void update(){
    }

    private void renderOrbFull(AbstractOrb instance, SpriteBatch sb, BobEffect bobEffect, float angle, float scale){
        render(instance, sb, bobEffect, angle, scale);
        Reflection.invokeMethod("renderText", instance, sb);
        instance.hb.render(sb);
    }

    public void render(AbstractOrb instance, SpriteBatch sb, BobEffect bobEffect, float angle, float scale){
        sb.setColor(Color.WHITE);
        sb.draw(getTexture(), instance.cX - 48.0F, instance.cY - 48.0F + bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, scale * 1.2f, scale * 1.2f, 0, 0, 0, 96, 96, false, false);
    }

    /** Methods */
    public Texture getTexture(){
        return orbImage;
    }

    /** Patches */
    public static class Patches{
        public static class RegularPatches{
            @SpirePatch2(clz = Lightning.class, method = "render")
            public static class LightningOrbPatch{
                public static SpireReturn Prefix(Lightning __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(!skin.overlay){
                                skin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);

                                return SpireReturn.Return();
                            }
                        }
                    }
                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = Frost.class, method = "render")
            public static class FrostOrbPatch{
                public static SpireReturn Prefix(Frost __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(!skin.overlay){
                                skin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);

                                return SpireReturn.Return();
                            }
                        }
                    }
                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = Dark.class, method = "render")
            public static class DarkOrbPatch{
                public static SpireReturn Prefix(Dark __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(!skin.overlay){
                                skin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);

                                return SpireReturn.Return();
                            }
                        }
                    }
                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = Plasma.class, method = "render")
            public static class PlasmaOrbPatch{
                public static SpireReturn Prefix(Plasma __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(!skin.overlay){
                                skin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);

                                return SpireReturn.Return();
                            }
                        }
                    }
                    return SpireReturn.Continue();
                }
            }
        }

        public static class OverlayPatches{
            @SpirePatch2(clz = AbstractOrb.class, method = "renderText")
            public static class LightningAndFrostOrbPatch{
                public static void Prefix(AbstractOrb __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(skin.overlay){
                                skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                            }
                        }
                    }
                }
            }

            @SpirePatch2(clz = Dark.class, method = "renderText")
            public static class DarkOrbPatch{
                public static void Prefix(Dark __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(skin.overlay){
                                skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                            }
                        }
                    }
                }
            }

            @SpirePatch2(clz = Plasma.class, method = "renderText")
            public static class PlasmaOrbPatch{
                public static void Prefix(Plasma __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        if(currentSkin.orbsSkinMap.containsKey(__instance.ID)){
                            OrbSkin skin = currentSkin.orbsSkinMap.get(__instance.ID);
                            if(skin.overlay){
                                skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                            }
                        }
                    }
                }
            }
        }
    }
}
