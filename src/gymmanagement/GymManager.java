/**
 * GymManager keeps a MemberDatabase and an array of FitnessClass objects, processing commands to alter those from standard input.
 * Reads input using a Scanner object, tokenizing each line by groups of non-whitespace characters.
 * Uses a continuous while loop to read from standard input until a termination command is given.
 * Reports status and results by printing to standard output.
 *
 * @author Maxim Yacun, Niklas Bloom
 */
package gymmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Defines a GymManager object with the main run() method, helpers to execute commands, and databases to store information.
 */
public class GymManager {
    private MemberDatabase DB;
    private ClassSchedule classes; //array of FitnessClasses, which are arrays of members

    /**
     * Uses a while loop to continuously read command lines from standard input and manage the member and class databases using helper methods.
     */
    public void run() {
        System.out.println("Gym Manager running...");
        DB = new MemberDatabase();
        classes = new ClassSchedule();

        Scanner sc = new Scanner(System.in);
        String currentLine = sc.nextLine();

        while (true) {
            StringTokenizer lineTokens = new StringTokenizer(currentLine);

            if (lineTokens.hasMoreTokens()) {
                if (commandParser(lineTokens))
                    break;
            } else { //skip blank line and print a blank line
                System.out.println();
            }
            currentLine = sc.nextLine();
        }
        System.out.println("Gym Manager terminated.");
    }

    /**
     * Parses standard input into appropriate commands and passes information to helper methods to handle command work.
     * @param lineTokens StringTokenizer object of the command line to parse.
     * @return true if we get a Q command to signify termination of the program, false otherwise; signifying run() should continue.
     */
    private boolean commandParser(StringTokenizer lineTokens) {
        String command = lineTokens.nextToken();
        switch (command) {
            case "Q" -> { return true; }
            case "A" -> addMember(lineTokens, "Member");
            case "AF" -> addMember(lineTokens, "Family");
            case "AP" -> addMember(lineTokens, "Premium");
            case "R" -> rmMember(lineTokens);
            case "P", "PC", "PN", "PD", "PF" -> {
                if (DB.isEmpty()) {
                    System.out.println("Member database is empty!");
                    return false;
                }
                switch (command) {
                    case "P" -> {
                        System.out.println("\n-list of members-");
                        DB.print();
                    } case "PC" -> {
                        System.out.println("\n-list of members sorted by county and zipcode-");
                        DB.printByCounty();
                    } case "PN" -> {
                        System.out.println("\n-list of members sorted by last name, and first name-");
                        DB.printByName();
                    } case "PD" -> {
                        System.out.println("\n-list of members sorted by membership expiration date-");
                        DB.printByExpirationDate();
                    } case "PF" -> {
                        System.out.println("\n-list of members with membership fees-");
                        DB.printWithFees();
                    }
                }
                System.out.println("-end of list-\n");
            }
            case "S" -> classes.print();
            case "C" -> checkInMember(lineTokens);
            case "D" -> dropClass(lineTokens);
            case "LS" -> addClasses();
            case "LM" -> loadMembers();
            default -> System.out.println(command + " is an invalid command!");
        }
        return false;
    }

    /**
     * Helper method to perform isValid(), over18(), and futureDateCheck() checks on a Date
     * @param dob a Date object to check against all validity conditions
     * @return true if DOB passes all checks, false otherwise
     */
    private boolean dobCheck(Date dob){
        if (!dob.isValid()) { //returns false if general errors in date.
            System.out.println("DOB " + dob.toString() + ": invalid calendar date!");
            return false;
        }
        if (dob.futureDateCheck()) { //return true if this.date > current date
            System.out.println("DOB " + dob.toString() + ": cannot be today or a future date!");
            return false;
        }
        if (!dob.over18()) { //returns true if 18 or older
            System.out.println("DOB " + dob.toString() + ": must be 18 or older to join!");
            return false;
        }
        return true;
    }

