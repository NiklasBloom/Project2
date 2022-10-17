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
    private ArrayList<Member> membersList; // I think this is correct
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

        /**
         * returns the instructor constant from the given instructor string
         * @param instructorName
         * @return
         */
        public static Instructor returnInstructor(String instructorName){
            instructorName = instructorName.toLowerCase();
            return switch (instructorName) {
                case ("jennifer") -> Instructor.JENNIFER;
                case ("denise") -> Instructor.DENISE;
                case ("kim") -> Instructor.KIM;
                case ("davis") -> Instructor.DAVIS;
                case ("emma") -> Instructor.EMMA;
                default -> null;
            };
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
        this.membersList = new ArrayList<Member>();
    }

    /**
     * Constructor for when time isn't specified; still enough info to find a class in the schedule.
     * @param fitnessClass
     * @param instructor
     * @param Location
     */
    public FitnessClass(String fitnessClass, String instructor, String Location) {
        this.className = fitnessClass;
        this.instructor = Instructor.returnInstructor(instructor); //this calls on method in Instructor Enum Class
        this.location = Member.Location.parseLocation(Location);
    }



    public String getClassName() {
        return this.className;
    }


    public Time getTime() {
        return this.time;
    }

    public Member.Location getLocation(){
        return this.location;
    }



    /**
    Prints the fitnessClass data in the below format:

    Pilates - JENNIFER 9:30
        ** participants **
            Jane Doe, DOB: 5/1/1996, Membership expires 3/30/2023, Location: EDISON, 08837, MIDDLESEX
     */

    public void print() {
        System.out.println(this.className.toUpperCase() + " - " + this.instructor + ", " + this.time + ", " + this.location.returnCapitalized());
        /*if(!isEmpty()) {
        System.out.println("\t** participants **");
            //super.print();
        }*/
    }

    /**
     Remove method for FitnessClass, Same as Member Database, uses ArrayList package
     @param member the member reference we want to search for and remove from this instance's mlist
     @return true if the member is removed, false otherwise
     */
    public boolean remove(Member member) {
        if(member == null){
            return false;
        }
        int index = this.find(member);
        if(index == -1){
            return false; // member not in the array so we didnt remove
        } //therefore index must now be index of the member reference in the array
        this.membersList.remove(index);
        return true;
    }

    /**
     Add method for FitnessClass, Same as MemberDatabase
     @Param the member we want to add to the mlist
     @return true if the member is added, false otherwise
     */

    public boolean add(Member member) {
        if(member == null){
            return false;
        }
        if(this.find(member) >= 0){ //already in the ArrayList
            return false;
        } //hence find(member) == -1 if we are here

        this.membersList.add(member);
        return true;
    }


    /**
     * Method used for finding if a member reference is in a FitnessClass, returns index or -1.
     * @param member
     * @return the index of the member if in the ArrayList, returns -1 if not in the arrayList
     */
    private int find(Member member) {
        if(this.membersList.isEmpty()){//if true that list is empty
            return -1; //TODO: CONSTANTS ENUM CLASS
        }

        for (int i = 0; i < this.membersList.size(); i++){
            if(this.membersList.get(i) != null) {
                if (this.membersList.get(i).equals(member)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
    returns the member reference from the mlist
     Returns null if the member is not in the fitnessClass
     @param member the member we want the full member reference
     @return the member reference that is in the mlist for the instance or null if not found
     */

    public Member getMember(Member member) {
        if(this.membersList.isEmpty()){
            return null;
        }
        if(member == null){
            return null;
        }
        int index = this.find(member); //gives the index of the given member if in the called FitnessClass
        //now have the index of the member
        if(index == -1){
            return null; //member not in fitnessClass
        }
        return membersList.get(index);
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj instanceof FitnessClass) {
            FitnessClass aClass = (FitnessClass) obj; //casting
            return aClass.className.equalsIgnoreCase(this.className)
                    && aClass.instructor.equals(this.instructor)
                    && aClass.location.equals(this.location); //added time
        }
        return false;
    }
}
