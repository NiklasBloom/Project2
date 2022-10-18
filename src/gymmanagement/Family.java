package gymmanagement;


/**
 * subclass of Member,
 * new guest passes data field, keep traks of remaining guest passes for this member
 * @author Maxim Yacun
 * @author Niklas Bloom
 */
public class Family extends Member {
    private int guestPasses;

    /**
     *5 arg constructor, takes fname, lname, dob, expire, location
     */

    /**
     *  5 argument constructor
     * @param fname - given fname
     * @param lname - given lname
     * @param dob - given dob
     * @param expire - given expire
     * @param location - given loc
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
        this.guestPasses = 1;
    }

    /**
     * @return number of guestPasses
     */
    public int getGuestPasses() {
        return this.guestPasses;
    }

    /**
     * decrements guest passes by 1
     */
    public void decrementGuestPasses() {
        this.guestPasses = this.guestPasses - 1;
    }

    /**
     * increments guest passes by 1
     */
    public void incrementGuestPasses() {
        this.guestPasses = this.guestPasses + 1;
    }

    /**
     * @return the value of the next bill; family fee * 3 months + one-time fee
     */
    @Override
    public double membershipFee() {
        return 29.99 + (59.99 * 3.0);
    }

    /**
     to print Member toString, prints fname, lname, DOB, expire, and location
     @returns a String which is the member in String format
     */
    @Override
    public String toString() {
        //checks if membership is expired, and change wording from "expires" to "expired" accordingly
        return super.toString() + ", (Family) guest-pass remaining: " + String.valueOf(this.guestPasses);
    }


}
