package skindex.modcompat.downfall.skins.player.hexaghost;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dLib.modcompat.ModManager;
import dLib.util.Reflection;
import dLib.util.TextureManager;
import skindex.SkindexGame;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.modcompat.downfall.entities.player.SkindexHexaghostAtlasEntity;
import skindex.modcompat.downfall.skins.player.DownfallSkinWrapper;
import skindex.skins.player.PlayerAtlasSkin;
import skindex.skins.player.AbstractPlayerSkin;
import theHexaghost.TheHexaghost;
import theHexaghost.vfx.MyBody;

public class HexaghostAtlasSkin extends PlayerAtlasSkin {
    /** Variables */
    public Texture plasma1;
    public Texture plasma2;
    public Texture plasma3;
    public Texture shadow;

    public boolean disablePlasma;

    public Float standardRotationSpeed = 20.f;
    public Float fastRotationSpeed = 100.f;

    public boolean isOriginal = false;

    /** Constructors */
    public HexaghostAtlasSkin(HexaghostAtlasSkinData skinData) {
        super(skinData);

        plasma1 = loadImageIfExists(skinData.plasma1Url);
        plasma2 = loadImageIfExists(skinData.plasma2Url);
        plasma3 = loadImageIfExists(skinData.plasma3Url);
        shadow = loadImageIfExists(skinData.shadowUrl);

        disablePlasma = skinData.disablePlasma;

        defaultAnimName = "idle2";

        standardRotationSpeed = skinData.standardRotationSpeed;
        fastRotationSpeed = skinData.fastRotationSpeed;
    }

    public HexaghostAtlasSkin(DownfallSkinWrapper.DownfallSkinWrapperData skinData){
        super(skinData);

        plasma1 = TextureManager.getTexture("images/monsters/theBottom/boss/ghost/plasma1.png");
        plasma2 = TextureManager.getTexture("images/monsters/theBottom/boss/ghost/plasma2.png");
        plasma3 = TextureManager.getTexture("images/monsters/theBottom/boss/ghost/plasma3.png");
        shadow = TextureManager.getTexture("images/monsters/theBottom/boss/ghost/shadow.png");
    }

