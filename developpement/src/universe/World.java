package universe;

import java.util.ArrayList;
import java.util.Arrays;

import universe.beliefs.Knowledge;
import universe.entities.Entity;
import universe.entities.Item;
import universe.entities.NPC;

/**
 * @author pierre
 * 
 */

public class World {

    private Case[][] cases;
    private int x;
    private int y;

    public World(int x, int y) {
	this.x = x;
	this.y = y;
	this.cases = new Case[x][y];
	for (int i = 0; i < x; i++) {
	    for (int j = 0; j < y; j++) {
		this.cases[i][j] = new Case();
	    }
	}
    }
    
    public void addEntity(Entity e, Position p){
	this.cases[p.x][p.y].addEntity(e);
    }

    public ArrayList<Entity> getEntities(Zone z) {
	ArrayList<Entity> entities = new ArrayList<Entity>();
	for (Position p : z.getPositions()) {
	    // do nothing because we can have position superior to x/y but
	    // nothing below 0
	    if (p.x <= this.x && p.y <= this.y) {
		entities.addAll(this.cases[p.x][p.y].getEntities());
	    }
	}
	return entities;
    }

    public ArrayList<Knowledge> getKnowledges(Zone z) {
	ArrayList<Knowledge> knowledges = new ArrayList<Knowledge>();
	ArrayList<Entity> entities;
	entities = this.getEntities(z);
	for (Entity e : entities) {
	    knowledges.addAll(e.getKnowledges());
	}
	return knowledges;
    }

    @Override
    public String toString() {
	String coffee="World : {\n";
	int len = this.cases.length;
	for (int i = 0; i < len; i++) {
	    coffee += "\t" + Arrays.toString(this.cases[i]) + "\n";
	}
	return coffee + "}";
    }

    public static void main(String[] args) {
	World w = new World(2, 3);
	NPC n = new NPC("Roger", 14);
	Item i = new Item("Candle", true);
	Item j = new Item("Match", true);
	n.addItem(i);
	w.addEntity(n, new Position(0,1));
	w.addEntity(j, new Position(1,1));
	System.out.println(w);
    }
}