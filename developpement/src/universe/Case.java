package universe;

import java.util.ArrayList;

import universe.entities.*;

/**
 * @author pierre
 * 
 */

public class Case {

    private ArrayList<Entity> entities;
    private LandType landtype;

    public Case() {
	this.entities = new ArrayList<Entity>();
	this.landtype = this.getRandomLandType();
    }

    public Case(LandType landtype) {
	this.entities = new ArrayList<Entity>();
	this.landtype = landtype;
    }

    public Case(LandType landtype, ArrayList<Entity> entities) {
	this.landtype = landtype;
	this.entities = entities;
    }

    private LandType getRandomLandType() {
	// TODO return something else than Grass always
	return LandType.Grass;
    }

    public void addEntity(Entity entity) {
	this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
	this.entities.remove(entity);
    }

    public ArrayList<Entity> getEntities() {
	return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
	this.entities = entities;
    }

    @Override
    public String toString() {
	String coffee = this.landtype.toString();
	if (this.entities.size() != 0) {
	    coffee += " [";
	    for (Entity e : this.entities) {
		coffee += e.toString() + ",";
	    }
	    coffee += "]";
	}
	return coffee;
    }
}