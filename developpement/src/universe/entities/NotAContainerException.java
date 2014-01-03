package universe.entities;

/**
 * @author pierre
 * 
 */

public class NotAContainerException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotAContainerException(Entity e, Entity f) {
	super("The action is not permitted because " + e
		+ " cannot contain other entity ");
    }
}
