package universe.entities;

import universe.World;

/**
 * @author pierre
 * 
 */

public class NPC extends Character {

    public NPC(World w, String name, Integer smartness) {
        super(w, name, smartness);
        this.generateDesires();
    }
    
    private void generateDesires(){
	// TODO
	// Get 500 gold and a significant other ?
    }

}