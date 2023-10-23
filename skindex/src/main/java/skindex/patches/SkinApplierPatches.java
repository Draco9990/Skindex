package skindex.patches;

import basemod.interfaces.StartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import skindex.SkindexGame;
import skindex.skins.player.PlayerSkin;

public class SkinApplierPatches implements StartGameSubscriber {
    @Override
    public void receiveStartGame() {
        PlayerSkin queuedSkin = SkindexGame.getQueuedPlayerSkin();
        if(queuedSkin != null){
            queuedSkin.loadOnPlayer();
            SkindexGame.clearQueuedPlayerSkin();
        }
    }
}
