package skindex.registering;

import skindex.bundles.Bundle;
import skindex.itemtypes.AbstractCustomizableItem;
import skindex.unlockmethods.UnlockMethod;

import java.util.ArrayList;
import java.util.List;

public interface ISkindexItemRegistrant extends ISkindexSubscriber {
    default List<UnlockMethod> getUnlockMethodsToRegister(){
        return new ArrayList<>();
    }

    default List<Bundle> getBundlesToRegister(){
        return new ArrayList<>();
    }

    default List<AbstractCustomizableItem> getItemsToRegister(){
        return new ArrayList<>();
    }
}
