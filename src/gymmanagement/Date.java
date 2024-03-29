package gymmanagement;
import java.util.Calendar;

/**
 * enum class for date
 * used as data field for Member parent class
 * @author Maxim Yacun
 * @author Niklas Bloom
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;


    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;



    /**
     * This constructor takes a String which should be a date, and converts it to a Date
     * object
     * @param date which is a date in String format and it is converted to a Date object
     */
    public Date(String date) { //take "mm/dd/yyyy" and create a Date object
        if (!date.contains("/")) {//if it does not contain /, then set to zero
            this.month = 0;
            this.day = 0;
            this.year = 0;
            return;
        }
        String[] tokens = date.split("/");
        if (tokens.length > 3) {
            this.month = 0;
            this.day = 0;
            this.year = 0;
            return;
        }
        String month = tokens[0];
        String day = tokens[1];
        String year = tokens[2];
        int intYear;
        int intDay;
        int intMonth;
        try {
            intMonth = Integer.parseInt(month, 10);
            intDay = Integer.parseInt(day, 10);
            intYear = Integer.parseInt(year, 10);
        } catch (NumberFormatException e) {
            intMonth = 0;
            intDay = 0;
            intYear = 0;
        }
        this.month = intMonth;
        this.day = intDay;
        this.year = intYear;
    }


    /**
    Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
    Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
    Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
    Step 4. The year is a leap year.
    Step 5. The year is not a leap year.
    February has 28 days in a non-leap year, and 29 days in a leap year.
     @Param takes a date in String form and only the year is extracted
     @returns true if the year is a leapyear, false otherwise
     */
    public boolean leapYearCheck(String date){
        int year = getDateYear(date); //used a static getter method
        if(year % QUADRENNIAL == 0){
            if(year % CENTENNIAL == 0){
                return year % QUARTERCENTENNIAL == 0;
            }
        }
        return false;
    }

    /**
     * This method takes a date in string format and only returns the year
     * @param date, the date we want to retrive the year from
     * @return the year in integer form
     */
    public static int getDateYear(String date){
        String[] tokens=date.split("/");
        String year = tokens[2];
        return Integer.parseInt(year);
    }


    /**
     * getter method for the Day if given a date in String format
     * @param date is a date in String format
     * @returns an int which is the day of the given date String
     */
    public static int getDateDay(String date){
        String[] tokens=date.split("/");
        String year = tokens[1];
        return Integer.parseInt(year);
    }

    /**
     *
     * @param obj is an object which should be an instance of the Date class
     * @returns true if the two dates equal eachother, or their days, month, and year values
     * line up, returns false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof Date) {
            Date dat = (Date) obj; //casting
            return dat.day == this.day && dat.month == this.month && dat.year == this.year;
        }
        return false;
    }

    /**
    compareTo() method is used when sorting by names
    if s1 > s2, it returns 1
    if s1 < s2, it returns -1
    if s1 == s2, it returns 0
     @param date is the date we want to compare to the date that this method is called on
     @returns an int which corresponds to a value depending on the compareTo outcome
   */
    @Override
    public int compareTo(Date date) {
        String[] tokens=date.toString().split("/");
        String month = tokens[0];
        String day = tokens[1];
        String year = tokens[2];
        int intMonth= Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        int intYear= Integer.parseInt(year);
        if(this.year > intYear){
            return 1;}
        if(this.year < intYear){
            return -1;}
        if(this.year == intYear){
            if(this.month > intMonth){
                return 1;}
        }
        if(this.year == intYear){
            if(this.month < intMonth){
                return -1;}
        }
        if(this.year == intYear){
            if(this.month == intMonth){
                if(this.day > intDay){
                    return 1;}
            }
        }
        if(this.year == intYear){
            if(this.month == intMonth){
                if(this.day < intDay){
                    return -1;}
            }
        }
        return -1;
    }

    /**
    Returns the string for a date object and Output should look like "6/30/2023"
     @returns the date in string format
     */
    @Override
    public String toString() {
        String month=Integer.toString(this.month);
        String day=Integer.toString(this.day);
        String year=Integer.toString(this.year);
        String date = month + "/" + day + "/" + year;
        return date;
    }

    /**
    Must be in  "MM/DD/YYYY" format
    expiry not less than current day
    DOB cannot be greater than current day
    day must not be greater than month value,
    month value must be in 1-12 range,
    day value in correct range according to month and leap year,
    year must be >= 1900,
    @returns true if the date is valid and meets the set conditions, false otherwise
     */
    public boolean isValid() { //called on date obj
        boolean LeapYear = leapYearCheck(this.toString());
        int month= this.month;
        if(this.year < 1900){
            return false;
        }
        int numberDaysInMonth = correspondingDaysInMonth(month,leapYearCheck(this.toString()));
        if(numberDaysInMonth == -1){
            return false; //ensures month number is 1 < x < 12
        }
        //how many days in month?
        int day=this.day;
        //does day value fit in month range
        if(day < 1 || day > numberDaysInMonth){ //ensures # days is 1 < x < numberDaysInMonth
            return false;
        }
        return true;
    }

    /**
     * checks if the date value is over 18 years ago, used for DOB's
     * @return true is the date is over 18, false otherwise
     */
    public boolean over18() { //check if member is over 18
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18);
        int CalendarYear = c.get(Calendar.YEAR);
        int CalendarDay = c.get(Calendar.DATE);
        int CalendarMonth = c.get(Calendar.MONTH);
        System.out.println("year: "+ year + ", month: "+ month + ", day: " + day);
        if (this.year == CalendarYear){
            if (this.month == CalendarMonth){
                return this.day <= CalendarDay;
            } else return this.month < CalendarMonth;
        } else return this.year < CalendarYear;
    }

    /**
    checks if the date is greater than the current date, which does not make sense for DOB/join date/other
    also Expire dates must be greater than the current date I assume
    returns true is this.date > current date
     @returns a boolean if this instances date values are greater than todays calendar date
     */
    public boolean futureDateCheck() {
        Calendar c = Calendar.getInstance();
        int CalendarYear = c.get(Calendar.YEAR);
        int CalendarDay = c.get(Calendar.DATE);
        int CalendarMonth = c.get(Calendar.MONTH); //get the current date
        if (this.year == CalendarYear){
            if(this.month == CalendarMonth){
                return this.day > CalendarDay;
            } else return this.month > CalendarMonth;
        } else return this.year > CalendarYear;
    }

    /**
     *This method takes a month value and a boolean if the year is a leapyear, and returns
     * the number of days in the month given
     * @param month is the value of the month and should be a value between 1-12
     * @param Leapyear is a boolean, true if it is a leapyear, false otherwise
     * @returns an int which is 1-31 and
     */
    public int correspondingDaysInMonth(int month, boolean Leapyear){
        switch(month){
            case 1:
                return MonthValue.January;
            case 2:// february
                if(Leapyear == true){
                    return MonthValue.FebruaryLeapyear;
                }
                else{
                    return MonthValue.FebruaryNonLeapyear;
                }
            case 3:
                return MonthValue.March;
            case 4:
                return MonthValue.April;
            case 5:
                return MonthValue.May;
            case 6:
                return MonthValue.June;
            case 7:
                return MonthValue.July;
            case 8:
                return MonthValue.August;
            case 9:
                return MonthValue.September;
            case 10:
                return MonthValue.October;
            case 11:
                return MonthValue.November;
            case 12:
                return MonthValue.December;
            default:
                return -1;
        }
    }


}
