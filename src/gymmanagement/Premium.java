package gymmanagement;

public class Premium extends Family {

    /**
     * 5 arg constructor that takes fname, lname, dob, expire, and location, calls on the parent class
     * which is family
     */

    public Premium(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
        super.incrementGuestPasses();
        super.incrementGuestPasses();
    }

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
        return (new Member(this.getFname(), this.getLname(), this.getDob(), this.getExpire(), this.getLocation())).toString() + ", (Premium) guest-pass remaining: " + String.valueOf(super.getGuestPasses());
    }

}
