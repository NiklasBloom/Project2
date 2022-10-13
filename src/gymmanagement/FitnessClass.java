package gymmanagement;
import java.util.ArrayList;
//can load a list of fitnessClasses
//add instance variable for list of classes
//The ArrayList holds members I assume

/**
You must include this Java class, which define a fitness class the members can check in. You can define the
instance variables and methods needed. You must use the enum class Time in this class or lose 2 points.
Your software shall not allow a member to check in if
o the membership has expired //just have to use the futureDateCheck() method
o the member does not exist //just the find method
o the date of birth is invalid //just the isvalid method
o the fitness class does not exist //idk just has to match spelling
o there is a time conflict with other fitness classes // find method in other two fitness classes?
o the member has already checked in // just the find method in the fitness class

Your software shall not allow the member to drop the class if the member is not checked in, or the date of birth is
invalid, or the fitness class does not exist.

S command, display the fitness class schedule. A fitness class shall include the fitness class name, instructor’s
name, the time of the class, and the list of members who have already checked in today. For simplicity, assuming
the schedule is for “today” only, you do not need to handle a multiple-day schedule.
 */

public class FitnessClass{
    private Time time;
    private Instructor instructor;
    private String className;
    private ArrayList<Member> members; // I think this is correct
    private ArrayList<Member> guests; // separate array for guests?
    private Member.Location location;


    /**
     Location constants for Location data field
     */
    enum Location{
        Bridgewater,
        Edison,
        Franklin,
        Piscataway,
        Somerville;

        /**
         Takes a String locStr and if its characters matches one of the locations, then return
         that location.
         @Param takes a string which should be one of the Gym Locations
         @returns a location variable if the string parameter matches one of the locations
         */
        public static Member.Location parseLocation(String locStr){
            String locNormalized = locStr.toLowerCase();
            Member.Location location;
            return switch (locNormalized) {  //TODO: maybe make this its own method
                case "piscataway" -> Member.Location.Piscataway;
                case "bridgewater" -> Member.Location.Bridgewater;
                case "edison" -> Member.Location.Edison;
                case "franklin" -> Member.Location.Franklin;
                case "somerville" -> Member.Location.Somerville;
                default -> null;
            };
        }
    }

    /**
    define the time of a fitness class in hh:mm
    Defines the time variable for FitnessClass
     */
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

    /**
    constants for instructors, instructor names
     */
    public enum Instructor{
        JENNIFER,
        DENISE,
        KIM,
        DAVIS,
        EMMA;

        public static Instructor returnInstructor(String instructorName){
            instructorName = instructorName.toLowerCase();
            switch(instructorName){
                case("jennifer"):
                    return Instructor.JENNIFER;
                case("denise"):
                    return Instructor.DENISE;
                case("kim"):
                    return Instructor.KIM;
                case("davis"):
                    return Instructor.DAVIS;
                case("emma"):
                    return Instructor.EMMA;
                default:
                    return null;
            }
        }
    }

    /**
    four argument constructor, Can be used for creating a fitness Class from the classSchedule.txt
     If a paramter does not match one of the enum constants for classname, time, instructor, or location,
     then the value for that field is null
     @Param A String which should be one of the fitness Classes, Pilates, Cardio, or Spinning
     */
    public FitnessClass(String fitnessClass, String instructor, String time, String Location) {
        this.className = fitnessClass;
        this.instructor = Instructor.returnInstructor(instructor); //this calls on method in Instructor Enum Class
        this.time = Time.returnTime(time);
        this.location = Member.Location.parseLocation(Location);

    }



    public String getClassName() {
        return this.className;
    }


    public Time getTime() {
        return this.time;
    }



    /**
    Prints the fitnessClass data in the below format:

    Pilates - JENNIFER 9:30
        ** participants **
            Jane Doe, DOB: 5/1/1996, Membership expires 3/30/2023, Location: EDISON, 08837, MIDDLESEX
     */

    public void print() {
        System.out.println(this.className + " - " + this.instructor + " " + this.time);
        /*if(!isEmpty()) {
        System.out.println("\t** participants **");
            //super.print();
        }*/
    }

    /**
     Remove method for FitnessClass, Same as Member Database
     @param member the member reference we want to search for and remove from this instance's mlist
     @return true if the member is removed, false otherwise
     */
    public boolean remove(Member member) {


        return true;
    }

    /**
     Add method for FitnessClass, Same as MemberDatabase
     @Param the member we want to add to the mlist
     @return true if the member is added, false otherwise
     */

    public boolean add(Member member) {
        return true;

    }

    /**
    returns the member reference from the mlist
     @param member the member we want the full member reference
     @return the member reference that is in the mlist for the instance
     */

    public Member getMember(Member member) {
        Member jeff = new Member("jeff","jeff",new Date("abruh"));
        return jeff;
    }

}
