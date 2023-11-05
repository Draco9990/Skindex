package skindex.callbacks;

import skindex.registering.ISkindexSubscriber;

public interface SkindexPostRegistryFinishCallback extends ISkindexSubscriber {
    void onRegistryFinish();
}
