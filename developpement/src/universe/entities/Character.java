package universe.entities;

import universe.Position;
import universe.Zone;
import universe.beliefs.Knowledge;
import universe.desires.Objective;

/**
 * @author pierre
 * 
 */

public class Character extends Entity {

    private int smartness;
    private Knowledge[] knowledgeOfTheWorld;
    private Item[] inventory;
    private Objective goal;
    private Position position;
    private int actionMaxLength;

    public Character(String name, Integer id, Integer smartness) {
        super(name, id);
        this.smartness = smartness;
    }

    public void discuss(Character c) {
        // TODO acquire a knowledge from discussing here.
    }

    public Boolean move(Position p) {
        this.position = p;
        // TODO Make sure the character can go there
        return true;

    }

    public Entity[] searchEntityInZone(Zone z) {
        // TODO Search
        // TODO Put this in world ?
        return null;
    }

    public void giveObject(Character c, Item o) {
        // TODO
    }

    public void lootObject(Item o) {
        // TODO
    }

    public void askObject(Character c, Item o) {
        // TODO
    }

    public void kill(Character c) {
        // TODO
    }

    public void loot(Character c) {
        // TODO
    }

    public void readObject(Item o) {
        // TODO
    }

    public void learnFromZone(Zone z) {
        // TODO
    }

}
