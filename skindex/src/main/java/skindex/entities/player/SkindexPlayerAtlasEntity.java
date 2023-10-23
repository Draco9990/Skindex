package skindex.entities.player;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;

public interface SkindexPlayerAtlasEntity extends SkindexPlayerEntity {
    void loadAnimation(String atlasUrl, String skeletonUrl, String resourceDirectory, float scale);

    TextureAtlas getAtlas();
    Skeleton getSkeleton();
    AnimationState getState();
    AnimationStateData getStateData();

    float getScale();
}
