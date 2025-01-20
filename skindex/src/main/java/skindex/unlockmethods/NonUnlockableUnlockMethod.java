package skindex.unlockmethods;

public class NonUnlockableUnlockMethod extends AbstractUnlockMethod {
    public static String methodId = "NON_UNLOCKABLE";

    public NonUnlockableUnlockMethod() {
        super(methodId, "This item is not unlockable.");
    }
}
