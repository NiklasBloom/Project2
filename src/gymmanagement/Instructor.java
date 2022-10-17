package gymmanagement;

public enum Instructor{
    JENNIFER,
    DENISE,
    KIM,
    DAVIS,
    EMMA;

    /**
     * returns the instructor constant from the given instructor string
     * @param instructorName
     * @return
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
