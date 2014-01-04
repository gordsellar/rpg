package universe.beliefs;

import universe.entities.Entity;

/**
 * 
 * @author sguingoin
 * 
 */
public abstract class Knowledge {

    protected long age;
    protected Entity entityConcerned;

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

    /**
     * @return the entityConcerned
     */
    public Entity getEntityConcerned() {
        return entityConcerned;
    }

    /**
     * @param entityConcerned
     *            the entityConcerned to set
     */
    public void setEntityConcerned(Entity entityConcerned) {
        this.entityConcerned = entityConcerned;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Knowledge && this.getClass().equals(o.getClass())) {
            if (this.entityConcerned.equals(((Knowledge) o)
                    .getEntityConcerned())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return 11 * hash + entityConcerned.hashCode();
    }
}
