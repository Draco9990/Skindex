package skindex.registering;

import skindex.bundles.Bundle;

import java.util.List;

public interface SkindexBundleRegistrant extends ISkindexSubscriber{
    List<Bundle> getBundlesToRegister();
}
