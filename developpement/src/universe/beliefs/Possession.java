package universe.beliefs;

import universe.entities.Entity;
import universe.entities.Item;

/**
 * 
 * @author sguingoin
 * 
 */
public class Possession extends Knowledge {

    private Entity possessedBy;
    private Item possession;

    /**
     * @param possessedBy
     *            the Entity possessing the Item
     * @param possession
     *            the Item being possessed
     */
    public Possession(Entity possessedBy, Item possession) {
        this.setPossessedBy(possessedBy);
        this.setPossession(possession);
    }

    /**
     * @return the possessedBy
     */
    public Entity getPossessedBy() {
        return possessedBy;
    }

    /**
     * @param possessedBy
     *            the Entity possessing the Item
     */
    public void setPossessedBy(Entity possessedBy) {
        resetAge();
        this.possessedBy = possessedBy;
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
        return "Possession [age=" + getAge() + ", possessedBy=" + possessedBy
                + ", possession=" + possession + "]";
    }
}
