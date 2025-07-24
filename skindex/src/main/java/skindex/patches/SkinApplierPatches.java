package skindex.patches;

import basemod.interfaces.StartGameSubscriber;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dLib.files.JsonDataFileManager;
import skindex.SkindexGame;
import skindex.files.SkindexUserConfig;
import skindex.skins.player.AbstractPlayerSkin;

public class SkinApplierPatches implements StartGameSubscriber {
    @Override
    public void receiveStartGame() {
        AbstractPlayerSkin queuedSkin = SkindexGame.getQueuedPlayerSkin();
        if (queuedSkin == null) {
            queuedSkin = JsonDataFileManager.load(SkindexUserConfig.class).getFavouritedSkin(AbstractDungeon.player.chosenClass);
        }

        if(queuedSkin == null) return;

        queuedSkin.loadOnPlayer();
        SkindexGame.clearQueuedPlayerSkin();
    }
}
