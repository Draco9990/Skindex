package skindex.registering;

import skindex.unlockmethods.UnlockMethod;

import java.util.List;

public interface SkindexUnlockMethodRegistrant extends ISkindexSubscriber{
    List<UnlockMethod> getUnlockMethodsToRegister();
}
