package gymmanagement;


/**
 * enum class for the different exercise classes, contains helper method to set the value, and return a
 * specific constant
 */
public enum ClassNames {
    CARDIO,
    SPINNING,
    PILATES;

    /**
     * returns the class Name constant for the given string parameter
     * @param ClassName the string we want the ClassName constant for
     * @return the class name which corresponds to the given String, returns null if no match
     */
    public static ClassNames returnClassName(String ClassName){
        String ClassNameUpper = ClassName.toUpperCase();
        ClassNameUpper = removeUTF8BOM(ClassNameUpper);
        //System.out.println(ClassNameUpper);
        //System.out.println(ClassNameUpper.equals("\uFEFFPILATES"));
        return switch (ClassNameUpper) {
            case "CARDIO" -> ClassNames.CARDIO;
            case "SPINNING" -> ClassNames.SPINNING;
            case "PILATES" -> ClassNames.PILATES;
            //case "\uFEFFPILATES" -> ClassNames.PILATES;
            default -> null;
        };
    }

    /**
     * sometimes when reading from a txt file, at the beginning of the file, there is this weird thing,
     * this method just checks if it is there and gets rid of it if it is there
     * @param fitnessClass - the string we want to remove the value from
     * @return the string without the weird value
     */
    public static String removeUTF8BOM(String fitnessClass) {
        if (fitnessClass.startsWith("\uFEFF")) {
            fitnessClass = fitnessClass.substring(1);
        }
        return fitnessClass;
    }

    /**
     * this method returns the className in uppercase form
     * @return
     */
    public String returnCapitalized(){
        if(this.name() == null){
            return null;
        }
        String Classname = this.name().toUpperCase();
        return Classname;
    }


    /**
     * a new equalsIgnoreCase that works for ClassNames, as comparing classNames does not work
     * @param className
     * @return
     */
    public boolean equalsIgnoreCase(ClassNames className) {
        if(this.name().equalsIgnoreCase(className.name())){
            return true;
        } else {
            return false;
        }
    }
}
