package universe.entities;


/**
 * @author pierre
 * 
 */

public class NPC extends Character implements Runnable {

    public NPC(String name, Integer smartness) {
        super(name, smartness);
        this.generateDesires();
    }

    private void generateDesires(){
        // TODO
        // Get 500 gold and a significant other ?
    }

    @Override
    public void run() {
        // Get new knowledge of the world on line of sight
        learnFromZone(getUnderstandabilityZone());
        // Use knowledges to choose a desire
        // Execute 1 action from the desire
    }
}