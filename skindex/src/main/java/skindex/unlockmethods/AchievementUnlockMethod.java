package skindex.unlockmethods;

public class AchievementUnlockMethod extends AbstractUnlockMethod {
    public static String methodId = "ACHIEVEMENT";

    public AchievementUnlockMethod() {
        super(methodId, "This item is unlocked through an achievement.");
    }
}
