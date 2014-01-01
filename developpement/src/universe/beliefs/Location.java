package universe.beliefs;

import universe.Position;
import universe.entities.Entity;

/**
 * 
 * @author sguingoin
 * 
 */
public class Location extends Knowledge {

    private Entity entity;
    private Position position;

    /**
     * @param entity
     *            the Entity
     * @param position
     *            the Position of the Entity
     */
    public Location(Entity entity, Position position) {
	this.setEntity(entity);
	this.setPosition(position);
    }

    /**
     * @return the Entity
     */
    public Entity getEntity() {
	return entity;
    }

    /**
     * @param entity
     *            the Entity to set
     */
    public void setEntity(Entity entity) {
	resetAge();
	this.entity = entity;
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
	String result = entity.getName() + " is located at " + position;
	return result;
    }
}
