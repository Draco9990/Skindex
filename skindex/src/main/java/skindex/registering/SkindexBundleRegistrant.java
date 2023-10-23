package skindex.registering;

import skindex.bundles.Bundle;
import skindex.trackers.SkindexTracker;

import java.util.ArrayList;
import java.util.List;

public interface SkindexBundleRegistrant extends ISkindexSubscriber{
    List<Bundle> getBundlesToRegister();
}
