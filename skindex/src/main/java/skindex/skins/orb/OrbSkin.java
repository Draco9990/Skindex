package skindex.skins.orb;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dLib.util.Help;
import dLib.util.Reflection;
import skindex.SkindexGame;
import skindex.itemtypes.CustomizableItem;
import skindex.skins.player.PlayerSkin;

public class OrbSkin extends CustomizableItem {
    //region Variables
    public String orbId;

    public Texture orbImage;
    public boolean overlay;
    //endregion Variables

    //region Constructors
    public OrbSkin(OrbSkinData orbSkinData){
        super(orbSkinData);

        orbId = orbSkinData.orbId;

        orbImage = loadImageIfExists(orbSkinData.orbImageUrl);
        overlay = orbSkinData.overlay;
    }
    //endregion Constructors

    //region Class Methods
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
    public Texture getTexture(){
        return orbImage;
    }

    public Sfx getChannelSound(){ return null; }
    public Sfx getEvokeSound(){ return null; }

    @Override
    public void dispose() {
        super.dispose();

        orbImage.dispose();
    }

    //endregion Class Methods

    //region Patches
    public static class Patches{
        public static class RegularPatches{
            @SpirePatch2(clz = Lightning.class, method = "render")
            public static class LightningOrbPatch{
                public static SpireReturn Prefix(Lightning __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin orbSkin = SkindexGame.getActiveOrbSkin(__instance.ID);
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
                    OrbSkin orbSkin = SkindexGame.getActiveOrbSkin(__instance.ID);
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
                    OrbSkin orbSkin = SkindexGame.getActiveOrbSkin(__instance.ID);
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
                    OrbSkin orbSkin = SkindexGame.getActiveOrbSkin(__instance.ID);
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
                    OrbSkin skin = SkindexGame.getActiveOrbSkin(__instance.ID);
                    if(skin != null && skin.overlay){
                        skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                    }
                }
            }

            @SpirePatch2(clz = Dark.class, method = "renderText")
            public static class DarkOrbPatch{
                public static void Prefix(Dark __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin skin = SkindexGame.getActiveOrbSkin(__instance.ID);
                    if(skin != null && skin.overlay){
                        skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                    }
                }
            }

            @SpirePatch2(clz = Plasma.class, method = "renderText")
            public static class PlasmaOrbPatch{
                public static void Prefix(Plasma __instance, SpriteBatch sb, BobEffect ___bobEffect, float ___angle, float ___scale){
                    OrbSkin skin = SkindexGame.getActiveOrbSkin(__instance.ID);
                    if(skin != null && skin.overlay){
                        skin.render(__instance, sb, ___bobEffect, 0, ___scale);
                    }
                }
            }
        }

        public static class AudioPatches{
            @SpirePatch2(clz = SoundMaster.class, method = "play", paramtypez = {String.class, float.class})
            public static class OrbAudioReplacer{
                public static SpireReturn<Long> Prefix(String key, float pitchVariation){
                    PlayerSkin activeSkin = SkindexGame.getActivePlayerSkin();
                    if(activeSkin == null || activeSkin.orbsSkinMap.isEmpty()) return SpireReturn.Continue();

                    if(key.contains("ORB_")){
                        String orbKey = key.replace("ORB_", "");

                        if(orbKey.contains("_CHANNEL")){
                            String orbId = key.replace("_CHANNEL", "");
                            orbId = Help.StringOps.capitalize(orbId);
                            OrbSkin orbSkin = activeSkin.orbsSkinMap.get(orbId);
                            if(orbSkin == null) return SpireReturn.Continue();

                            Sfx evokeSound = orbSkin.getChannelSound();
                            if(evokeSound == null) return SpireReturn.Continue();

                            return SpireReturn.Return(evokeSound.play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + MathUtils.random(-pitchVariation, pitchVariation), 0.0F));
                        }
                        if(orbKey.contains("_EVOKE")){
                            String orbId = key.replace("_EVOKE", "");
                            orbId = Help.StringOps.capitalize(orbId);
                            OrbSkin orbSkin = activeSkin.orbsSkinMap.get(orbId);
                            if(orbSkin == null) return SpireReturn.Continue();

                            Sfx evokeSound = orbSkin.getEvokeSound();
                            if(evokeSound == null) return SpireReturn.Continue();

                            return SpireReturn.Return(evokeSound.play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + MathUtils.random(-pitchVariation, pitchVariation), 0.0F));
                        }
                    }

                    return SpireReturn.Continue();
                }
            }
        }
    }
    //endregion patches
}
