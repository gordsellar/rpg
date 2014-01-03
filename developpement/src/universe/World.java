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
    public int x;
    public int y;

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

    public ArrayList<Entity> getAllEntities() {
	ArrayList<Entity> entities = new ArrayList<Entity>();
	for (int i = 0; i < x; i++) {
	    for (int j = 0; j < y; j++) {
		entities.addAll(this.cases[i][j].getEntities());
	    }
	}
	return entities;
    }

    public void removeEntity(Entity e) {
	for (int i = 0; i < x; i++) {
	    for (int j = 0; j < y; j++) {
		try {
		    this.cases[i][j].removeEntity(e);
		} finally {
		}

	    }
	}

    }

    public Entity getEntityByName(String name) {
	for (Entity e : getAllEntities()) {
	    if (e.getName() == name)
		return e;
	}
	return null;
    }
    
    public Entity getEntityById(int id) {
	for (Entity e : getAllEntities()) {
	    if (e.getId() == id)
		return e;
	}
	return null;
    }

    public void addEntity(Entity e, Position p) {
	this.cases[p.x][p.y].addEntity(e);
    }

    public ArrayList<Entity> getEntities(Zone z) {
	ArrayList<Entity> entities = new ArrayList<Entity>();
	for (Position p : z.getPositions()) {
	    // do nothing because we can have position superior to x/y but
	    // nothing below 0
	    if (p.x < this.x && p.y < this.y) {
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
	String result = "World : {\n";
	int len = this.cases.length;
	for (int i = 0; i < len; i++) {
	    result += "\t" + Arrays.toString(this.cases[i]) + "\n";
	}
	return result + "}";
    }

    public static void main(String[] args) {
	World w = new World(2, 3);
	NPC n = new NPC(w, "Roger", 14);
	NPC m = new NPC(w, "Roger", 16); // Another Roger
	Item i = new Item(w, "Candle", 500, true);
	Item j = new Item(w, "Match", 1, true);
	n.addItem(i);
	w.addEntity(n, new Position(0, 1));
	w.addEntity(m, new Position(1, 2));
	w.addEntity(j, new Position(1, 1));
	System.out.println(w);
    }
}