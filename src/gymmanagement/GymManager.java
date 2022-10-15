/**
 * GymManager keeps a MemberDatabase and an array of FitnessClass objects, processing commands to alter those from standard input.
 * Reads input using a Scanner object, tokenizing each line by groups of non-whitespace characters.
 * Uses a continuous while loop to read from standard input until a termination command is given.
 * Reports status and results by printing to standard output.
 *
 * @author Maxim Yacun, Niklas Bloom
 */
package gymmanagement;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Defines a GymManager object with the main run() method, helpers to execute commands, and databases to store information.
 */
public class GymManager {
    private MemberDatabase DB;
    private FitnessClass[] classes;

    /**
     * Uses a while loop to continuously read command lines from standard input and manage the member and class databases using helper methods.
     */
    public void run() {
        System.out.println("Gym Manager running...");
        DB = new MemberDatabase();
        //classes = new FitnessClass[]{new FitnessClass("Morning"), new FitnessClass("Spinning"), new FitnessClass("Cardio")};
        //commented this out
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
            case "A" -> addMember(lineTokens);
            case "R" -> rmMember(lineTokens);
            case "P", "PC", "PN", "PD" -> {
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
                    }
                }
                System.out.println("-end of list-\n");
            }
            case "S" -> {
                System.out.println("\n-Fitness classes-");
                for (FitnessClass aClass : classes) aClass.print();
                System.out.println();
            }
            case "C" -> checkInMember(lineTokens);
            case "D" -> dropClass(lineTokens);
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
        if (!dob.over18()) { //returns true if 18 or older
            System.out.println("DOB " + dob.toString() + ": must be 18 or older to join!");
            return false;
        }
        if (dob.futureDateCheck()) { //return true if this.date > current date
            System.out.println("DOB " + dob.toString() + ": cannot be today or a future date!");
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
    private void addMember(StringTokenizer dataTokens) {
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        Date dob = new Date(dataTokens.nextToken());
        if(!dobCheck(dob))
            return;

        Date expire = new Date(dataTokens.nextToken());
        if (!expire.isValid()) { //returns false if general errors in date.
            System.out.println("Expiration date " + expire.toString() + ": invalid calendar date!");
            return;
        }
        String locParam = dataTokens.nextToken();
        Member.Location location = Member.Location.parseLocation(locParam);
        if (location == null) {
            System.out.println(locParam + ": invalid location!");
            return;
        }
        Member newMem = new Member(fname, lname, dob, expire, location);
        if (!DB.add(newMem)) {
            System.out.println(fname + " " + lname + " is already in the database.");
        } else {
            System.out.println(fname + " " + lname + " added.");
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
        String classStr = dataTokens.nextToken();
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
        Date expire = dbMember.getExpire();
        if (!expire.futureDateCheck()) {
            System.out.println(fname + " " + lname + " " + dob.toString() + " membership expired.");
            return;
        }

        FitnessClass choiceClass = null;
        for (FitnessClass aClass : classes) { //finds the chosen class and checks if already a member
            if (aClass.getClassName().equalsIgnoreCase(classStr)) {
                if (aClass.getMember(dbMember) != null) {
                    System.out.println(fname + " " + lname + " has already checked in " + aClass.getClassName() + ".");
                    return;
                } else {
                    choiceClass = aClass;
                    break;
                }
            }
        }
        if (choiceClass == null) { //checks if class exists
            System.out.println(classStr + " class does not exist.");
            return;
        }
        for (FitnessClass aClass : classes) { //checks for time conflict
            if (aClass != choiceClass) {
                if (aClass.getMember(dbMember) != null && aClass.getTime().equals(choiceClass.getTime())) {
                    System.out.println(choiceClass.getClassName() + " time conflict -- " +
                            fname + " " + lname + " has already checked in " + aClass.getClassName() + ".");
                    return;
                }
            }
        }
        choiceClass.add(dbMember); //having passed all the above checks, adds the member to the chosen class
        System.out.println(fname + " " + lname + " checked in " + choiceClass.getClassName() + ".");
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
        String classStr = dataTokens.nextToken();

        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken()); //check if dob is valid
        if(!dobCheck(dob))
            return;

        Member classMember = new Member(fname, lname, dob); //use given info to search for member in class

        for (FitnessClass aClass : classes) { //finds the chosen class and checks if already a member
            if (aClass.getClassName().equalsIgnoreCase(classStr)) {
                if (!aClass.remove(classMember)) {
                    System.out.println(fname + " " + lname + " is not a participant in " + classStr + ".");
                } else {
                    System.out.println(fname + " " + lname + " dropped " + classStr + ".");
                }
                return;
            }
        }
        //if we get here, then the chosen class did not exist.
        System.out.println(classStr + " class does not exist.");
    }
}
