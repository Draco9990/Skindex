package skindex.skins.stances;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.*;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import dLib.util.Reflection;
import dLib.util.StaticResourceLoader;
import dLib.util.Timer;
import skindex.SkindexGame;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.skins.misc.particle.ParticleData;
import skindex.skins.player.AbstractPlayerSkin;

import java.util.ArrayList;
import java.util.Map;

public class StanceSkin extends AbstractCustomizableItem {
    //region Variables
    public String stanceId;

    public String stanceEnterSound;

    private ArrayList<Timer> effectTimers = new ArrayList<>();
    //endregion Variables

    //region Constructors
    public StanceSkin(StanceSkinData itemData){
        super(itemData);

        this.stanceId = itemData.stanceId;

        this.stanceEnterSound = itemData.stanceEnterSound;

        for(Map.Entry<float[], ParticleData> particleData : itemData.particleData.entrySet()){

        }
    }

    @Override
    protected Class<? extends AbstractCustomizableItem> getItemType() {
        return StanceSkin.class;
    }

    //endregion Constructors

    //region Class Methods

    public AbstractCreature getRenderTarget(){
        return SkindexGame.getStanceOverrideRenderCreature() == null ? AbstractDungeon.player : SkindexGame.getStanceOverrideRenderCreature();
    }

    //region Update & Render
    public void update(){
        for(Timer t : effectTimers){
            t.update();
        }
    }
    //endregion

    //region Timers
    public void addTimer(Timer timer){
        effectTimers.add(timer);
    }
    //endregion

    //region Audio

    public boolean hasCustomEnterSound(){
        return stanceEnterSound != null;
    }

    public long playEnterSound(){
        if (!CardCrawlGame.MUTE_IF_BG && !Settings.isBackgrounded) {
            return StaticResourceLoader.getSfx(stanceEnterSound).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        }

        return 0L;
    }

    //endregion

    //endregion Class Methods

