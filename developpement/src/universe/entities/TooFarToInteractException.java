package universe.entities;

/**
 * @author pierre
 * 
 */

public class TooFarToInteractException extends Exception {

    private static final long serialVersionUID = 1L;

    public TooFarToInteractException(Entity e, Entity f) {
	super("The action is not permitted because " + e.getName()
		+ " is too far away from " + f.getName());
    }
}
