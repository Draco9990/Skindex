package skindex.skins.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.CardTrailEffect;
import skindex.SkindexGame;
import skindex.itemtypes.OwnableItem;
import skindex.registering.SkindexRegistry;
import skindex.effects.AbstractCreatureEffect;
import skindex.entities.player.SkindexPlayerEntity;
import skindex.skins.orb.OrbSkin;
import skindex.skins.stances.StanceSkin;
import skindex.util.SkindexLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayerSkin extends OwnableItem {
    /** Variables */
    public AbstractPlayer.PlayerClass playerClass;

    protected String resourceDirectory;

    public Texture shoulderIMG;
    public Texture shoulder2IMG;
    public Texture corpseIMG;

    public Color renderColor = Color.WHITE.cpy();

    public ArrayList<String> cardSkins;
    public ArrayList<Color> cardTrailColors = new ArrayList<>();

    public HashMap<String, String> orbsSkinMap = new HashMap<>();
    public HashMap<String, StanceSkin> stanceSkinMap = new HashMap<>();

    private AbstractCreatureEffect effect = null;

    public float scale = 1;

    /** Constructors */
    public PlayerSkin(PlayerSkinData skinData){
        super(skinData);
        this.resourceDirectory = skinData.resourceDirectoryUrl;
        this.playerClass = AbstractPlayer.PlayerClass.valueOf(skinData.playerClass);

        shoulderIMG = loadImageIfExists(skinData.shoulderIMG);
        shoulder2IMG = loadImageIfExists(skinData.shoulder2IMG);
        corpseIMG = loadImageIfExists(skinData.corpseIMG);

        cardSkins = skinData.cardSkins;
        for(String cardTrailColor : skinData.cardTrailColors){
            cardTrailColors.add(Color.valueOf(cardTrailColor));
        }

        for(String orbSkin : skinData.orbSkins){
            OrbSkin skin = SkindexRegistry.getOrbSkinById(orbSkin, false);
            if(skin == null) continue;

            orbsSkinMap.put(skin.orbId, orbSkin);
        }
        for(String stanceSkin : skinData.stanceSkins){
            StanceSkin skin = SkindexRegistry.getStanceSkinById(stanceSkin, true);
            if(skin == null) continue;

            stanceSkinMap.put(skin.stanceId, skin);
        }

        scale = skinData.scale;
    }

    /** Getters and Setters */
    public PlayerSkin setRenderColor(Color c){
        renderColor = c.cpy();
        return this;
    }

    public PlayerSkin setEffect(AbstractCreatureEffect skinEffect){
        this.effect = skinEffect;
        return this;
    }
    public AbstractCreatureEffect getEffect(){
        return effect;
    }

    /** Update and Render */
    public void update(AbstractCreature updateSource){
        if(effect != null){
            effect.setTarget(updateSource);
            effect.update();
        }
    }

    /** Load methods */
    public boolean loadOnEntity(SkindexPlayerEntity entity){
        if(!playerClass.equals(entity.getPlayerClass())){
            SkindexLogger.log("Tried to load skin: " + getId() + " for class " + playerClass + "on a " + entity);
            return false;
        }

        entity.setPlayerSkin(this);
        return true;
    }
    public boolean loadOnPlayer(){
        if(!CardCrawlGame.isInARun()) return false;
        if(!playerClass.equals(AbstractDungeon.player.chosenClass)) {
            SkindexLogger.log("Tried to load skin: " + getId() + " for class " + playerClass + "on a " + AbstractDungeon.player);
            return false;
        }

        if(corpseIMG != null) AbstractDungeon.player.corpseImg = corpseIMG;
        if(shoulderIMG != null) AbstractDungeon.player.shoulderImg = shoulderIMG;
        if(shoulder2IMG != null) AbstractDungeon.player.shoulder2Img = shoulder2IMG;

        SkindexGame.setActivePlayerSkin(this);
        return true;
    }

    /** Methods */
    @Override
    public boolean unlock() {
        if(getTracker() != null){
            return getTracker().unlockSkin(this);
        }

        return false;
    }
    @Override
    public boolean hasUnlocked() {
        if(super.hasUnlocked()) return true;

        if(getTracker() != null){
            return getTracker().hasSkin(this);
        }

        return false;
    }

    public static void unlockSkin(String skinId, AbstractPlayer.PlayerClass playerClass){
        PlayerSkin skin = SkindexRegistry.getPlayerSkinByClassAndId(playerClass, skinId);
        if(skin != null){
            skin.unlock();
        }
    }

    public static void unlockSkin(PlayerSkinData skinData){
        unlockSkin(skinData.id, AbstractPlayer.PlayerClass.valueOf(skinData.playerClass));
    }

    /** Patches */
    public static class Patches{
        @SpirePatch2(clz = AbstractPlayer.class, method = "update")
        public static class UpdatePatcher{
            public static void Postfix(AbstractPlayer __instance){
                if(__instance.equals(AbstractDungeon.player)){
                    PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                    if(currentSkin != null){
                        currentSkin.update(__instance);
                    }
                }
            }
        }

        @SpirePatch2(clz = CardTrailEffect.class, method = "init")
        public static class CardTrailPatcher{
            static Random colorRand = new Random();

            public static void Postfix(CardTrailEffect __instance){
                PlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
                if(currentSkin != null){
                    ArrayList<Color> cardTrailColors = currentSkin.cardTrailColors;
                    if(cardTrailColors.size() == 1){
                        dLib.util.Reflection.setFieldValue("color", __instance, cardTrailColors.get(0));
                    }
                    else if(cardTrailColors.size() > 1){
                        dLib.util.Reflection.setFieldValue("color", __instance, cardTrailColors.get(colorRand.nextInt(cardTrailColors.size())));
                    }
                }
            }
        }
    }

    /** System Methods */

    @Override
    public Integer getUUID(){
        return 10000 + new Random((id + playerClass.toString()).hashCode()).nextInt(90000);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PlayerSkin)) return false;

        PlayerSkin item = (PlayerSkin) obj;

        return item.getId().equals(id) &&
                item.playerClass.equals(playerClass);
    }
}