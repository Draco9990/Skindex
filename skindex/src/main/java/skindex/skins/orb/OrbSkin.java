package skindex.skins.orb;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dLib.util.Reflection;
import skindex.SkindexGame;
import skindex.itemtypes.AbstractCustomizableItem;

public class OrbSkin extends AbstractCustomizableItem {
    //region Variables
    public String orbId;

    public boolean overlay;
    //endregion Variables

    //region Constructors
    public OrbSkin(OrbSkinData orbSkinData){
        super(orbSkinData);

        orbId = orbSkinData.orbId;

        overlay = orbSkinData.overlay;
    }
    //endregion Constructors

    //region Class Methods

    //region Update & Render
    public void update(){
    }

    public void render(AbstractOrb instance, SpriteBatch sb, BobEffect bobEffect, float angle, float scale){
    }

    private void renderOrbFull(AbstractOrb instance, SpriteBatch sb, BobEffect bobEffect, float angle, float scale){
        render(instance, sb, bobEffect, angle, scale);
        Reflection.invokeMethod("renderText", instance, sb);
        instance.hb.render(sb);
    }
    //endregion Update & Render

    protected void onEvoke(AbstractOrb instance){

    }

    //endregion Class Methods

    //region Patches
    public static class Patches{
        public static class ClassPatches{
            @SpirePatch2(clz = AbstractOrb.class, method = SpirePatch.CLASS)
            public static class ClassPatch{
                public static SpireField<OrbSkin> orbSkin = new SpireField<>(() -> null);
            }

            @SpirePatch2(clz = Lightning.class, method = SpirePatch.CONSTRUCTOR)
            public static class ConstructorPatchLightning{
                @SpirePostfixPatch
                public static void loadSkinPatch(AbstractOrb __instance){
                    OrbSkin orbSkin = SkindexGame.makeOrbSkinForOrb(__instance.ID);
                    if(orbSkin != null){
                        setSkin(__instance, orbSkin);
                    }
                }
            }

            @SpirePatch2(clz = Dark.class, method = SpirePatch.CONSTRUCTOR)
            public static class ConstructorPatchDark{
                @SpirePostfixPatch
                public static void loadSkinPatch(AbstractOrb __instance){
                    OrbSkin orbSkin = SkindexGame.makeOrbSkinForOrb(__instance.ID);
                    if(orbSkin != null){
                        setSkin(__instance, orbSkin);
                    }
                }
            }

            @SpirePatch2(clz = Frost.class, method = SpirePatch.CONSTRUCTOR)
            public static class ConstructorPatchFrost{
                @SpirePostfixPatch
                public static void loadSkinPatch(AbstractOrb __instance){
                    OrbSkin orbSkin = SkindexGame.makeOrbSkinForOrb(__instance.ID);
                    if(orbSkin != null){
                        setSkin(__instance, orbSkin);
                    }
                }
            }

            @SpirePatch2(clz = Plasma.class, method = SpirePatch.CONSTRUCTOR)
            public static class ConstructorPatchPlasma{
                @SpirePostfixPatch
                public static void loadSkinPatch(AbstractOrb __instance){
                    OrbSkin orbSkin = SkindexGame.makeOrbSkinForOrb(__instance.ID);
                    if(orbSkin != null){
                        setSkin(__instance, orbSkin);
                    }
                }
            }
        }

        public static class RegularPatches{
            @SpirePatch2(clz = Lightning.class, method = "render")
            public static class LightningOrbPatch{
                public static SpireReturn Prefix(Lightning __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);
                        return SpireReturn.Return();
                    }
                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = Frost.class, method = "render")
            public static class FrostOrbPatch{
                public static SpireReturn Prefix(Frost __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);
                        return SpireReturn.Return();
                    }
                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = Dark.class, method = "render")
            public static class DarkOrbPatch{
                public static SpireReturn Prefix(Dark __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);
                        return SpireReturn.Return();
                    }
                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = Plasma.class, method = "render")
            public static class PlasmaOrbPatch{
                public static SpireReturn Prefix(Plasma __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.renderOrbFull(__instance, sb, ___bobEffect, 0, ___scale);
                        return SpireReturn.Return();
                    }
                    return SpireReturn.Continue();
                }
            }
        }

        public static class OverlayPatches{
            @SpirePatch2(clz = AbstractOrb.class, method = "renderText")
            public static class LightningAndFrostOrbPatch{
                public static void Prefix(AbstractOrb __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin skin = getSkin(__instance);
                    if(skin != null && skin.overlay){
                        skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                    }
                }
            }

            @SpirePatch2(clz = Dark.class, method = "renderText")
            public static class DarkOrbPatch{
                public static void Prefix(Dark __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin skin = getSkin(__instance);
                    if(skin != null && skin.overlay){
                        skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                    }
                }
            }

            @SpirePatch2(clz = Plasma.class, method = "renderText")
            public static class PlasmaOrbPatch{
                public static void Prefix(Plasma __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin skin = getSkin(__instance);
                    if(skin != null && skin.overlay){
                        skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                    }
                }
            }
        }

        public static class EvokePatches{
            @SpirePatch2(clz = Lightning.class, method = "onEvoke")
            public static class LightningOrbPatch{
                public static void Prefix(Lightning __instance){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.onEvoke(__instance);
                    }
                }
            }

            @SpirePatch2(clz = Frost.class, method = "onEvoke")
            public static class FrostOrbPatch{
                public static void Prefix(Frost __instance){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.onEvoke(__instance);
                    }
                }
            }

            @SpirePatch2(clz = Dark.class, method = "onEvoke")
            public static class DarkOrbPatch{
                public static void Prefix(Dark __instance){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.onEvoke(__instance);
                    }
                }
            }

            @SpirePatch2(clz = Plasma.class, method = "onEvoke")
            public static class PlasmaOrbPatch{
                public static void Prefix(Plasma __instance){
                    OrbSkin orbSkin = getSkin(__instance);
                    if(orbSkin != null && !orbSkin.overlay){
                        orbSkin.onEvoke(__instance);
                    }
                }
            }
        }

        public static class UpdatePatches{
            @SpirePatch2(clz = AbstractOrb.class, method = "update")
            public static class UpdatePatch{
                @SpirePostfixPatch
                public static void update(AbstractOrb __instance){
                    OrbSkin skin = getSkin(__instance);
                    if(skin != null){
                        skin.update();
                    }
                }
            }
        }
    }
    //endregion patches

    //region Static Managers
    public static void setSkin(AbstractOrb orb, OrbSkin orbSkin){
        if(orbSkin == null){
            return;
        }

        Patches.ClassPatches.ClassPatch.orbSkin.set(orb, orbSkin);
    }

    public static OrbSkin getSkin(AbstractOrb orb){
        return Patches.ClassPatches.ClassPatch.orbSkin.get(orb);
    }
    //endregion Static Managers
}
