package gymmanagement;

public enum ClassNames {
    CARDIO,
    SPINNING,
    PILATES;

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
     * @param s
     * @return
     */
    public static String removeUTF8BOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
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
