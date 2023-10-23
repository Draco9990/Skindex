package skindex.unlockmethods;

public class NonUnlockableUnlockMethod extends UnlockMethod{
    /** Variables */
    public static String methodId = "NON_UNLOCKABLE";

    /** Constructors */
    public NonUnlockableUnlockMethod() {
        super(methodId, "This item is not unlockable.");
    }
}
