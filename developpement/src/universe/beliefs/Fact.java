package universe.beliefs;

/**
 * 
 * @author sguingoin
 * 
 */
public class Fact extends Knowledge {

    private String information;

    /**
     * @param information
     *            the information of the Knowledge
     */
    public Fact(String information) {
        this.setInformation(information);
    }

    /**
     * @return the information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information the information to set
     */
    public void setInformation(String information) {
        resetAge();
        this.information = information;
    }

    @Override
    public String toString() {
        return "Fact [age=" + getAge() + ", information=" + information + "]";
    }
}
