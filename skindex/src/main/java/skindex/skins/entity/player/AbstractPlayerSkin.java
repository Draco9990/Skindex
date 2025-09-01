package skindex.skins.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.CardTrailEffect;
import dLib.files.JsonDataFileManager;
import skindex.SkindexGame;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.itemtypes.AbstractOwnableItem;
import skindex.registering.SkindexRegistry;
import skindex.effects.AbstractCreatureEffect;
import skindex.skins.orb.OrbSkin;
import skindex.skins.stances.StanceSkin;
import skindex.trackers.SkindexUnlockTracker;
import skindex.util.SkindexLogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class AbstractPlayerSkin extends AbstractOwnableItem {
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

    public boolean isDefault = false;

    /** Constructors */
    public AbstractPlayerSkin(PlayerSkinData skinData){
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

        isDefault = skinData.isDefault;
    }

    @Override
    protected Class<? extends AbstractCustomizableItem> getItemType() {
        return AbstractPlayerSkin.class;
    }

    /** Getters and Setters */
    public AbstractPlayerSkin setRenderColor(Color c){
        renderColor = c.cpy();
        return this;
    }

    public AbstractPlayerSkin setEffect(AbstractCreatureEffect skinEffect){
        this.effect = skinEffect;
        return this;
    }
    public AbstractCreatureEffect getEffect(){
        return effect;
    }

    /** Update and Render */
    public void update(AbstractPlayer updateSource){
        if(effect != null){
            effect.setTarget(updateSource);
            effect.update();
        }
    }

    public final boolean loadOnPlayer(){
        return loadOnPlayer(AbstractDungeon.player);
    }
    public boolean loadOnPlayer(AbstractPlayer player){
        if(!CardCrawlGame.isInARun()) {
            return false;
        }
        if(!playerClass.equals(player.chosenClass)) {
            SkindexLogger.log("Tried to load skin: " + getId() + " for class " + playerClass + "on a " + player);
            return false;
        }

        SkindexGame.setActivePlayerSkinRaw(player, this);

        if(corpseIMG != null) player.corpseImg = corpseIMG;
        if(shoulderIMG != null) player.shoulderImg = shoulderIMG;
        if(shoulder2IMG != null) player.shoulder2Img = shoulder2IMG;
        return true;
    }

    /** Methods */
    @Override
    public boolean unlock() {
        SkindexUnlockTracker tracker = JsonDataFileManager.load(SkindexUnlockTracker.class);
        return tracker.unlockPlayerSkin(this);
    }

    @Override
    public boolean hasUnlocked() {
        SkindexUnlockTracker tracker = JsonDataFileManager.load(SkindexUnlockTracker.class);
        return tracker.hasPlayerSkin(this);
    }

    public static void unlockSkin(String skinId, AbstractPlayer.PlayerClass playerClass){
        AbstractPlayerSkin skin = SkindexRegistry.getPlayerSkinByClassAndId(playerClass, skinId);
        if(skin != null){
            skin.unlock();
        }
    }

    public static void unlockSkin(PlayerSkinData skinData){
        unlockSkin(skinData.id, AbstractPlayer.PlayerClass.valueOf(skinData.playerClass));
    }

    public static class Patches{
        @SpirePatch2(clz = AbstractPlayer.class, method = "update")
        public static class UpdatePatcher{
            public static void Postfix(AbstractPlayer __instance){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin(__instance);
                if(currentSkin != null){
                    currentSkin.update(__instance);
                }
            }
        }

        @SpirePatch2(clz = CardTrailEffect.class, method = "init")
        public static class CardTrailPatcher{
            static Random colorRand = new Random();

            public static void Postfix(CardTrailEffect __instance){
                AbstractPlayerSkin currentSkin = SkindexGame.getActivePlayerSkin();
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
        if(!(obj instanceof AbstractPlayerSkin)) return false;

        AbstractPlayerSkin item = (AbstractPlayerSkin) obj;

        return item.getId().equals(id) &&
                item.playerClass.equals(playerClass);
    }

    public static class PlayerSkinData extends OwnableItemData implements Serializable {
        static final long serialVersionUID = 1L;

        /** Resources */
        @SerializedName("playerClass")
        public String playerClass = AbstractPlayer.PlayerClass.IRONCLAD.name();

        @SerializedName("resourceDirectoryUrl")
        public String resourceDirectoryUrl;
        @SerializedName("scale")
        public Float scale = 1.f;

        /** Shoulder & Death */
        @SerializedName("shoulderImg")
        public String shoulderIMG;
        @SerializedName("shoulder2Img")
        public String shoulder2IMG;
        @SerializedName("corpseIms")
        public String corpseIMG;

        /** Card stuff */
        @SerializedName("cardSkins")
        public ArrayList<String> cardSkins = new ArrayList<>();
        @SerializedName("cardTrailColors")
        public ArrayList<String> cardTrailColors = new ArrayList<>();

        @SerializedName("orbSkins")
        public ArrayList<String> orbSkins = new ArrayList<>();
        @SerializedName("stanceSkins")
        public ArrayList<String> stanceSkins = new ArrayList<>();

        @SerializedName("isDefault")
        public boolean isDefault = false;
    }
}