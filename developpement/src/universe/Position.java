package universe;

/**
 * @author pierre
 * 
 */

public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
	this.set(x, y);
    }
    
    public void set(int x,int y){
	this.x = x;
	this.y = y;
    }

    /**
     * @param remote
     *            A position
     * @return The Distance between the two position
     */
    public int getDistance(Position remote) {
	return Math.abs(this.x - remote.x) + Math.abs(this.y - remote.y);
    }

    @Override
    public String toString() {
	return "Position [x=" + x + ", y=" + y + "]";
    }
}
