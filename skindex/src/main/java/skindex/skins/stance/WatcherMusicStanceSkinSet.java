package skindex.skins.stance;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.DivinityStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import dLib.effects.AuraEffect;
import dLib.util.Timer;
import skindex.registering.SkindexRegistry;
import skindex.skins.misc.particle.music.CalmNoteParticleEffect;
import skindex.skins.misc.particle.music.DivinityNoteParticleEffect;
import skindex.skins.misc.particle.music.WrathNoteParticleEffect;
import skindex.skins.stances.StanceSkin;
import skindex.skins.stances.StanceSkinData;

import java.util.Arrays;
import java.util.List;

public class WatcherMusicStanceSkinSet {
    public static void registerStanceSkins(){
        SkindexRegistry.registerItem(new CalmSkin());
        SkindexRegistry.registerItem(new WrathSkin());
        SkindexRegistry.registerItem(new DivinitySkin());
    }

    public static List<String> collectStanceSkinIds(){
        return Arrays.asList(
                CalmSkin.SkinData.ID,
                WrathSkin.SkinData.ID,
                DivinitySkin.SkinData.ID
        );
    }

    public static class CalmSkin extends StanceSkin{
        public CalmSkin() {
            super(new SkinData());

            addTimer(new Timer(0.04F) {
                @Override
                public void onTrigger() {
                    AbstractDungeon.effectsQueue.add(new CalmNoteParticleEffect(getRenderTarget()));
                }
            });

            addTimer(new Timer(0.45F, 0.55F) {
                @Override
                public void onTrigger() {
                    AbstractDungeon.effectsQueue.add(new AuraEffect(Color.valueOf("#dbfffaff"), getRenderTarget()));
                }
            });
        }

        public static class SkinData extends StanceSkinData {
            public static String ID = "WATCHER_MUSIC_CALM";
            public SkinData(){
                id = ID;
                stanceId = CalmStance.STANCE_ID;
                stanceEnterSound = "skindexResources/images/skins/player/watcher/music/calm.ogg";
            }
        }
    }

    public static class WrathSkin extends StanceSkin{
        public WrathSkin() {
            super(new SkinData());

            addTimer(new Timer(0.05F) {
                @Override
                public void onTrigger() {
                    AbstractDungeon.effectsQueue.add(new WrathNoteParticleEffect(getRenderTarget()));
                }
            });

            addTimer(new Timer(0.3F, 0.4F) {
                @Override
                public void onTrigger() {
                    AbstractDungeon.effectsQueue.add(new AuraEffect(Color.valueOf("#ffe4deff"), getRenderTarget()));
                }
            });
        }

        public static class SkinData extends StanceSkinData{
            public static String ID = "WATCHER_MUSIC_WRATH";
            public SkinData(){
                id = ID;
                stanceId = WrathStance.STANCE_ID;
                stanceEnterSound = "skindexResources/images/skins/player/watcher/music/wrath.ogg";
            }
        }
    }

    public static class DivinitySkin extends StanceSkin{
        public DivinitySkin() {
            super(new SkinData());

            addTimer(new Timer(0.05F) {
                @Override
                public void onTrigger() {
                    AbstractDungeon.effectsQueue.add(new DivinityNoteParticleEffect(getRenderTarget()));
                }
            });

            addTimer(new Timer(0.3F, 0.4F) {
                @Override
                public void onTrigger() {
                    AbstractDungeon.effectsQueue.add(new AuraEffect(Color.valueOf("#ffe0f8"), getRenderTarget()));
                }
            });
        }

        public static class SkinData extends StanceSkinData{
            public static String ID = "WATCHER_MUSIC_DIVINITY";
            public SkinData(){
                id = ID;
                stanceId = DivinityStance.STANCE_ID;
                stanceEnterSound = "skindexResources/images/skins/player/watcher/music/divinity.ogg";
            }
        }
    }
}
