package gymmanagement;

public enum Time{
    MORNING(9 ,30), //MORNING 9:30
    AFTERNOON(14 , 0), //AFTERNOON 14:00
    EVENING(18 , 30); //EVENING 18:30

    private final int hour;
    private final int minutes;

    Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }
    /**
     returns the time in String format
     @return the time of the instance in String format
     */
    @Override
    public String toString(){
        return String.format("%d:%02d", this.hour, this.minutes);
    }

    /**
     Compares and sees if two fitnessClass times are the same
     @param otherTime a time to see if equal to this time
     @returns a boolean, true if equal, false otherwise
     */
    public boolean equals(Time otherTime){
        return (this.hour == otherTime.hour && this.minutes == otherTime.minutes);
    }

    public static Time returnTime(String time){
        if(time == null){
            return null;
        }
        time = time.toLowerCase();
        switch(time){
            case("morning"):
                return Time.MORNING;
            case("afternoon"):
                return Time.AFTERNOON;
            case("evening"):
                return Time.EVENING;
            default:
                return null;
        }
    }
}