    /**
     * Helper method to execute the "A" command and add a member to the MemberDatabase DB.
     * Reads member information from a StringTokenizer of the command line.
     * Uses helper methods from the Date class to check Date parameters' validity
     * rejects if:
     *     Any date is not a valid calendar date
     *     The date of birth is today or a future date
     *     Member is less than 18 years old
     *     City name is invalid, that is, the gym location doesn’t exist
     *
     * Prints result of command execution to terminal.
     * @param dataTokens StringTokenizer object of the necessary information to process given by the command line.
     */
    private void addMember(StringTokenizer dataTokens, String type) {
        String fname = new String(dataTokens.nextToken());
        String lname = new String(dataTokens.nextToken());
        Date dob = new Date(dataTokens.nextToken());
        if(!dobCheck(dob))
            return;
        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.MONTH, 3);
        Date expire = new Date(String.valueOf(exp.get(Calendar.MONTH) + 1) + "/" + String.valueOf(exp.get(Calendar.DATE))
                                + "/" + String.valueOf(exp.get(Calendar.YEAR)));
        String locParam = dataTokens.nextToken();
        Location location = Location.parseLocation(locParam);
        if (location == null) {
            System.out.println(locParam + ": invalid location!");
            return;
        }
        switch(type){ // make a helper method for this?
            case("Member") -> {
                Member newMem = new Member(fname, lname, dob, expire, location);
                if (!DB.add(newMem)) {
                    System.out.println(fname + " " + lname + " is already in the database.");
                } else {
                    System.out.println(fname + " " + lname + " added.");
                }
            }
            case("Family") -> {
                Family newMem = new Family(fname, lname, dob, expire, location);
                if (!DB.add(newMem)) {
                    System.out.println(fname + " " + lname + " is already in the database.");
                } else {
                    System.out.println(fname + " " + lname + " added.");
                }
            }
            case("Premium") -> {
                Premium newMem = new Premium(fname, lname, dob, expire, location);
                if (!DB.add(newMem)) {
                    System.out.println(fname + " " + lname + " is already in the database.");
                } else {
                    System.out.println(fname + " " + lname + " added.");
                }
            }
        }
    }

    /**
     * Helper method to execute the "R" command and cancel/remove a member from the MemberDatabase DB.
     * Reads member information from a StringTokenizer of the command line.
     * Prints result of command execution to terminal.
     * @param dataTokens StringTokenizer object of the necessary information to process given by the command line.
     */
    private void rmMember(StringTokenizer dataTokens) {
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        Date dob = new Date(dataTokens.nextToken());
        Member rmMem = new Member(fname, lname, dob, null, null);
        if (!DB.remove(rmMem)) {
            System.out.println(fname + " " + lname + " is not in the database.");
        } else {
            System.out.println(fname + " " + lname + " removed.");
        }
    }

    /**
     * Helper method to execute the "C" command and add a member to a class schedule.
     * Reads member information from a StringTokenizer of the command line.
     * rejects if:
     *      the membership has expired (by checking if expiration is a future date)
     *      the member does not exist (by checking if the member is in the database)
     *      the date of birth is invalid (using the isValid() method of Date class)
     *      the fitness class does not exist
     *      there is a time conflict with other fitness classes (by checking if they are a member of a class at the same time)
     *      the member has already checked in (by checking if they are already a member of the chosen class)
     *
     * Prints result of command execution to terminal.
     * @param dataTokens StringTokenizer object of the necessary information to process given by the command line.
     */
    private void checkInMember(StringTokenizer dataTokens) {
        String className = dataTokens.nextToken();
        String instructor = dataTokens.nextToken();
        String location = dataTokens.nextToken();
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        Date dob = new Date(dataTokens.nextToken()); //check if dob is valid
        if(!dobCheck(dob))
            return;
        Member testMember = new Member(fname, lname, dob); //use given info to search for member in DB
        Member dbMember = DB.getMember(testMember); //get the reference to the member in the DB, if it matches
        if (dbMember == null) {
            System.out.println(fname + " " + lname + " " + dob.toString() + " is not in the database.");
            return;
        }
        if (Instructor.returnInstructor(instructor) == null){
            System.out.println(instructor + " - instructor does not exist.");
            return;
        }

        Date expire = dbMember.getExpire();
        if (!expire.futureDateCheck()) {
            System.out.println(fname + " " + lname + " " + dob.toString() + " membership expired.");
            return;
        }
        FitnessClass choiceClass = classes.getFitnessClass(new FitnessClass(className, instructor, location)); // For choiceClass, time is not given
        if (choiceClass == null) { //checks if class exists
            System.out.println(className + " - class does not exist.");
            return;
        }
        if (choiceClass.getMember(dbMember) != null) { //check if member is already in the class
            System.out.println(fname + " " + lname + " has already checked in.");
            return;
        }

        if(timeConflictCheck(choiceClass,dbMember))
            return;

        //TODO: Mary Lindsey checking in BRIDGEWATER, 08807, SOMERSET - standard membership location restriction.
        if(!locationAllowed(choiceClass, dbMember)) {//O location is not allowed
            System.out.println(fname + " " + lname + " checking in " + choiceClass.getLocation().returnFullLocation()
                                + " - standard membership location restriction.");
            return; // need full location
        }

        choiceClass.add(dbMember); //having passed all the above checks, adds the member to the chosen class
        System.out.println(fname + " " + lname + " checked in " + choiceClass.returnPrintString());
        //now print whole fitnessClass, Member.toString, loop through array
        choiceClass.printWholeFitnessClass();
    }

    /**
     * helper method that iterates through fitnessClasses that have a time conflict,
     * then goes through the conflicted classes, if they are different, and have same time,
     * but it doesnt check if the member is in both right?
     * @param choiceClass
     * @param member
     */
    public boolean timeConflictCheck(FitnessClass choiceClass, Member member){
        //get list of all conflicting classes
        FitnessClass[] conflicts = classes.conflicts(choiceClass);
        for (FitnessClass aClass : conflicts) {
            if (aClass != null) {
                if (!aClass.equals(choiceClass) && (aClass.getMember(member) != null)) {
                    System.out.println("Time conflict - " + choiceClass.getClassName() + "- " +
                            choiceClass.returnPrintStringForTimeConflict());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean locationAllowed(FitnessClass choiceClass, Member member){
        return choiceClass.getLocation() == member.getLocation() || member instanceof Family;
    }


    /**
     * Helper method to execute the "D" command and remove a member from a class schedule.
     * Reads member information from a StringTokenizer of the command line.
     * rejects if:
     *      the member is not checked in (by against checking the database of members signed up for the class)
     *      the date of birth is invalid (using helper methods from the Date class)
     *      the fitness class does not exist (checking against the list of available classes)
     *
     * Prints result of command execution to terminal.
     * @param dataTokens StringTokenizer object of the necessary information to process given by the command line.
     */
    private void dropClass(StringTokenizer dataTokens) {
        String className = new String(dataTokens.nextToken());
        String instructor = dataTokens.nextToken();
        String location = dataTokens.nextToken();
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken()); //check if dob is valid
        if(!dobCheck(dob))
            return;

        Member classMember = new Member(fname, lname, dob); //use given info to search for member in class

        FitnessClass choiceClass = classes.getFitnessClass(new FitnessClass(className, instructor, null, location));
        if (choiceClass == null) { //checks if class exists
            System.out.println(className + "  - class does not exist.");
            return;
        }

        if (!choiceClass.remove(classMember)) {
            System.out.println(fname + " " + lname + " is not a participant in " + className + ".");
        } else {
            System.out.println(fname + " " + lname + " dropped " + className + ".");
        }
    }

    private void addClasses() {
        Scanner schedFile;
        try {
            schedFile = new Scanner(new File("classSchedule.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File classSchedule.txt not found!");
            return;
        }

        System.out.println("\n-Fitness classes loaded-");
        while (schedFile.hasNext()) {
            StringTokenizer lineTokens =
                    new StringTokenizer(schedFile.nextLine().replaceAll("\\p{C}", ""));

            String className = lineTokens.nextToken();
            String instructor = lineTokens.nextToken();
            String time = lineTokens.nextToken();
            String location = lineTokens.nextToken(); // if any are null then continue? never null, being set to null by constructor
            FitnessClass newClass = new FitnessClass(className, instructor, time, location);
            classes.add(newClass);
            newClass.print();
        }
        System.out.println("-end of class list.\n");
    }

    private void loadMembers() {
        Scanner memFile;
        try {
            memFile = new Scanner(new File("memberList.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File memberList.txt not found!");
            return;
        }

        System.out.println("\n-list of members loaded-");
        while (memFile.hasNext()) {
            StringTokenizer dataTokens =
                    new StringTokenizer(memFile.nextLine().replaceAll("\\p{C}", "")); //removes all invisible characters

            String fname = new String(dataTokens.nextToken());
            String lname = new String(dataTokens.nextToken());
            Date dob = new Date(new String(dataTokens.nextToken()));
            if(!dobCheck(dob))
                return;

            Date expire = new Date(new String(dataTokens.nextToken()));
            if (!expire.isValid()) { //returns false if general errors in date.
                System.out.println("Expiration date " + expire.toString() + ": invalid calendar date!");
                return;
            }
            String locParam = new String(dataTokens.nextToken());
            Location location = Location.parseLocation(locParam);
            if (location == null) {
                System.out.println(locParam + ": invalid location!");
                return;
            }
            Member newMem = new Member(fname, lname, dob, expire, location);
            if (!DB.add(newMem)) {
                System.out.println(fname + " " + lname + " is already in the database.");
            } else {
                System.out.println(newMem);
            }
        }
        System.out.println("-end of list-\n");
    }
}
