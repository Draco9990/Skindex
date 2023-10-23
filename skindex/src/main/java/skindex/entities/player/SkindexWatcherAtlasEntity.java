package skindex.entities.player;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;

public interface SkindexWatcherAtlasEntity extends SkindexPlayerAtlasEntity {
    void setEyeBone(Bone eyeBone);

    void setEyeAtlas(TextureAtlas textureAtlas);
    TextureAtlas getEyeAtlas();

    void setEyeSkeleton(Skeleton eyeSkeleton);

    void setEyeStateData(AnimationStateData eyeStateData);

    void setEyeState(AnimationState eyeState);
}
