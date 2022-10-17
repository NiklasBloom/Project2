package gymmanagement;

public class Premium extends Family {
    private int guestPasses;


    /**
     * 5 arg constructor that takes fname, lname, dob, expire, and location, calls on the parent class
     * which is family
     */

    public Premium(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
        this.guestPasses = 3;
    }


    /**
     * returns the guestpasses
     * @return guestPasses datafield for this instance
     */
    public int getGuestPasses() {
        return guestPasses;
    }

    /**
     * decrements the guestPasses by 1
     */
    public void decrementGuestPasses() {
        this.guestPasses = this.guestPasses - 1;
    }

    /**
     * increments guestPasses by 1
     */
    public void incrementGuestPasses() {
        this.guestPasses = this.guestPasses + 1;
    }

    /**
     * @return the value of the next bill; family fee * 11 months
     */
    @Override
    public double membershipFee() {
        return 59.99 * 11.0;
    }

    /**
     to print Member toString, prints fname, lname, DOB, expire, and location
     @returns a String which is the member in String format
     */
    @Override
    public String toString() {
        //checks if membership is expired, and change wording from "expires" to "expired" accordingly
        return (new Member(this.getFname(), this.getLname(), this.getDob(), this.getExpire(), this.getLocation())).toString() + ", (Premium) guest-pass remaining: " + String.valueOf(this.guestPasses);
    }

}
