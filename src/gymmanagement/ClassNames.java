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

    public static String removeUTF8BOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
    }

    public String returnCapitalized(){
        if(this.name() == null){
            return null;
        }
        String Classname = this.name().toUpperCase();
        return Classname;
    }


    public boolean equalsIgnoreCase(ClassNames className) {
        if(this.name().equalsIgnoreCase(className.name())){
            return true;
        } else {
            return false;
        }
    }
}
