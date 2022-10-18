package gymmanagement;

/**
 * Defines a standard Member with attributes and methods used for creating and comparing members.
 * Superclass of Premium and Family
 * @author Maxim Yacun
 * @author Niklas Bloom
 */
public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private gymmanagement.Date dob;
    private gymmanagement.Date expire;
    private gymmanagement.Location location;


    /**
    constructor for Member Class with each parameter given
     @Param first name, last name, Date of Birth, Date of Membership expiration,
     and Gym Location
     */
    public Member(String fname, String lname,
                  Date dob, Date expire, Location location) {

        this.location = location;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
    }



    /**
    constructor method if only fname, lname, and DOB is given.
    Used for the checkIn method when only those dat fields are available
     @Param First name, Last name, and Date of Birth
     */
    public Member(String fname, String lname,
                  Date dob) {

        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.location = null;
        this.expire = null;
    }

    /**
     * @return fname
     */
    public String getFname(){
        return this.fname;
    }

    /**
     *
     * @return Lname data field
     */
    public String getLname(){
        return this.lname;
    }

    /**
     * @return dob data field
     */
    public Date getDob(){
        return this.dob;
    }

    /**
     * @return expire data field
     */
    public Date getExpire(){
        return this.expire;
    }

    /**
     * @return location data field
     */
    public Location getLocation(){
        return this.location;
    }


    /**
    to see if two Members are equal to eachother.
    Tests if Members are equal by seeing if the fname, lname and DOB all match
     @Param takes an obj which should be of the member Class
     @returns true if this member equals the obj parameter given, returns false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Member) {
            Member student = (Member) obj; //casting
            return student.fname.equalsIgnoreCase(this.fname)
                    && student.lname.equalsIgnoreCase(this.lname)
                    && student.dob.equals(this.dob);
        }

        return false;
    }

    /**
    to print Member toString, prints fname, lname, DOB, expire, and location
     @returns a String which is the member in String format
     */
    @Override
    public String toString() {
        //checks if membership is expired, and change wording from "expires" to "expired" accordingly
        String s = this.fname + " " + this.lname + ", DOB: " + this.dob.toString()
                + ((this.expire.futureDateCheck()) ? ", Membership expires " : ", Membership expired ")
                + this.expire.toString() + ", Location: " + this.fullLocation();
        return s;
    }

    /**
    Based off the Members location, this returns the full String location for the toString() method
     @returns the string of the full location based off this members Location data field value
     */
    public String fullLocation() {
        return switch (location) {
            case Edison -> ("EDISON, 08837, MIDDLESEX");
            case Piscataway -> ("PISCATAWAY, 08854, MIDDLESEX");
            case Bridgewater -> ("BRIDGEWATER, 08807, SOMERSET");
            case Franklin -> ("FRANKLIN, 08873, SOMERSET");
            case Somerville -> ("SOMERVILLE, 08876, SOMERSET");
        };
    }
    /**
    returns a numeric value for each county/zip code for sorting purposes
     1) Edison, 08837, Middlesex County
    2) Piscataway, 08854, Middlesex County
    3) Bridgewater, 08807, Somerset County
    4) Franklin, 08873, Somerset County
    5) Somerville, 08876, Somerset County
     @returns an integer based off of this members location value
     */
    public int locationNumeric() {
        int locationNumber = -1;
        switch (this.location) {
            case Edison:
                locationNumber = 1;
                break;
            case Piscataway:
                locationNumber = 2;
                break;
            case Bridgewater:
                locationNumber = 3;
                break;
            case Franklin:
                locationNumber = 4;
                break;
            case Somerville:
                locationNumber = 5;
                break;
        }
        return locationNumber;
    }





    /**
     compareTo() method is used when sorting by names
     if s1 > s2, it returns 1
     if s1 < s2, it returns -1
     if s1 == s2, it returns 0
     @Param the integer we want to compare to the Member this method is called on
     @returns an integer based off the CompareTo description
    */
    @Override
    public int compareTo(Member member) {
        if(this.getLname().compareTo(member.getLname()) > 0){ //this last name is greater
            return 1;
        }
        if(this.getLname().compareTo(member.getLname()) < 0){ //this last name is lesser
            return -1;
        }
        if(this.getLname().compareTo(member.getLname()) == 0){ //this last name is equal
            if(this.getFname().compareTo(member.getFname()) > 0){//this first name greater
                return 1;
            }
        }
        if(this.getLname().compareTo(member.getLname()) == 0){ //this last name is equal
            if(this.getFname().compareTo(member.getFname()) < 0){//this first name lesser
                return -1;
            }
        }
        if(this.getLname().compareTo(member.getLname()) == 0){ //this last name is equal
            if(this.getFname().compareTo(member.getFname()) == 0){//this first name equal
                return 0;
            }
        }
        return 0;
    }

    /**
     * @return the value of the next bill; family fee * 3 months + one-time fee
     */
    public double membershipFee() {
        return 29.99 + (39.99 * 3.0);
    }


}

