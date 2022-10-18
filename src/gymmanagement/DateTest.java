package gymmanagement;

import static org.junit.Assert.*;
import org.junit.Test;

public class DateTest {

    @Test
    public void isValid() {
        //28 days in Feb in a non-leap year
        Date date = new Date("2/29/2011");
        assertFalse(date.isValid());

        //before 1900
        Date date1 = new Date("9/2/1899");
       assertFalse(date1.isValid());

       //29 days in Feb in leap year
        Date date2 = new Date("2/29/2000");
        assertTrue(date2.isValid());

        //month value out of bounds
        Date date3 = new Date("13/31/2003");
        assertFalse(date3.isValid());

        //day value out of bounds
        Date date4 = new Date("3/32/2003");
        assertFalse(date4.isValid());

        //month value out of bounds negative
        Date date5 = new Date("-1/31/2003");
        assertFalse(date5.isValid());


        //zeroes for all time values
        Date date6 = new Date("0/0/0");
        assertFalse(date6.isValid());

        //a nondate given, given a string
        Date date7 = new Date("random test text");
        assertFalse(date7.isValid());

        //empty string given
        Date date8 = new Date("");
        assertFalse(date8.isValid());

        //extra value given
        Date date9 = new Date("1/20/2022/1");
        assertFalse(date9.isValid());

        //doubles given
        Date date10 = new Date("1.0/3.0/2003.0");
        assertFalse(date10.isValid());

        //day value out of bounds negative
        Date date11 = new Date("1/-1/2022");
        assertFalse(date11.isValid());

        //year value out of bounds negative
        Date date12 = new Date("1/1/-1");
        assertFalse(date12.isValid());


    }

    @Test
    public void over18() {
        //date more than 18 years old
        Date date = new Date("6/2/2000");
        assertTrue(date.over18());

        //exactly 18 years ago, so not over18
        Date date1 = new Date("10/17/2004");
        assertFalse(date1.over18());

        //exactly 18 years ago + 1 day
        Date date2 = new Date("10/18/2004");
        assertFalse(date2.over18());
    }
}