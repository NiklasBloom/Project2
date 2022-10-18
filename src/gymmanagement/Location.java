package gymmanagement;


/**
 * enum class for the different location values,
 * contains helper methods to return the enum constants depending on the given string.
 * @author Maxim Yacun
 * @author Niklas Bloom
 */
public enum Location{
    Bridgewater,
    Edison,
    Franklin,
    Piscataway,
    Somerville;

    /**
     * Takes a String locStr and if its characters matches one of the locations, then return
     * that location.
     *
     * @Param takes a string which should be one of the Gym Locations
     * @returns a location variable if the string parameter matches one of the locations
     */
    public static Location parseLocation(String locStr){
        String locNormalized = locStr.toLowerCase();
        Location location;
        return switch (locNormalized) {
            case "piscataway" -> Location.Piscataway;
            case "bridgewater" -> Location.Bridgewater;
            case "edison" -> Location.Edison;
            case "franklin" -> Location.Franklin;
            case "somerville" -> Location.Somerville;
            default -> null;
        };
    }

    /**
     * helper method that returns the supplementary information for a given location
     * @return the string with the full location of the current enum class value
     */
    public String returnFullLocation(){

        return switch (this.name().toLowerCase()) {
            case "piscataway" -> "PISCATAWAY, 08854, MIDDLESEX";
            case "bridgewater" -> "BRIDGEWATER, 08807, SOMERSET";
            case "edison" -> "EDISON, 08837, MIDDLESEX";
            case "franklin" -> "FRANKLIN, 08873, SOMERSET";
            case "somerville" -> "SOMERVILLE, 08876, SOMERSET";
            default -> null;
        };
    }

    /**
     * returns the corresponding enum class constant capitalized, used for comparing and printing purposes
     * @return the name of this Location but capitalized
     */
    public String returnCapitalized(){
        if(this.name() == null){
            return null;
        }
        String name = this.name().toUpperCase();
        return name;
    }
}
