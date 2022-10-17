package gymmanagement;

public class Family extends Member {
    private int guestPasses;

    //TODO: add guestPasses field to track number of passes left
    public Family(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
        this.guestPasses = 1;
    }
    public int getGuestPasses() {
        return this.guestPasses;
    }

    public void decrementGuestPasses() {
        this.guestPasses = this.guestPasses - 1;
    }

    public void incrementGuestPasses() {
        this.guestPasses = this.guestPasses + 1;
    }

    //TODO: membershipFee() should work the same as Member class, just return 59.99 instead
    /**
     * @return the value of the next bill; family fee * 3 months + one-time fee
     */
    @Override
    public double membershipFee() {
        return 29.99 + (59.99 * 3.0);
    }

    //TODO: override toString() to indicate family and show number of passes

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
