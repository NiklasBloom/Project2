package gymmanagement;
import java.util.ArrayList;

/**
You must include this Java class, which define a fitness class the members can check in. You can define the
instance variables and methods needed. You must use the enum class Time in this class or lose 2 points.
Your software shall not allow a member to check in if
1) the membership has expired
2) the member does not exist
3) the date of birth is invalid
4) the fitness class does not exist
5) there is a time conflict with other fitness classes
6) the member has already checked in

Your software shall not allow the member to drop the class if the member is not checked in, or the date of birth is
invalid, or the fitness class does not exist.

S command, display the fitness class schedule. A fitness class shall include the fitness class name, instructor’s
name, the time of the class, and the list of members who have already checked in today. For simplicity, assuming
the schedule is for “today” only, you do not need to handle a multiple-day schedule.

 This method now includes two Arraylists which hold the members and guests for a specific FitnessClass
 */

public class FitnessClass {
    private gymmanagement.Time time;
    private gymmanagement.Instructor instructor;
    private gymmanagement.ClassNames className;
    private ArrayList<Member> membersList;

    private ArrayList<Member> guestList;
    private gymmanagement.Location location;


    /**
     * four argument constructor, Can be used for creating a fitness Class from the classSchedule.txt
     * If a paramter does not match one of the enum constants for classname, time, instructor, or location,
     * then the value for that field is null
     *
     * @Param A String which should be one of the fitness Classes, Pilates, Cardio, or Spinning
     */
    public FitnessClass(String fitnessClass, String instructor, String time, String location) {
        this.className = ClassNames.returnClassName(fitnessClass);
        this.instructor = Instructor.returnInstructor(instructor); //this calls on method in Instructor Enum Class
        this.time = Time.returnTime(time);
        this.location = Location.parseLocation(location);
        this.membersList = new ArrayList<Member>();
        this.guestList = new ArrayList<Member>();
    }

    /**
     * Constructor for when time isn't specified; still enough info to find a class in the schedule.
     *
     * @param fitnessClass
     * @param instructor
     * @param location
     */
    public FitnessClass(String fitnessClass, String instructor, String location) {
        this.className = ClassNames.returnClassName(fitnessClass);
        this.instructor = Instructor.returnInstructor(instructor); //this calls on method in Instructor Enum Class
        this.location = Location.parseLocation(location);
    }


    /**
     * @return classname data field
     */
    public ClassNames getClassName() {
        return this.className;
    }

    /**
     * @return time data field
     */
    public Time getTime() {
        return this.time;
    }

    /**
     * @return location data field
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * @return instructor data field
     */
    public Instructor getInstructor() {
        return this.instructor;
    }

    /**
     * Prints the fitnessClass data in the below format:
     * <p>
     * Pilates - JENNIFER 9:30
     * * participants **
     * Jane Doe, DOB: 5/1/1996, Membership expires 3/30/2023, Location: EDISON, 08837, MIDDLESEX
     */

    public void print() {
        System.out.println(this.className.returnCapitalized() + " - " + this.instructor + ", " + this.time + ", " + this.location.returnCapitalized());
    }

    /**
     * prints the entire Member ArrayList
     */
    public void printWholeFitnessClass() {
        if(!this.membersList.isEmpty()){
            System.out.println("- Participants -");
            for(int i = 0; i < this.membersList.size(); i++) {
                System.out.println("\t" + membersList.get(i).toString());
            }
        }


    }

    /**
     * prints the entire guest ArrayList
     */
    public void printWholeFitnessClassGuests() {
        if(!this.guestList.isEmpty()){
            System.out.println("- Guests -");
            for(int i = 0; i < this.guestList.size(); i++) {
                System.out.println("\t" + guestList.get(i).toString());
            }
        }
    }

    /**
     * @return returns what the print method prints, as a string
     */
    public String returnPrintString() {
        return (this.className.returnCapitalized() + " - " + this.instructor + ", " + this.time + ", " + this.location.returnCapitalized());
    }

    public String returnPrintStringForTimeConflict() {
        return (this.instructor + ", " + this.time + ", " + this.location.returnFullLocation());
    }

