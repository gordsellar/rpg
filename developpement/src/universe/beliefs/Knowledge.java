package universe.beliefs;

/**
 * 
 * @author sguingoin
 * 
 */
public class Knowledge {

    private long age;

    /**
     * Default constructor
     */
    public Knowledge() {
        resetAge();
    }

    /**
     * @return the age
     */
    public long getAge() {
        return age;
    }

    /**
     * Reset the age of the Knowledge as if it was new
     */
    public void resetAge() {
        this.age = System.currentTimeMillis();
    }
}
