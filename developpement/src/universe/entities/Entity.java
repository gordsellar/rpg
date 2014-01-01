package universe.entities;

import java.util.ArrayList;
import java.util.Random;

import sun.util.logging.resources.logging;
import universe.Position;
import universe.World;
import universe.beliefs.Knowledge;
import universe.beliefs.Location;
import universe.beliefs.Possession;

/**
 * @author pierre
 * 
 */

public class Entity {

    private static int numberOfEntity = -1;

    protected World world;
    protected Position position;
    protected String name;
    protected ArrayList<Item> inventory;
    protected ArrayList<Knowledge> knowledges;
    protected int id;

    public Entity(World w, String name) {
	this.world = w;
	this.setName(name);
	
	// TODO Make the position system cleaner ?
	int x, y;
	x = new Random().nextInt(w.x);
	y = new Random().nextInt(w.y);
	this.position = new Position(x, y);
	w.addEntity(this, this.position);

	Entity.numberOfEntity++;
	this.setId(Entity.numberOfEntity);
	this.setInventory(new ArrayList<Item>());
	this.setKnowledges(new ArrayList<Knowledge>());
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setPosition(Position position) {
	// Changing the position of the entity
	this.position = position;
	// Changing the position of all the contained entity (recursively)
	for (Entity e : this.inventory) {
	    e.setPosition(position);
	}
    }

    public Position getPosition() {
	return position;
    }

    public ArrayList<Item> getInventory() {
	return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
	this.inventory = inventory;
    }

    public void addItem(Item i) {
	this.world.removeEntity(i);
	this.inventory.add(i);
    }

    public void removeItem(Item i) {
	this.inventory.remove(i);
    }

    public ArrayList<Knowledge> getAutomaticKnowledges() {
	ArrayList<Knowledge> automaticKnowledges = new ArrayList<Knowledge>();
	automaticKnowledges.add(new Location(this, position));
	for (Item i : this.inventory) {
	    automaticKnowledges.add(new Possession(this, i));
	    automaticKnowledges.add(new Location(i, position));
	}
	// System.out.println("Automatic knoledges : " + automaticKnowledges);
	return automaticKnowledges;
    }

    public ArrayList<Knowledge> getKnowledges() {
	ArrayList<Knowledge> knowledgesResult = new ArrayList<Knowledge>();
	knowledgesResult.addAll(this.knowledges);
	knowledgesResult.addAll(getAutomaticKnowledges());
	return knowledgesResult;

    }

    public void setKnowledges(ArrayList<Knowledge> knowledges) {
	this.knowledges = knowledges;
    }

    public void addKnowledge(Knowledge k) {
	ArrayList<Knowledge> alreadyKnown;
	alreadyKnown = this.getKnowledges();
	if (!alreadyKnown.contains(k)) {
	    // System.out.println("Adding belief that '" + k + "' in " + this.name);
	    this.knowledges.add(k);
	}
	else{
	    // System.out.println("Already known : " + k); 
	}

    }

    public void removeKnowledge(Knowledge k) throws Exception {
	this.knowledges.remove(k);
    }

    @Override
    public String toString() {
	String coffee = name + " (id=" + id + ")";
	if (inventory.size() != 0)
	    coffee += ", inventory=" + inventory;
	// We don't print the knowledge of all object
	// Just the character one
	return coffee;
    }

    public static void main(String[] args) {
	World w = new World(2, 2);
	Entity e = new Entity(w, "Robert");
	System.out.println(e.id);
	Entity f = new Entity(w, "Roger");
	System.out.println(f.id);
	Entity g = new Entity(w, "Robert");
	System.out.println(g.id);
    }
}
