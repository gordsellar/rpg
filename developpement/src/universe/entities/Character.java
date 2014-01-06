package universe.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import universe.Position;
import universe.Zone;
import universe.beliefs.Fact;
import universe.beliefs.Knowledge;
import universe.beliefs.Location;
import universe.desires.Objective;
import universe.utils.UI;
/**
 * @author pierre
 * 
 */

public class Character extends Entity {

    private Characteristic characteristic;
    private Objective goal;
    private Character killer = null;

    public Character(String name, Integer smartness) {
        super(name);
        this.characteristic = new Characteristic(smartness);
    }

    /**
     * @return The zone in which a character can have knowledge or comprehension
     */
    protected Zone getUnderstandabilityZone() {
        return new Zone(position,
                characteristic.getModifier(characteristic.smartness));
    }

    /**
     * @return The zone in which the character can interact
     */
    public Zone getActionZone() {
        return new Zone(position, characteristic.actionMaxLength);
    }
    
    public Boolean isAlive(){
	return this.killer == null;
    }

    /**
     * @param e
     *            The entity to interact with
     * @return True
     * @throws TooFarToInteractException
     */
    private Boolean assertCanInteractWith(Entity e)
            throws TooFarToInteractException {
        if (getActionZone().contain(e.getPosition())) {
            return true;
        } else {
            //throw new TooFarToInteractException(this, e);
            return false;
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
    public void updateAutomaticKnowledges() {
        super.updateAutomaticKnowledges();
        // We return the character State, it may be the only things this
        // character knows, and can talk about.
        knowledgesManager.addKnowledge(new Fact(this, this.name
                + " is feeling " + this.characteristic.state));
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
            this.knowledgesManager.addKnowledge(k);
        } catch (Exception e) {
            UI ui = new UI(this.world);
            ui.displayException(e.getMessage());
        }
    }

    /**
     * @param p
     *            The position where the character want to go
     * @return A Boolean with the value True if the move is possible
     */
    public Boolean move(Position p) {
        this.setPosition(p);
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

    public ArrayList<Knowledge> askInformationAboutAnEntity(Character c,
            Entity e) {
        ArrayList<Knowledge> result;
        if (c.knowsAbout(e)) {
            // TODO A character may accept depending on his feeling for the
            // other character or hide certain information
            result = c.getKnowledgeAbout(e);
        } else {
            result = new ArrayList<Knowledge>();
            result.add(new Fact(c, c.getName()
                    + " don't have any information about " + e));
        }
        return result;
    }

    public Boolean askObject(Character c, Item i) {
        // TODO A character may accept depending on his feeling for the other
        // character
        if (new Random().nextInt(100) <= 50) {
            return true;
        } else {
            return false;
        }
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
        this.knowledgesManager.addKnowledges(i.getKnowledges());
    }

    /**
     * Updates known Knowledges. Scanning the Zone in line of sight to remove
     * Knowledges of Location not accurate anymore
     */
    public void updateKnowledges() {
        updateAutomaticKnowledges();
        List<Knowledge> knowledges = knowledgesManager.getKnowledges();
        List<Knowledge> oldKnowledges = new ArrayList<>();
        for (Knowledge knowledge : knowledges) {
            if (knowledge instanceof Location) {
                Location l = (Location) knowledge;
                if (getUnderstandabilityZone().contain(l.getPosition())) {
                    if (!l.getEntityConcerned().getPosition()
                            .equals(l.getPosition())) {
                        oldKnowledges.add(knowledge);
                    }
                }
            }
        }
        knowledgesManager.removeKnowledges(oldKnowledges);
    }

    /**
     * A character learn what can be learn in a Zone according to its smartness.
     * 
     * @param zone
     *            The zone in which you search for Knowledge
     */
    public void learnFromZone(Zone zone) {
        ArrayList<Knowledge> knowledges;
        int numberLearnable, numberAvailable, numberToLearn;
        synchronized (world) {
            knowledges = world.getKnowledges(zone);
        }
        numberLearnable = characteristic.getModifier(characteristic.smartness);
        numberAvailable = knowledges.size();
        numberToLearn = Math.min(numberLearnable, numberAvailable);
        if (numberToLearn > 0) {
            for (int numberLearned = 0; numberLearned < numberToLearn
                    - numberLearnable; numberLearned++) {
                int i = new Random().nextInt(knowledges.size());
                knowledges.remove(i);
            }
            this.knowledgesManager.addKnowledges(knowledges);
        }
    }

    public void learnFromUnderstandabilityZone() {
        this.learnFromZone(this.getUnderstandabilityZone());
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += ", knowledges=" + this.getKnowledges();
        result += " smartness=" + characteristic.smartness;
        return result;
    }
}
