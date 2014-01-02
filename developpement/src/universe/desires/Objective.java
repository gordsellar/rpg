package universe.desires;

/**
 * @author claire
 * 
 */

public class Objective {

    private String description;

    public Objective(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Objective [description=" + description + "]";
    }
}