    //region Patches
    public static class Patches{
        public static class InitialEffectPatches{
            @SpirePatch(clz = StanceAuraEffect.class, method = SpirePatch.CONSTRUCTOR)
            public static class StanceAuraPatcher{
                public static void Postfix(StanceAuraEffect __instance){
                    AbstractCreature renderOverride = SkindexGame.getStanceOverrideRenderCreature();
                    if(renderOverride != null){
                        Reflection.setFieldValue("x", __instance, renderOverride.hb.cX + MathUtils.random(-renderOverride.hb.width / 16.0F, renderOverride.hb.width / 16.0F) - ((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedWidth/2.0F);
                        Reflection.setFieldValue("y", __instance, renderOverride.hb.cY + MathUtils.random(-renderOverride.hb.height / 16.0F, renderOverride.hb.height / 12.0F) - ((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedHeight/2.0F);
                    }
                }
            }

            @SpirePatch(clz = DivinityParticleEffect.class, method = SpirePatch.CONSTRUCTOR)
            public static class DivinityEffectsPatcher{
                public static void Postfix(DivinityParticleEffect __instance){
                    AbstractCreature renderOverride = SkindexGame.getStanceOverrideRenderCreature();
                    if(renderOverride != null){
                        Reflection.setFieldValue("x", __instance, renderOverride.hb.cX + MathUtils.random(-renderOverride.hb.width / 2.0F - 50.0F * Settings.xScale, renderOverride.hb.width / 2.0F + 50.0F * Settings.xScale) - ((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedWidth/2.0F);
                        Reflection.setFieldValue("y", __instance, renderOverride.hb.cY + MathUtils.random(-renderOverride.hb.height / 2.0F + 10.0F * Settings.yScale, renderOverride.hb.height / 2.0F - 20.0F * Settings.yScale) - ((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedHeight/2.0F);
                    }
                }
            }

            @SpirePatch(clz = WrathParticleEffect.class, method = SpirePatch.CONSTRUCTOR)
            public static class WrathEffectsPatcher{
                public static void Postfix(WrathParticleEffect __instance){
                    AbstractCreature renderOverride = SkindexGame.getStanceOverrideRenderCreature();
                    if(renderOverride != null){
                        Reflection.setFieldValue("x", __instance, renderOverride.hb.cX + MathUtils.random(-renderOverride.hb.width / 2.0F - 30.0F * Settings.xScale, renderOverride.hb.width / 2.0F + 30.0F * Settings.xScale) - ((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedWidth/2.0F);
                        Reflection.setFieldValue("y", __instance, renderOverride.hb.cY + MathUtils.random(-renderOverride.hb.height / 2.0F - -10.0F * Settings.yScale, renderOverride.hb.height / 2.0F - 10.0F * Settings.yScale) - ((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedHeight/2.0F);
                    }
                }
            }

            @SpirePatch(clz = CalmParticleEffect.class, method = SpirePatch.CONSTRUCTOR)
            public static class CalmEffectsPatcher{
                public static void Postfix(CalmParticleEffect __instance){
                    AbstractCreature renderOverride = SkindexGame.getStanceOverrideRenderCreature();
                    if(renderOverride != null){
                        Reflection.setFieldValue("x", __instance, renderOverride.hb.cX + MathUtils.random(100.0F, 160.0F) * Settings.xScale - 32.0F);
                        Reflection.setFieldValue("y", __instance, renderOverride.hb.cY + MathUtils.random(-50.0F, 220.0F) * Settings.yScale - 32.0F);
                    }
                }
            }
        }
        
        public static class RenderPatches{
            public static StanceSkin getStanceSkin(String stanceId){
                AbstractCreature renderOverride = SkindexGame.getStanceOverrideRenderCreature();

                AbstractPlayerSkin skinToRender = null;
                if(renderOverride instanceof SkindexPlayerEntity){
                    skinToRender = ((SkindexPlayerEntity) renderOverride).getPlayerSkin();
                }
                else if (AbstractDungeon.player != null){
                    skinToRender = SkindexGame.getActivePlayerSkin();
                }
                if(skinToRender == null) return null;

                StanceSkin currentSkin = skinToRender.stanceSkinMap.get(stanceId);
                return currentSkin;
            }

            public static boolean updateCustomStance(String stanceId){
                StanceSkin currentSkin = getStanceSkin(stanceId);
                if(currentSkin == null) return false;

                currentSkin.update();
                return true;
            }

            @SpirePatch2(clz = WrathStance.class, method = "updateAnimation")
            public static class WrathReplacer{
                public static SpireReturn Prefix(WrathStance __instance){
                    if(updateCustomStance(__instance.ID)){
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = CalmStance.class, method = "updateAnimation")
            public static class CalmReplacer{
                public static SpireReturn Prefix(CalmStance __instance){
                    if(updateCustomStance(__instance.ID)){
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = DivinityStance.class, method = "updateAnimation")
            public static class DivinityReplacer{
                public static SpireReturn Prefix(DivinityStance __instance){
                    if(updateCustomStance(__instance.ID)){
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }

            @SpirePatch2(clz = AbstractStance.class, method = "updateAnimation")
            public static class GenericReplacer{
                public static SpireReturn Prefix(AbstractStance __instance){
                    if(updateCustomStance(__instance.ID)){
                        return SpireReturn.Return();
                    }

                    return SpireReturn.Continue();
                }
            }
        }

        public static class AudioPatches{
            @SpirePatch2(clz = SoundMaster.class, method = "play", paramtypez = {String.class, boolean.class})
            public static class SoundPatcher{
                public static SpireReturn<Long> Prefix(String key){
                    if(!key.startsWith("STANCE_ENTER_"))
                        return SpireReturn.Continue();

                    String stance = key.substring("STANCE_ENTER_".length());
                    stance = stance.substring(0, 1).toUpperCase() + stance.substring(1).toLowerCase();
                    StanceSkin currentSkin = RenderPatches.getStanceSkin(stance);
                    if(currentSkin != null && currentSkin.hasCustomEnterSound()){
                        return SpireReturn.Return(currentSkin.playEnterSound());
                    }

                    return SpireReturn.Continue();
                }
            }
        }
    }
    //endregion
}
