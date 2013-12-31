package universe.entities;

import universe.beliefs.Knowledge;

/**
 * @author pierre
 * 
 */

public class Item extends Entity {

    private Boolean carriable;

    public Item(String name, Boolean carriable) {
	super(name);
	this.carriable = carriable;
	this.knowledges.add(new Knowledge());
	// This particular object is carriable (in position p)
    }

    public Boolean isCarriable() {
        return carriable;
    }

    public void setCarriable(Boolean carriable) {
	this.carriable = carriable;
    }
}