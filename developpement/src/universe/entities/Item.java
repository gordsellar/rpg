package universe.entities;

// import java.util.ArrayList;


/**
 * @author pierre
 * 
 */

public class Item extends Entity {

    private Boolean carriable;
    // private Boolean container;
    private int value; // Value in copper

    public Item(String name, Integer copperValue, Boolean carriable) {
        //,}Boolean container) {
        super(name);
        this.carriable = carriable;
        // this.container = container;
        this.value = copperValue;
    }

    /*    public Boolean isContainer() {
	return container;
    }

    public void setContainer(Boolean container) {
	this.container = container;
    }

    @Override
    public void setInventory(ArrayList<Item> inventory) {
	// TODO Auto-generated method stub
	super.setInventory(inventory);
    }

    @Override
    public void addItem(Item i) {
	// TODO Auto-generated method stub
	super.addItem(i);
    }

    @Override
    public void removeItem(Item i) {
	// TODO Auto-generated method stub
	super.removeItem(i);
    }

     */

    public int getValue() {
        int globalValue = value;
        for (Item i : inventory) {
            globalValue += i.getValue();
        }
        return globalValue;
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