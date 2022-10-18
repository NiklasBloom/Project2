package gymmanagement;

import static org.junit.Assert.*;
import org.junit.Test;

public class FitnessClassTest {

    FitnessClass niksGymClass = new FitnessClass( "Cardio",  "Kim", "morning", "Edison");
    @Test
    public void add() {
        //adding new member to class with zero errors, should add and return true
        Member Nik = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean added = niksGymClass.add(Nik);
        assertTrue(added);

        //adding same member thats already in the list, should return false
        Member Nik1 = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean added1 = niksGymClass.add(Nik1);
        assertFalse(added1);

        //Member is null, should return false
        Member Nik2 = null;
        boolean added2 = niksGymClass.add(Nik2);
        assertFalse(added2);




    }
    @Test
    public void remove() {
        //adding new member to then remove from the fitnessClass to test remove method
        Member InitialNik = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean add = niksGymClass.add(InitialNik);
        assertTrue(add);

        //adding new member to class with zero errors, should add and return true
        Member Nik = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean removed = niksGymClass.remove(Nik);
        assertTrue(removed);

        //testing remove on a member we already removed, should return false
        boolean removed1 = niksGymClass.remove(Nik);
        assertFalse(removed1);

        //removing a null member, should return false
        Member Nik2 = null;
        boolean removed2 = niksGymClass.remove(Nik2);
        assertFalse(removed2);
    }


    @Test
    public void addGuest() {

        //testing adding a member which should have no errors
        Member Nik = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean added = niksGymClass.addGuest(Nik);
        assertTrue(added);

        // adding the same member, which should still be added to the ArrayList and return True
        Member Nik2 = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean added2 = niksGymClass.addGuest(Nik2);
        assertTrue(added2);

        // adding a null member which should return false
        Member Nik3 = null;
        boolean added3 = niksGymClass.addGuest(Nik3);
        assertFalse(added3);
    }

    @Test
    public void removeGuest() {
        //testing adding a guest which should have no errors
        Member Nik = new Member("Niklas", "Bloom", new Date("6/2/2000"));
        boolean added = niksGymClass.addGuest(Nik);
        assertTrue(added);

        //removing the guest we just added to the arraylist
        boolean dropped = niksGymClass.removeGuest(Nik);
        assertTrue(dropped);

        ///removing a guest which we already removed
        boolean dropped2 = niksGymClass.removeGuest(Nik);
        assertFalse(dropped2);

        //removing a guest which is null, should return false;
        Member Nik1 = null;
        boolean dropped3 = niksGymClass.removeGuest(Nik1);
        assertFalse(dropped3);

    }


}