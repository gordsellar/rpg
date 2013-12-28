package universe;

import universe.entities.*;

/**
 * @author pierre
 * 
 */

public class Case {

	private Entity[] entities;
	private LandType landtype;

	public Case() {
		this.landtype = this.getRandomLandType();
	}

	private LandType getRandomLandType() {
		// TODO return something else than Grass always
		return LandType.Grass;
	}

	public Case(LandType landtype, Entity[] entities) {
		this.landtype = landtype;
		this.entities = entities;
	}

	public void addEntity(Entity entity) {
		// TODO
	}

	public Entity[] getentities() {
		return entities;
	}

	public void setentities(Entity[] entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		String coffee = "There is " + this.landtype + " on this case.";
		// TODO entities coffee += ""
		return coffee;
	}
}