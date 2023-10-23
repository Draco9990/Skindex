package skindex.registering;

import skindex.trackers.SkindexTracker;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.List;

public interface SkindexUnlockMethodRegistrant extends ISkindexSubscriber{
    List<UnlockMethod> getUnlockMethodsToRegister();
}
