package skindex.unlockmethods;

import skindex.itemtypes.AbstractOwnableItem;

public class FreeUnlockMethod extends AbstractUnlockMethod {
    public static String methodId = "FREE";

    public FreeUnlockMethod() {
        super(methodId, "");
    }

    @Override
    public boolean hasUnlocked(AbstractOwnableItem ownableItem) {
        return true;
    }
}
