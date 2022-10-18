package gymmanagement;

/**
 * just an array of classes,
 * Each FitnessClass instance is also an arraylist which has the fitnessClasss in the FitnessClass
 * has print methods and can use I/O in this class
 * needs to I/O the class schedule Input the file for class schedule,
 * go through line by line
 * Initialize fitness classes with the time, intstructor, and location
 * add FitnessClass to the fitnessClass Array
 * @author Maxim Yacun
 * @author Niklas Bloom
 */
public class ClassSchedule {
    private FitnessClass [] classes;
    private int numClasses;

    public static final int NOT_FOUND = -1;

    /**
     * no argument constructor
     */
    public ClassSchedule() {
        this.numClasses=0;
        this.classes = new FitnessClass[4];

    }


    /**
     *  Returns a reference to a FitnessClass in classes that matches the given FitnessClass
     *  The given fitnessClass only requires an fname, lname, and DOB, the other params
     *  do not matter for this method.
     * @param fitnessClass - the fitness class we want the reference for
     * @return the reference for this fitnessClass
     */
    public FitnessClass getFitnessClass(FitnessClass fitnessClass) {
        if(this.isEmpty()){
            return null;
        }
        if(fitnessClass == null ||fitnessClass.getClassName() == null
        || fitnessClass.getLocation() == null ||fitnessClass.getInstructor() == null){
            return null;
        }

        for (FitnessClass aClass : this.classes) {
            if (aClass != null) {
                if (aClass.equals(fitnessClass)) {
                    return aClass;
                }
            }
        }
        return null;
    }

    /**
     * given a fitnessClass, if any in current classes has a time conflict with param,
     * then add this class to the conflict array
     * @param fitnessClass - the fitness class we want to test to see if it has time conflicts
     * @return the array of fitness classes which have a time conflict with the given class
     */
    public FitnessClass[] conflicts(FitnessClass fitnessClass){
        if(this.isEmpty()){
            return null;
        }
        if(fitnessClass == null){
            return null;
        }
        FitnessClass[] conflicts = new FitnessClass[this.classes.length];
        for (int i = 0; i < this.classes.length; i++) {
            if (this.classes[i] != null) {
                if (this.classes[i].getTime().equals(fitnessClass.getTime())) {
                    conflicts[i] = this.classes[i];
                }
            }
        }
        return conflicts;
    }

    /**
     print the array contents as is
     */
    public void print () {
        if(isEmpty()) {
            System.out.println("Fitness class schedule is empty.");
            return;
        }
        System.out.println("\n-Fitness classes-");
        for (FitnessClass aClass : this.classes) {
            if (aClass != null) {
                aClass.print();
                if(!aClass.isEmpty()){
                    aClass.printWholeFitnessClass();
                }
                if(!aClass.isEmptyguest()){
                    aClass.printWholeFitnessClassGuests();
                }

            }
        }
        System.out.println("-end of class list\n");
    }


    /**
     * grows the array by 4
     */
    private void grow(){
        FitnessClass[] arr = new FitnessClass[this.classes.length+4];
        System.arraycopy(this.classes, 0, arr, 0, this.classes.length);
        this.classes = arr;
    }


    /**
     *  adds a fitnessClass to the array
     * @param fitnessClass - given fitness class we wanna add to our array
     */
    public void add(FitnessClass fitnessClass){
        if(!this.checkCapacity()){ //check capacity if have to increase numClasses by 4
            this.grow();
        }
        //get index of first null element in array
        int firstNull = -1;
        for (int i = 0; i < this.classes.length; i++) { //should numClasses be numClasses of array or # of fitnessClasss?
            if (this.classes[i] == null) {
                firstNull = i;
                break;
            }
        }
        classes[firstNull] = fitnessClass;
        //firstnull should never be -1, since we grew array if empty, a first null should always exist
        numClasses++;
    }

    /**
     * @return true if the numclasses is correct and equals the true classes array length
     */
    public boolean checkCapacity(){
        return this.numClasses != this.classes.length;
    }

    /**
     * @return true if the num classes in the array is 0, false otherwise
     */
    public boolean isEmpty() {
        return this.numClasses == 0;
    }
}
