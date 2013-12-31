package universe;

import java.util.ArrayList;

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

    public Boolean contain(Position p) {
	return this.position.getDistance(p) <= this.radius;
    }

    public ArrayList<Position> getPositions() {
	ArrayList<Position> positions = new ArrayList<Position>();
	Position p = new Position(0, 0);
	int minX = this.position.x - this.radius;
	int maxX = this.position.x + this.radius;
	int minY = this.position.y - this.radius;
	int maxY = this.position.y + this.radius;
	for (int i = minX; i < maxX; i++) {
	    for (int j = minY; j < maxY; j++) {
		p.set(i, j);
		if (this.contain(p) && i >= 0 && j >= 0) {
		    positions.add(new Position(i, j));
		}
	    }
	}
	return positions;
    }

    @Override
    public String toString() {
        return "Zone [position=" + position + ", radius=" + radius + "]";
    }
}