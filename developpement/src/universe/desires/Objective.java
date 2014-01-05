package universe.desires;

import universe.entities.Entity;

/**
 * @author claire
 * 
 */

public class Objective {

    private Verb desire;
    private int priority;
    private Entity entity;

    public Objective(Verb desire, Integer priority) {
	this(desire, priority, null);
    }

    public Objective(Verb desire, Integer priority, Entity entity) {
	this.desire = desire;
	this.setPriority(priority);
	this.setEntity(entity);
    }

    public Verb getDesire() {
	return desire;
    }

    public void setDesire(Verb desire) {
	this.desire = desire;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
	return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(int priority) {
	this.priority = priority;
    }

    /**
     * @return the entity
     */
    public Entity getEntity() {
	return entity;
    }

    /**
     * @param entity
     *            the entity to set
     */
    public void setEntity(Entity entity) {
	this.entity = entity;
    }

    @Override
    public String toString() {
	if (entity != null)
	    return "Objective [desire=" + desire + ", priority=" + priority
		    + ", entity=" + entity + "]";
	else
	    return "Objective [desire=" + desire + ", priority=" + priority;
    }
}
