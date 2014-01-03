package universe.beliefs;

import universe.entities.Entity;
import universe.entities.Item;

/**
 * 
 * @author sguingoin
 * 
 */
public class Possession extends Knowledge {

    private Item possession;

    /**
     * @param possessedBy
     *            the Entity possessing the Item
     * @param possession
     *            the Item being possessed
     */
    public Possession(Entity possessedBy, Item possession) {
        this.setEntityConcerned(possessedBy);
        this.setPossession(possession);
    }

    /**
     * @return the possession
     */
    public Item getPossession() {
        return possession;
    }

    /**
     * @param possession
     *            the Item being possessed by the Entity
     */
    public void setPossession(Item possession) {
        resetAge();
        this.possession = possession;
    }

    @Override
    public String toString() {
        return entityConcerned.getName() + " possess " + possession.getName();
    }
}
