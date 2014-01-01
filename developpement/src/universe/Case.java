package universe;

import java.util.ArrayList;
import java.util.Random;

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
	int random = new Random().nextInt(100);
	if (random < 60)
	    return LandType.Grass;
	else if (random < 90)
	    return LandType.Tree;
	else if (random < 99)
	    return LandType.Dirt;
	else
	    return LandType.Cliff;
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
	String result = this.landtype.toString();
	if (this.entities.size() != 0) {
	    result += " [";
	    for (Entity e : this.entities) {
		result += e.toString() + ",";
	    }
	    result += "]";
	}
	return result;
    }
}