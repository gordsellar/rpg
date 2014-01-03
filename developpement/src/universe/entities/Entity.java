package universe.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import universe.Position;
import universe.World;
import universe.beliefs.Fact;
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
	String unifiedName;
	Boolean nameIsUnique = false;
	int count = 1;

	this.world = w;

	while (!nameIsUnique) {
	    try {
		if (count == 1)
		    unifiedName = name;
		else
		    unifiedName = name + " " + count;
		this.setName(unifiedName);
		nameIsUnique = true;
		// System.out.println("The name of the entity is " +
		// unifiedName);
	    } catch (DuplicateEntityNameException e) {
		count++;
		e.printStackTrace();
	    }
	}

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

    public void setName(String name) throws DuplicateEntityNameException {
	if (this.world.getEntityByName(name) != null) {
	    throw new DuplicateEntityNameException(name);
	}
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

    public Boolean got(Item i) {
	return this.getInventory().contains(i);
    }

    public Boolean gotAll(Collection<Item> c) {
	return this.getInventory().containsAll(c);
    }

    public void addItem(Item i) {
	this.world.removeEntity(i);
	i.setPosition(this.getPosition());
	this.inventory.add(i);
    }

    public void removeItem(Item i) {
	this.inventory.remove(i);
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

    public boolean knows(Knowledge k) {
	return this.getKnowledges().contains(k);
    }

    public boolean knowsAll(Collection<Knowledge> c) {
	return this.getKnowledges().containsAll(c);
    }

    public ArrayList<Knowledge> getKnowledgeAbout(Entity e) {
	ArrayList<Knowledge> knowledgesAboutAnEntity;
	knowledgesAboutAnEntity = new ArrayList<Knowledge>();
	if (this == e)
	    // TODO Make this smarter
	    knowledgesAboutAnEntity.add(new Fact("I'm " + e));
	for (Knowledge k : this.getKnowledges()) {
	    if (k instanceof Possession) {
		Possession p = ((Possession) k);
		if (p.getPossessedBy() == e || p.getPossession() == e)
		    knowledgesAboutAnEntity.add(k);
	    } else if (k instanceof Location && ((Location) k).getEntity() == e) {
		knowledgesAboutAnEntity.add(k);
	    }
	}
	return knowledgesAboutAnEntity;
    }

    public boolean knowsAbout(Entity e) {
	return getKnowledgeAbout(e).size() != 0;
    }

    protected ArrayList<Knowledge> getAutomaticKnowledges() {
	ArrayList<Knowledge> automaticKnowledges = new ArrayList<Knowledge>();
	automaticKnowledges.add(new Location(this, position));
	for (Item i : this.inventory) {
	    automaticKnowledges.addAll(i.getKnowledges());
	    automaticKnowledges.add(new Possession(this, i));
	}
	// System.out.println("Automatic knowledges : " + automaticKnowledges);
	return automaticKnowledges;
    }

    public void addKnowledge(Knowledge k) {
	if (!this.getKnowledges().contains(k))
	    this.knowledges.add(k);
    }

    public void removeKnowledge(Knowledge k) {
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
}
