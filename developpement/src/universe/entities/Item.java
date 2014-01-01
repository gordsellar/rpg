package universe.entities;

import universe.Position;
import universe.World;
import universe.beliefs.Location;

/**
 * @author pierre
 * 
 */

public class Item extends Entity {

    private Boolean carriable;
    private int value; // Value in copper

    public Item(World w, String name, int copperValue, Boolean carriable) {
	super(w, name);
	this.carriable = carriable;
	this.value = copperValue;
	// TODO Set the position
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Boolean isCarriable() {
        return carriable;
    }

    public void setCarriable(Boolean carriable) {
	this.carriable = carriable;
    }
}