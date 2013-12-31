package universe.entities;

import java.util.ArrayList;
import java.util.Random;

import universe.Position;
import universe.beliefs.Knowledge;
import universe.desires.Objective;

/**
 * @author pierre
 * 
 */

public class Character extends Entity {

    private int smartness;
    private Objective goal;
    private Position position;
    private int actionMaxLength;
    private CharacterState state;
    private Character killer = null;

    public Character(String name, int smartness) {
	super(name);
	this.smartness = smartness;
	this.state = CharacterState.Ok;
	// TODO More knowledge if the character is smarter ? (With learnFromZone
	// with a radius)
    }

    /**
     * @return The current position of the Character
     */
    public Position getPosition() {
	return position;
    }

    private Boolean assertCanInteractWith(Entity e)
	    throws TooFarToInteractException {
	return true;
	// TODO Handle the distance of two entities
	/*
	 * if (this.position.getDistance(e.getPosition())) { return true; } else
	 * { throw new TooFarToInteractException(this, e); }
	 */
    }

    public Objective getGoal() {
	return goal;
    }

    public void setGoal(Objective goal) {
	this.goal = goal;
    }

    public void changeMood(CharacterState cs) {
	this.state = cs;
    }

    /**
     * @param cara
     *            A strictly positive int
     * @return The D&D modifier for this characteristic (-5 / infinite)
     */
    private int getCharacteristicModifier(int cara) {
	return (cara - 10) / 2;
    }

    /**
     * @return A random Knowledge of this character
     */
    public Knowledge discuss() {
	int n = this.knowledges.size();
	if (n == 0)
	    // TODO We return the character State, it's the only things this one
	    // knows, and can talk about.
	    return new Knowledge();
	else
	    return this.knowledges.get(new Random().nextInt(n));
    }

    /**
     * @param c
     *            The character from which you'll learn
     * @throws TooFarToInteractException
     */
    public void discussWith(Character c) throws TooFarToInteractException {
	this.assertCanInteractWith(c);
	Knowledge k;
	k = c.discuss();
	this.addKnowledge(k);
    }

    /**
     * @param p
     *            The position where the character want to go
     * @return A Boolean with the value True if the move is possible
     */
    public Boolean move(Position p) {
	this.position = p;
	// TODO Make sure the character can go there
	return true;

    }

    public Entity[] searchEntityInZone(ArrayList<Entity> e) {
	// TODO handle distance for an action
	// TODO Handle this in world ?
	return null;
    }

    /**
     * @param c
     *            The character whom you giving the object
     * @param i
     *            The object given
     * @throws TooFarToInteractException
     */
    public void giveObject(Character c, Item i)
	    throws TooFarToInteractException {
	this.assertCanInteractWith(c);
	this.removeItem(i);
	c.addItem(i);
    }

    public Boolean askObject(Character c, Item o) {
	// TODO A character may not accept to give object everytime
	return true;
    }

    public void kill(Character c) {
	c.killer = this;
	c.state = CharacterState.Dead;
    }

    /**
     * @param c
     *            The character to loot
     * @throws TooFarToInteractException
     */
    public void loot(Character c) throws TooFarToInteractException {
	this.assertCanInteractWith(c);
	// TODO Permit to select only one item ?
	ArrayList<Item> lootedItems = c.getInventory();
	for (Item i : lootedItems) {
	    this.addItem(i);
	}
	c.setInventory(new ArrayList<Item>());
    }

    /**
     * @param i
     *            Item to read
     * @throws TooFarToInteractException
     */
    public void readObject(Item i) throws TooFarToInteractException {
	this.assertCanInteractWith(i);
	this.knowledges.addAll(i.getKnowledges());
    }

    /**
     * @param k
     *            An array list of the knowledge you can find in a zone
     * @return The index of the knowledges actually learned in the array list
     */
    public int[] learnFromZone(ArrayList<Knowledge> k) {
	int[] learnedKnowledges;
	int numberOfKnowledge = this.getCharacteristicModifier(this.smartness);
	learnedKnowledges = new int[numberOfKnowledge];
	for (int i = 0; i < numberOfKnowledge; i++) {
	    // TODO Make this random so a dumb as a small chance of learning
	    // something trying a lot ?
	    this.addKnowledge(k.get(i));
	}
	return learnedKnowledges;
    }

    @Override
    public String toString() {
	String coffee = super.toString();
	if (knowledges.size() != 0)
	    coffee += ", knowledges=" + knowledges;
	coffee += " smartness=" + smartness;
	return coffee;
    }
}
