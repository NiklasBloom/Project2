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

public class FitnessClass extends MemberDatabase{
    private Time time;
    private Instructor instructor;
    private String className;
    private ArrayList<Member> members; // i think this is correct
    private ArrayList<Member> guests; // separate array for guests?

    /**
    define the time of a fitness class in hh:mm
    Defines the time variable for FitnessClass
     */
    public enum Time{
        PILATES(9 ,30),
        SPINNING(14 , 0),
        CARDIO(14 , 0);

        private final int hour;
        private final int minutes;

        Time(int hour, int minutes) {
            this.hour = hour;
            this.minutes = minutes;
        }
        /**
        returns the time in String format
         @returns the time of the instance in String format
         */
        @Override
        public String toString(){
            return String.format("%d:%02d", this.hour, this.minutes);
        }

        /**
        Compares and sees if two fitnessClass times are the same
         @Param a time to see if equal to this time
         @returns a boolean, true if equal, false otherwise
         */
        public boolean equals(Time otherTime){
            return (this.hour == otherTime.hour && this.minutes == otherTime.minutes);
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
        EMMA
    }

    /**
    one argument constructor, given the fitnessClass as a strin calls the Superclass
     which is MemberDatabase
     @Param A String which should be one of the fitness Classes, Pilates, Cardio, or Spinning
     */
    public FitnessClass(String fitnessClass) {
        super();
        this.className = fitnessClass;
        this.instructor = setInstructor(fitnessClass);
        this.time = setTime(fitnessClass);
    }

    /**
    sets the instructor datafield according to the fitnessClass parameter
     @Param A String which should be one of the fitness Classes, Pilates, Cardio, or Spinning
     @returns the instructor of the given fitnessClass
     */
    public Instructor setInstructor(String fitnessClass){
        String fitnessClassLowerCase = fitnessClass.toLowerCase();
        switch(fitnessClassLowerCase){
            case "pilates":
                return Instructor.JENNIFER;
            case "cardio":
                return Instructor.KIM;
            case "spinning":
                return Instructor.DENISE;
            default:
                return null; //just to detect and raise an error
        }
    }


    /**
    Sets the time data field according to the FitnessClass String given, If it matches with
     one of the FitnessClasses
     @Param A String which should be one of the fitness Classes, Pilates, Cardio, or Spinning
     @return the time of the class
     */
    public Time setTime(String fitnessClass){
        String fitnessClassLowerCase = fitnessClass.toLowerCase();
        switch(fitnessClassLowerCase){
            case "pilates":
                return Time.PILATES;
            case "cardio":
                return Time.CARDIO;
            case "spinning":
                return Time.SPINNING;
            default:
                return null; //just to detect and raise an error
        }
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
    @Override
    public void print() {
        System.out.println(this.className + " - " + this.instructor + " " + this.time);
        if(!isEmpty()) {
            System.out.println("\t** participants **");
            super.print();
        }
    }

    /**
     Remove method for FitnessClass, Same as Member Database
     @Param the member reference we want to search for and remove from this instance's mlist
     @return true if the member is removed, false otherwise
     */
    @Override
    public boolean remove(Member member) {
        return super.remove(member);
    }

    /**
     Add method for FitnessClass, Same as MemberDatabase
     @Param the member we want to add to the mlist
     @return true if the member is added, false otherwise
     */
    @Override
    public boolean add(Member member) {
        return super.add(member);
    }

    /**
    returns the member reference from the mlist
     @Param the member we want the full member reference
     @return the member reference that is in the mlist for the instance
     */
    @Override
    public Member getMember(Member member) {
        return super.getMember(member);
    }

}
