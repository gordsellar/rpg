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
            if (e.getName() == name) {
                return e;
            }
        }
        return null;
    }

    public Entity getEntityById(int id) {
        for (Entity e : getAllEntities()) {
            if (e.getId() == id) {
                return e;
            }
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
    
    public String toAsciiArt() {
        String result = "World :\n";
        int height = this.cases.length;
        int width = this.cases[0].length;
        for (int i = 0; i < height; i++) {
            String line1 = ""; String line2 = ""; String line3 = "";
            for (int j = 0; j < width; j++) {  
                // Building landColor and landSymbol for Ascii Art
                String landColor = "\033[0m";
                String landSymbol = "#";
                if (this.cases[i][j].getLandType() == LandType.Grass) {
                    landColor = "\033[32m"; // Console code for green
                    landSymbol = "„ ";
                }
                else if (this.cases[i][j].getLandType() == LandType.Tree) {
                    landColor = "\033[32m";
                    landSymbol = "φ ";
                }
                else if (this.cases[i][j].getLandType() == LandType.Dirt) {
                    landColor = "\033[33m"; // Console code for yellow
                    landSymbol = "Ξ ";
                }
                else if (this.cases[i][j].getLandType() == LandType.Cliff) {
                    landColor = "\033[33m";
                    landSymbol = "Δ ";
                }
                
                // Building Ascii Art
                line1 += landColor + landSymbol + landSymbol + landSymbol;
                line3 += landColor + landSymbol + landSymbol + landSymbol;
                // Line 2 that contains items and npc symbols
                boolean containsItem = false;
                boolean containsNPC = false;
                for (Entity e : this.cases[i][j].getEntities()) {
                    if (Item.class.isInstance(e)) {
                        containsItem = true;
                    }
                    if (NPC.class.isInstance(e)) {
                        containsNPC = true;
                    }
                }
                if (containsItem && !containsNPC) {
                    line2 += landColor + landSymbol + landSymbol + "\033[0m\033[1m♦\033[0m ";
                }
                else if (containsNPC && !containsItem) {
                    line2 += "\033[0m\033[1mθ \033[0m" + landColor + landSymbol + landSymbol;
                }
                else if (containsItem && containsNPC) {
                    line2 += "\033[0m\033[1mθ \033[0m" + landColor + landSymbol +
                             "\033[0m\033[1m♦\033[0m ";
                }
                else {
                  line2 += landColor + landSymbol + landSymbol + landSymbol;
                }
            }
            line1 += "\n"; line2 += "\n"; line3 += "\n";
            result += line1 + line2 + line3 + "\033[0m";
        }
        return result;
    }

    public static void main(String[] args) {
        World w = new World(2, 3);
        NPC n = new NPC("Roger", 14);
        n.setWorld(w);
        NPC m = new NPC("Roger", 16); // Another Roger
        m.setWorld(w);
        Item i = new Item("Candle", 500, true);
        i.setWorld(w);
        Item j = new Item("Match", 1, true);
        j.setWorld(w);
        n.addItem(i);
        w.addEntity(n, new Position(0, 1));
        w.addEntity(m, new Position(1, 2));
        w.addEntity(j, new Position(1, 1));
        System.out.println(w);
        System.out.println(w.toAsciiArt());
    }
}
