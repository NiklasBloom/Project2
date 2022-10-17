package gymmanagement;

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
        return switch (locNormalized) {  //TODO: maybe make this its own method
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
     * @return
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

    public String returnCapitalized(){
        if(this.name() == null){
            return null;
        }
        String name = this.name().toUpperCase();
        return name;
    }
}
