package universe.beliefs;

import universe.Position;
import universe.entities.Entity;

/**
 * 
 * @author sguingoin
 * 
 */
public class Location extends Knowledge {

    private Position position;

    /**
     * @param entity
     *            the Entity
     * @param position
     *            the Position of the Entity
     */
    public Location(Entity entity, Position position) {
        this.setEntityConcerned(entity);
        this.setPosition(position);
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position
     *            the Position to set
     */
    public void setPosition(Position position) {
        resetAge();
        this.position = position;
    }

    @Override
    public String toString() {
        String result = entityConcerned.getName() + " is located at "
                + position;
        return result;
    }
}
