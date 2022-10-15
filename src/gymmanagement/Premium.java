package gymmanagement;

public class Premium extends Family {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;
    private int guestPasses;

    //TODO: override guestPasses field to have 3 available
    public Premium(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
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
        String s = this.fname + " " + this.lname + ", DOB: " + this.dob.toString()
                + ((this.expire.futureDateCheck()) ? ", Membership expires " : ", Membership expired ")
                + this.expire.toString() + ", Location: " + this.fullLocation() + " (Premium) guest-pass remaining: " + String.valueOf(this.guestPasses);
        return s;
    }
}
