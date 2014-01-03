package universe.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import universe.Position;
import universe.World;
import universe.Zone;
import universe.beliefs.Fact;
import universe.beliefs.Knowledge;
import universe.desires.Objective;

/**
 * @author pierre
 * 
 */

public class Character extends Entity {

    private Characteristic characteristic;
    private Objective goal;
    private Character killer = null;

    public Character(World w, String name, Integer smartness) {
	super(w, name);
	this.characteristic = new Characteristic(smartness);
	this.learnFromZone(getUnderstandabilityZone());
    }

    /**
     * @return The zone in which a character can have knowledge or comprehension
     */
    private Zone getUnderstandabilityZone() {
	return new Zone(position,
		characteristic.getModifier(characteristic.smartness));
    }

    /**
     * @return The zone in which the character can interact
     */
    private Zone getActionZone() {
	return new Zone(position, characteristic.actionMaxLength);
    }

    /**
     * @param e
     *            The entity to interact with
     * @return True
     * @throws TooFarToInteractException
     */
    private Boolean assertCanInteractWith(Entity e)
	    throws TooFarToInteractException {
	if (this.world.getEntities(getActionZone()).contains(e))
	    return true;
	else {
	    throw new TooFarToInteractException(this, e);
	}
    }

    public Objective getGoal() {
	return goal;
    }

    public void setGoal(Objective goal) {
	this.goal = goal;
    }

    public void setState(CharacterState cs) {
	this.characteristic.state = cs;
    }

    @Override
    public ArrayList<Knowledge> getAutomaticKnowledges() {
	ArrayList<Knowledge> result = new ArrayList<Knowledge>();
	// We return the character State, it may be the only things this
	// character knows, and can talk about.
	result.add(new Fact(this.name + " is feeling "
		+ this.characteristic.state));
	return super.getAutomaticKnowledges();
    }

    /**
     * @return A random Knowledge of this character
     */
    public Knowledge discuss() {
	int n = this.getKnowledges().size();
	return this.getKnowledges().get(new Random().nextInt(n));
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
	try {
	    this.addKnowledge(k);
	} catch (Exception e) {
	    e.printStackTrace();
	}
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

    public List<Entity> searchEntityInZone(Zone z, Entity e)
	    throws TooFarToInteractException {
	this.assertCanInteractWith(e);
	int i = 0 + this.characteristic.getModifier(characteristic.smartness);
	// TODO Make this random so a dumb as a small chance of learning
	// something trying a lot ?
	return this.world.getEntities(z).subList(0, i);
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
	// TODO A character may accept depending on his feeling for the other
	// character
	if (new Random().nextInt(100) <= 50)
	    return true;
	else
	    return false;
    }

    public void kill(Character c) {
	c.killer = this;
	c.setState(CharacterState.Dead);
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
     * A character learn what can be learn in a Zone according to its smartness.
     * 
     * @param zone
     *            The zone in which you search for Knowledge
     * @return The index of the knowledges actually learned in the array list
     */
    public int[] learnFromZone(Zone zone) {
	ArrayList<Knowledge> knowledges;
	Knowledge k = null;
	int numberLearnable, numberAvailable, numberToLearn;
	int[] learnedKnowledges;
	knowledges = world.getKnowledges(zone);
	numberLearnable = characteristic.getModifier(characteristic.smartness);
	numberAvailable = knowledges.size();
	// System.out.println(numberToLearn);
	numberToLearn = Math.min(numberLearnable, numberAvailable);
	if (numberToLearn > 0) {
	    learnedKnowledges = new int[numberToLearn];
	    for (int numberLearned = 0, i = 0; numberLearned < numberToLearn; numberLearned++) {
		i = new Random().nextInt(numberAvailable);
		learnedKnowledges[numberLearned] = i;
		k = world.getKnowledges(zone).get(i);
		// System.out.println(this.name + " learned that " + k);
		this.addKnowledge(k);
	    }
	} else {
	    learnedKnowledges = null;
	}
	return learnedKnowledges;
    }

    @Override
    public String toString() {
	String result = super.toString();
	result += ", knowledges=" + this.getKnowledges();
	result += " smartness=" + characteristic.smartness;
	return result;
    }
}