    /**
     * Remove method for FitnessClass, Same as Member Database, uses ArrayList package
     *
     * @param member the member reference we want to search for and remove from this instance's mlist
     * @return true if the member is removed, false otherwise
     */
    public boolean remove(Member member) {
        if (member == null) {
            return false;
        }
        int index = this.find(member);
        if (index == -1) {
            return false; // member not in the array so we didnt remove
        } //therefore index must now be index of the member reference in the array
        this.membersList.remove(index);
        return true;
    }

    /**
     * Add method for FitnessClass, Same as MemberDatabase
     *
     * @return true if the member is added, false otherwise
     * @Param the member we want to add to the mlist
     */

    public boolean add(Member member) {
        if (member == null) {
            return false;
        }
        if (this.find(member) >= 0) { //already in the ArrayList
            return false;
        } //hence find(member) == -1 if we are here

        this.membersList.add(member);
        return true;
    }


    /**
     * Method used for finding if a member reference is in a FitnessClass, returns index or -1.
     *
     * @param member
     * @return the index of the member if in the ArrayList, returns -1 if not in the arrayList
     */
    private int find(Member member) {
        if (this.membersList.isEmpty()) {//if true that list is empty
            return -1;
        }

        for (int i = 0; i < this.membersList.size(); i++) {
            if (this.membersList.get(i) != null) {
                if (this.membersList.get(i).equals(member)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * returns the member reference from the mlist
     * Returns null if the member is not in the fitnessClass
     *
     * @param member the member we want the full member reference
     * @return the member reference that is in the mlist for the instance or null if not found
     */

    public Member getMember(Member member) {
        if (this.membersList.isEmpty()) {
            return null;
        }
        if (member == null) {
            return null;
        }
        int index = this.find(member); //gives the index of the given member if in the called FitnessClass
        //now have the index of the member
        if (index == -1) {
            return null; //member not in fitnessClass
        }

        return membersList.get(index);
    }


    /**
     * Tests if two fitnessClasses are equal by comparing Classname, instructor, and location
     *
     * @param obj which is supposed to take in a FitnessClass as parameter
     * @return false if not equal or null or obj is not a fitnessClass, returns true if
     * this fitnessclass is equal to the given fitnessClass
     */
    @Override
    public boolean equals(Object obj) {
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


    /**
     *Method to determine if the memberList ArrayList is empty or not
     * @return true if the memberList arrayList is empty, false if not empty
     */
    public boolean isEmpty(){

        if(this.membersList.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * this method is the same as isEmpty but for the guest ArrayList
     * @return true if the Guest ArrayList is empty, false otherwise
     */
    public boolean isEmptyguest(){

        if(this.guestList.size() == 0){
            return true;
        }
        return false;
    }



    /**
     * Remove method for FitnessClass, Same as Member Database, uses ArrayList package
     *
     * @param member the member reference we want to search for and remove from this instance's mlist
     * @return true if the member is removed, false otherwise
     */
    public boolean removeGuest(Member member) {
        if (member == null) {
            return false;
        }
        int index = this.findGuest(member);
        if (index == -1) {
            return false; // member not in the array so we didnt remove
        } //therefore index must now be index of the member reference in the array
        this.guestList.remove(index);
        return true;
    }

    /**
     * Add method for FitnessClass, Same as MemberDatabase
     * @return true if the member is added, false otherwise
     * @Param the member we want to add to the mlist
     */

    public boolean addGuest(Member member) {
        if (member == null) {
            return false;
        }
        this.guestList.add(member);
        return true;
    }


    /**
     * Method used for finding if a member reference is in a FitnessClass, returns index or -1.
     * @param member
     * @return the index of the member if in the ArrayList, returns -1 if not in the arrayList
     */
    private int findGuest(Member member) {
        if (this.guestList.isEmpty()) {//if true that list is empty
            return -1; //CONSTANTS ENUM CLASS
        }

        for (int i = 0; i < this.guestList.size(); i++) {
            if (this.guestList.get(i) != null) {
                if (this.guestList.get(i).equals(member)) {
                    return i;
                }
            }
        }
        return -1;
    }

}