    /** Load */
    @Override
    public boolean loadOnEntity(SkindexPlayerEntity entity) {
        if(!super.loadOnEntity(entity)) return false;
        if(!(entity instanceof SkindexHexaghostAtlasEntity)) return false;

        MyBody hexaBody = ((SkindexHexaghostAtlasEntity) entity).getMyBody();
        if(plasma1 != null) Reflection.setFieldValue("plasma1", hexaBody, plasma1);
        if(plasma2 != null) Reflection.setFieldValue("plasma2", hexaBody, plasma2);
        if(plasma3 != null) Reflection.setFieldValue("plasma3", hexaBody, plasma3);
        if(shadow != null) Reflection.setFieldValue("shadow", hexaBody, shadow);

        Patches.FieldPatches.scale.set(hexaBody, scale);

        return true;
    }
    @Override
    public boolean loadOnPlayer() {
        if(!super.loadOnPlayer()) return false;

        MyBody hexaBody = ((TheHexaghost) AbstractDungeon.player).myBody;
        if(plasma1 != null) Reflection.setFieldValue("plasma1", hexaBody, plasma1);
        if(plasma2 != null) Reflection.setFieldValue("plasma2", hexaBody, plasma2);
        if(plasma3 != null) Reflection.setFieldValue("plasma3", hexaBody, plasma3);
        if(shadow != null) Reflection.setFieldValue("shadow", hexaBody, shadow);

        Patches.FieldPatches.scale.set(hexaBody, scale);

        return true;
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = TheHexaghost.class, method = "preBattlePrep", requiredModId = ModManager.Downfall.modId, optional = true)
        public static class InCombatRotationSpeedPatch{
            public static void Postfix(){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin instanceof HexaghostAtlasSkin && AbstractDungeon.player instanceof TheHexaghost){
                    ((TheHexaghost) AbstractDungeon.player).myBody.targetRotationSpeed = ((HexaghostAtlasSkin) currentSkin).fastRotationSpeed;
                }
            }
        }

        @SpirePatch2(clz = TheHexaghost.class, method = "onVictory", requiredModId = ModManager.Downfall.modId, optional = true)
        public static class OutOfCombatRotationSpeedPatch{
            public static void Postfix(){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin instanceof HexaghostAtlasSkin && AbstractDungeon.player instanceof TheHexaghost){
                    ((TheHexaghost) AbstractDungeon.player).myBody.targetRotationSpeed = ((HexaghostAtlasSkin) currentSkin).standardRotationSpeed;
                }
            }
        }

        /** Field Patches */
        @SpirePatch(clz = MyBody.class, method = "<class>", requiredModId = ModManager.Downfall.modId, optional = true)
        public static class FieldPatches {
            public static SpireField<Float> scale = new SpireField<>(() -> 1.f);
        }

        @SpirePatch2(clz = MyBody.class, method = "render", requiredModId = ModManager.Downfall.modId, optional = true)
        public static class MyBodyRenderPatch{
            public static SpireReturn Prefix(MyBody __instance, SpriteBatch sb){
                Float scale = FieldPatches.scale.get(__instance);
                if(scale != null){
                    AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin instanceof HexaghostAtlasSkin && AbstractDungeon.player instanceof TheHexaghost){
                        if(__instance.equals(((TheHexaghost) AbstractDungeon.player).myBody)){
                            if(((HexaghostAtlasSkin) currentSkin).disablePlasma){
                                return SpireReturn.Return();
                            }
                        }
                    }

                    float BODY_OFFSET_Y = Reflection.getFieldValue("BODY_OFFSET_Y", __instance);

                    float plasma1Angle = Reflection.getFieldValue("plasma1Angle", __instance);
                    float plasma2Angle = Reflection.getFieldValue("plasma2Angle", __instance);
                    float plasma3Angle = Reflection.getFieldValue("plasma3Angle", __instance);

                    Texture plasma1 = Reflection.getFieldValue("plasma1", __instance);
                    Texture plasma2 = Reflection.getFieldValue("plasma2", __instance);
                    Texture plasma3 = Reflection.getFieldValue("plasma3", __instance);
                    Texture shadow = Reflection.getFieldValue("shadow", __instance);

                    BobEffect effect = Reflection.getFieldValue("effect", __instance);

                    sb.setColor(AbstractDungeon.player.tint.color);
                    sb.draw(plasma3, __instance.XOffset + AbstractDungeon.player.drawX - 270.0F + AbstractDungeon.player.animX - 12.0F * Settings.scale * scale, __instance.YOffset + AbstractDungeon.player.drawY + AbstractDungeon.player.animY + effect.y * 2.0F - 256.0F + BODY_OFFSET_Y * scale, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 0.95F * scale, Settings.scale * 0.95F * scale, plasma3Angle, 0, 0, 512, 512, false, false);
                    sb.draw(plasma2, __instance.XOffset + AbstractDungeon.player.drawX - 270.0F + AbstractDungeon.player.animX - 6.0F * Settings.scale * scale, __instance.YOffset + AbstractDungeon.player.drawY + AbstractDungeon.player.animY + effect.y - 256.0F + BODY_OFFSET_Y * scale, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * scale, Settings.scale * scale, plasma2Angle, 0, 0, 512, 512, false, false);
                    sb.draw(plasma1, __instance.XOffset + AbstractDungeon.player.drawX - 270.0F + AbstractDungeon.player.animX, __instance.YOffset + AbstractDungeon.player.drawY + AbstractDungeon.player.animY + effect.y * 0.5F - 256.0F + BODY_OFFSET_Y * scale, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * scale, Settings.scale * scale, plasma1Angle, 0, 0, 512, 512, false, false);
                    sb.draw(shadow, __instance.XOffset + AbstractDungeon.player.drawX - 270.0F + AbstractDungeon.player.animX - 12.0F * Settings.scale * scale, __instance.YOffset + AbstractDungeon.player.drawY + AbstractDungeon.player.animY + effect.y / 4.0F - 15.0F * Settings.scale * scale - 256.0F + BODY_OFFSET_Y * scale, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * scale, Settings.scale * scale, 0.0F, 0, 0, 512, 512, false, false);

                    return SpireReturn.Return();
                }

                return SpireReturn.Continue();
            }
        }
    }
}
