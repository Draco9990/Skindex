package skindex.unlockmethods;

public class AchievementUnlockMethod extends UnlockMethod {
    /** Variables */
    public static String methodId = "ACHIEVEMENT";

    /** Constructors */
    public AchievementUnlockMethod() {
        super(methodId, "This item is unlocked through an achievement.");
    }
}
