package skindex.registering;

import skindex.trackers.SkindexTracker;

import java.util.ArrayList;
import java.util.List;

public interface SkindexTrackerRegistrant extends ISkindexSubscriber{
    List<SkindexTracker> getTrackersToRegister(int saveSlot);
}
