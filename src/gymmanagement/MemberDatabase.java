package gymmanagement;

/**
 * Class which stores members in a array.
 * This class can add members, remove, find members
 * This class also takes members that are instances of Family members and Premium members
 * @author Maxim Yacun
 * @author Niklas Bloom
 */
public class MemberDatabase {
    private Member [] mlist;
    private int size;

    public static final int NOT_FOUND = -1;


    /**
     * no argument constructor
     * sets the size to 4 automatically
     */
    public MemberDatabase() {
        this.size=4;
        this.mlist=new Member[4];

    }



    /**
     * finds a specific member in the mlist, returns the index, or -1 if not found
     * @param member  - the member we look for in the array
     * @return the index of the member or -1 if not found
     */
    private int find(Member member) {
        if(isEmpty()){//if true that list is empty
            return NOT_FOUND;
        }

        for (int i = 0; i < size; i++){
            if(this.mlist[i] != null) {
                if (this.mlist[i].equals(member)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }


    /**
     *  Returns a reference to a Member in mlist that matches the given Member
     *  The given member only requires an fname, lname, and DOB, the other params
     *  do not matter for this method.
     * @param member, the member we want the full reference from the Database
     * @return the reference for this member in the Mlist with this members full data
     */
    public Member getMember(Member member) {
        if(this.isEmpty()){
            return null;
        }
        if(member == null){
            return null;
        } else if (member.getFname() == null || member.getLname() == null || member.getDob() == null){
            return null;
        }
        for (int i = 0; i < size; i++){
            if(this.mlist[i] != null) {
                if(this.mlist[i].equals(member)) {
                    return this.mlist[i];
                }
            }
        }
        return null;
    }


    /**
     * see if member is of type Family, then Look in the array for member, and return member casted as Family
     * @param member - the member we search for in the array and return as a Family type
     * @return the member parameter casted as a Family type
     */
    public Family getMemberFamily(Member member) {
        if(this.isEmpty()){
            return null;
        }
        if(member == null){
            return null;
        } else if (member.getFname() == null || member.getLname() == null || member.getDob() == null){
            return null;
        }
        if(member instanceof Family){
            Family memberFamily = (Family) member; //cast as a Family member
            for (int i = 0; i < size; i++){
                if(this.mlist[i] != null) {
                    if(this.mlist[i].equals(memberFamily)) {
                        return (Family) this.mlist[i]; // if family instance, returns member which is equal to this instance
                    }
                }
            }
        }

        return null;
    }

    /**
     * This method gets a member and returns that member from the database casted as a Premium member, if that member
     * has the premium membership
     * @param member, the member we want from the Database as a Premium instance
     * @return the member from the database that is of type Premium and matches member
     */
    public Premium getMemberPremium(Member member) {
        if(this.isEmpty()){
            return null;
        }
        if(member == null){
            return null;
        } else if (member.getFname() == null || member.getLname() == null || member.getDob() == null){
            return null;
        }
        if(member instanceof Premium){
            Premium memberPremium = (Premium) member; //cast as a Family member
            for (int i = 0; i < size; i++){
                if(this.mlist[i] != null) {
                    if(this.mlist[i].equals(memberPremium)) {
                        return (Premium) this.mlist[i]; // if family instance, returns member which is equal to this instance
                    }
                }
            }
        }

        return null;
    }


    /**
     * grows the database by four, called in the add method when out of room in the array
     */
    private void grow(){
        Member[] arr = new Member[this.size+4];
        System.arraycopy(this.mlist, 0, arr, 0, this.mlist.length);
        this.size = this.size + 4;
        this.mlist = arr;
    }


    /**
     * adds a new Member instance to the MemberDatabase
     * @param member - the member we want to add to the database
     * @return true if the member is added, false otherwise
     */
    public boolean add(Member member){
        if(this.find(member) >= 0){
            return false;
        }
        if(!this.checkCapacity()){ //check capacity if have to increase size by 4
            this.grow();
        }
        //get index of first null element in array
        int firstNull = -1;
        for (int i = 0; i < this.mlist.length; i++) { //should size be size of array or # of members?
            if (this.mlist[i] == null) {
                firstNull = i;
                break;
            }
        }
        mlist[firstNull] = member;
        //firstnull should never be -1, since we grew array if empty, a first null should always exist

        return true;
    }



    /**
     * helper method for add method to check whether there is room in mlist array to add another element.
     * If there is no room, the add element will grow() the array.
     * @return true if any element is null in the array, false if the array is full
     */
    public boolean checkCapacity(){
        boolean arrCapacity = false;
        for(int i = 0; i < this.size; i++){
            if(this.mlist[i] == null){
                arrCapacity = true;//is any element null? if so then not full
            }
        }
        return arrCapacity;
    }



    /**iterates through array, if an element != null, then there is a member existing in
     the list,  return false, if no member exists in whole array, return true
     * @return true if the mlist is empty, false otherwie
     */
    public boolean isEmpty() {
        //dont check for size, because could be any size but we just removed all the members
        // because we dont make the array smaller when we remove members
        for (int i = 0; i < size; i++){
            if (this.mlist[i] != null) {
                return false;
            }
        }
        return true;
    }


    /**
     * The remove() method remove a member from the list. This method maintains the relative order of the
     * members in the list after the remove, -3 points if this is not done correctly.
     * The container does not decrease in capacity.
     * @param member, the member we want to remove from the array
     * @return true if the member is removed, false otherwise
     */
    public boolean remove(Member member) {
        int memberIndex = find(member);
        if(memberIndex == NOT_FOUND){
            return false; //member does not exist in array
        }
        Member[] arr = new Member[this.size];
        boolean emptyFlag = true;
        for(int i = 0; i < this.size; i++){
            if(i == memberIndex)
                continue; //skip this index, dont copy this member over
            if(this.mlist[i] != null) {
                if(emptyFlag)
                    emptyFlag = false;
                arr[i] = this.mlist[i];
            }
        }

        this.mlist = arr;

        return true;
    }




    /**
    print the array contents as is
     */
    public void print () {
        if(isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        for(int i = 0; i < this.size; i++){
            if(this.mlist[i] != null) {
                System.out.println(this.mlist[i].toString());
            }
        }
    }

    /**
     * helper method used for the PF Command
     * this method prints the MemberList but also adds the members Membership Fee
     */
    public void printWithFees() {
        if(isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        for(int i = 0; i < this.size; i++){
            if(this.mlist[i] != null) {
                System.out.println(this.mlist[i].toString()
                        + ", Membership fee: $" + String.valueOf(this.mlist[i].membershipFee()));
            }
        }
    }

    /**
    PC command, to display the list of members in the database ordered by the county names and then the zip codes;
    that is, if the locations are in the same county, ordered by the zip codes.
    Ordered by

    1) Edison, 08837, Middlesex County
    2) Piscataway, 08854, Middlesex County
    3) Bridgewater, 08807, Somerset County
    4) Franklin, 08873, Somerset County
    5) Somerville, 08876, Somerset County

    these sorts use bubblesort
     */
    public void printByCounty() {
        if(isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if(this.mlist[j] == null || this.mlist[j+1] == null)
                    continue;
                if(this.mlist[j].locationNumeric() > this.mlist[j+1].locationNumeric()){
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j+1];
                    this.mlist[j+1] = temp;
                }
            }
        }
        this.print();
    }

    /**
    PD command, display the list of members in the database ordered by the expiration dates. If two expiration dates
    are the same, their order doesn’t matter.
     */
    public void printByExpirationDate() {
        if(isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if(this.mlist[j] == null || this.mlist[j+1] == null)
                    continue;
                if(this.mlist[j].getExpire().compareTo(this.mlist[j+1].getExpire()) > 0){
                    //getExpire returns a string, am comparing strings oops not work
                    //ok fixed, now getexpire returns Date object, CompareTo should not compare the Dates
                    //if j > j+1
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j+1];
                    this.mlist[j+1] = temp;

                }
            }
        }
        this.print();
    }



    /**
     * PN command, display the list of membersin the database ordered by the members’ last names and then first names;
     * that is, if two members have the same last name, ordered by the first name.
     */
    public void printByName() {
        if(isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if(this.mlist[j] == null || this.mlist[j+1] == null)
                    continue;
                if(this.mlist[j].compareTo(this.mlist[j+1])>0){ //this should use member compareTo method
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j+1];
                    this.mlist[j+1] = temp;
                }
                else if(this.mlist[j].compareTo(this.mlist[j+1])==0){
                    if(this.mlist[j].compareTo(this.mlist[j+1])>0){
                        //if last names are the same, then compare first names
                        Member temp = this.mlist[j];
                        this.mlist[j] = this.mlist[j+1];
                        this.mlist[j+1] = temp;
                    }
                }
            }
        }
        this.print();
    }
}
