package universe.beliefs;

import universe.entities.Entity;

/**
 * Class representing an information concerning an Entity. Facts are considered
 * equals when they concern the same Entity
 * 
 * @author sguingoin
 * 
 */
public class Fact extends Knowledge {

    private String information;

    /**
     * @param entityConcerned
     *            the entity concerned by the information
     * @param information
     *            the information of the Entity
     */
    public Fact(Entity entityConcerned, String information) {
        this.setEntityConcerned(entityConcerned);
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
        return information;
    }
}
