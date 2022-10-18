package gymmanagement;

/**
 * enum class for the different constructor and helper methods
 * used as a data field for FitnessClass
 */
public enum Instructor{
    JENNIFER,
    DENISE,
    KIM,
    DAVIS,
    EMMA;

    /**
     * returns the instructor constant from the given instructor string
     * @param instructorName - parameter to be matched with the enum class constants
     * @return a instructor instance that matches the given parameter instructorname
     */
    public static Instructor returnInstructor(String instructorName){
        String instructorNameNormalized = instructorName.toLowerCase();
        return switch (instructorNameNormalized) {
            case ("jennifer") -> Instructor.JENNIFER;
            case ("denise") -> Instructor.DENISE;
            case ("kim") -> Instructor.KIM;
            case ("davis") -> Instructor.DAVIS;
            case ("emma") -> Instructor.EMMA;
            default -> null;
        };
    }
}
