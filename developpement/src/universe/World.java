package universe;

import java.util.ArrayList;
import java.util.Arrays;

import universe.beliefs.Knowledge;
import universe.entities.Entity;
import universe.entities.Item;
import universe.entities.NPC;
import universe.entities.Player;

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
    
    public String toAsciiArt(Position playerPosition) {
        String result = "World :\n";
        int height = this.cases.length;
        int width = this.cases[0].length;
        
        // Display width numbers
        result += "     ";
        for (int i = playerPosition.y - 10; i <= playerPosition.y + 10; i++) {
            if (!(i < 0 || i >= width)) {
                if (i < 10)
                    result += "  " + i + "   ";
                else if (i < 100)
                    result += "  " + i + "  ";
                else
                    result += "  " + i + " ";
            }
            else
                result += "      ";
        }
        result += "\n";
        
        // Display map
        for (int i = playerPosition.x - 5; i <= playerPosition.x + 5; i++) {
            String line1 = "", line2 = "", line3 = "";
            for (int j = playerPosition.y - 10; j <= playerPosition.y + 10; j++) {
                // Building landColor and landSymbol for Ascii Art
                String landColor = "\033[0m";
                String landSymbol = "# ";
                boolean containsItem = false;
                boolean containsNPC = false;
                boolean containsPlayer = false;
                if (!(i < 0 || i >= height || j < 0 || j >= width)) {
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
                    for (Entity e : this.cases[i][j].getEntities()) {
                        if (Item.class.isInstance(e))
                            containsItem = true;
                        if (NPC.class.isInstance(e))
                            containsNPC = true;
                        if (Player.class.isInstance(e))
                            containsPlayer = true;
                    }
                }
                // Building Ascii Art
                line1 += landColor + landSymbol + landSymbol + landSymbol;
                line3 += landColor + landSymbol + landSymbol + landSymbol;
                // Line 2 that contains items and npc symbols
                String line2s1 = landColor + landSymbol;
                String line2s2 = landColor + landSymbol;
                String line2s3 = landColor + landSymbol;
                if (containsNPC)
                    line2s1 = "\033[0m\033[1mθ\033[0m ";
                if (containsPlayer)
                    line2s2 = "\033[0m\033[1m⊕\033[0m ";
                if (containsItem)
                    line2s3 = "\033[0m\033[1m♦\033[0m ";
                line2 += line2s1 + line2s2 + line2s3;
            }
            line1 = "     " + line1 + "\n";
            if (!(i < 0 || i >= height)) {
                if (i < 10)
                    line2 = " " + i + "   " + line2 + "\n";
                else if (i < 100)
                    line2 = " " + i + "  " + line2 + "\n";
                else
                    line2 = " " + i + " " + line2 + "\n";
            }
            else
                line2 = "     " + line2 + "\n";
            line3 = "     " + line3 + "\n"; 
            result += line1 + line2 + line3 + "\033[0m";
        }
        
        return result;
    }
    /*
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
    }
    */
}
