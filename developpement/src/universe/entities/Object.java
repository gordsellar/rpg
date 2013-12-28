package universe.entities;
/**
 * @author pierre
 * 
 */

public class Object extends Entity {

	private Boolean carriable;

	public Object(String name, int id, Boolean carriable) {
		super(name, id);
		this.carriable = carriable;
	}

	public Boolean isCarriable() {
		return carriable;
	}

	public void setCarriable(Boolean carriable) {
		this.carriable = carriable;
	}

}