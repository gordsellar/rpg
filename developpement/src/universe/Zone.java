package universe;

/**
 * @author pierre
 * 
 */

public class Zone {

	private Position position;
	private int radius;

	public Zone(Position position, int radius) {
		this.position = position;
		this.radius = radius;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Zone [position=" + position + ", radius=" + radius + "]";
	}
}