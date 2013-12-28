package universe;

/**
 * @author pierre
 * 
 */

public class World {

	private Case[][] cases;

	public World(int x, int y) {
		this.cases = new Case[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				this.cases[x][y] = new Case();
			}
		}
	}
}