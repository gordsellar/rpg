package universe.entities;

/**
 * @author pierre
 * 
 */

public class DuplicateEntityNameException extends Exception {

    private static final long serialVersionUID = 1L;

    public DuplicateEntityNameException(String name) {
	super("The name '" + name + "' already exist in the world,"
		+ " it will be renamed with a number.");
    }
}
