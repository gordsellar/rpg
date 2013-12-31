package universe.entities;

import java.util.ArrayList;

import universe.beliefs.Knowledge;

/**
 * @author pierre
 * 
 */

public class Entity {

    protected String name;
    protected ArrayList<Item> inventory;
    protected ArrayList<Knowledge> knowledges;
    private static int numberOfEntity = -1;
    protected int id;

    public Entity(String name) {
	this.name = name;
	Entity.numberOfEntity++;
	this.id = Entity.numberOfEntity;
	this.inventory = new ArrayList<Item>();
	this.knowledges = new ArrayList<Knowledge>();
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

    public ArrayList<Item> getInventory() {
	return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
	this.inventory = inventory;
    }

    public void addItem(Item i) {
	this.inventory.add(i);
    }

    public void removeItem(Item i) {
	this.inventory.remove(i);
    }

    public ArrayList<Knowledge> getKnowledges() {
	return knowledges;
    }

    public void setKnowledges(ArrayList<Knowledge> knowledges) {
	this.knowledges = knowledges;
    }

    protected void addKnowledge(Knowledge k) {
	this.knowledges.add(k);
    }

    protected void removeKnowledge(Knowledge k) {
	this.knowledges.remove(k);
    }

    @Override
    public String toString() {
	String coffee = name + "(id=" + id + ")";
	if (inventory.size() != 0)
	    coffee += ", inventory=" + inventory;
	// We don't print the knowledge of all object
	// Just the character one
	return coffee;
    }

    public static void main(String[] args) {
	Entity e = new Entity("Robert");
	System.out.println(e.id);
	Entity f = new Entity("Roger");
	System.out.println(f.id);
	Entity g = new Entity("Robert");
	System.out.println(g.id);
    }
}
