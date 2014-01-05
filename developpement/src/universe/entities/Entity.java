package universe.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import universe.Position;
import universe.World;
import universe.beliefs.Fact;
import universe.beliefs.Knowledge;
import universe.beliefs.KnowledgesManager;
import universe.beliefs.Location;
import universe.beliefs.Possession;
import universe.utils.DatabaseManager;
import universe.utils.UI;

/**
 * @author pierre
 * 
 */

public class Entity {

    protected World world;
    protected Position position;
    protected String name;
    protected ArrayList<Item> inventory;
    protected KnowledgesManager knowledgesManager = new KnowledgesManager();
    protected int id;

    public Entity(String name) {
        String unifiedName;
        Boolean nameIsUnique = false;
        int count = 1;

        while (!nameIsUnique) {
            try {
                if (count == 1) {
                    unifiedName = name;
                } else {
                    unifiedName = name + " " + count;
                }
                this.setName(unifiedName);
                nameIsUnique = true;
            } catch (DuplicateEntityNameException e) {
                count++;
                UI ui = new UI(this.world);
                ui.displayException(e.getMessage());
            }
        }

        this.setInventory(new ArrayList<Item>());
    }

    public void setWorld(World w) {
        this.world = w;

        // TODO Make the position system cleaner ?
        int x, y;
        x = new Random().nextInt(w.x);
        y = new Random().nextInt(w.y);
        this.position = new Position(x, y);
        w.addEntity(this, this.position);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DuplicateEntityNameException {
        if (DatabaseManager.findBy(Entity.class, new String[] { "name" },
                new String[] { name }) != null) {
            throw new DuplicateEntityNameException(name);
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(Position position) {
        // Changing the position of the entity as asked
        if (!(position.x < 0 || position.x >= world.x || 
              position.y < 0 || position.y >= world.y)) {
            this.position = position;
        }
        // If position is out of range, place at the closest valid position
        else {
            if (position.x < 0)
                this.position.x = 0;
            if (position.x >= world.x)
                this.position.x = world.x - 1;
            if (position.y < 0)
                this.position.y = 0;
            if (position.y >= world.y)
                this.position.y = world.y - 1;
        }
        world.moveEntity(this, this.position);
         // Changing the position of all the contained entity (recursively) 
        for (Entity e : this.inventory) {
            e.setPosition(this.position);
            world.moveEntity(e, position);
        }
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public boolean got(Item i) {
        return this.getInventory().contains(i);
    }

    public boolean gotAll(Collection<Item> c) {
        return this.getInventory().containsAll(c);
    }

    public void addItem(Item i) {
        this.world.removeEntity(i);
        i.setPosition(this.getPosition());
        this.inventory.add(i);
    }

    public void removeItem(Item i) {
        this.inventory.remove(i);
    }

    public List<Knowledge> getVisibleKnowledges() {
        List<Knowledge> knowledges = new ArrayList<>();
        knowledges.add(new Location(this, getPosition()));

        return knowledges;
    }

    public List<Knowledge> getKnowledges() {
        updateAutomaticKnowledges();
        return this.knowledgesManager.getKnowledges();
    }

    public boolean knows(Knowledge k) {
        return this.getKnowledges().contains(k);
    }

    public boolean knowsAll(Collection<Knowledge> c) {
        return this.getKnowledges().containsAll(c);
    }

    public ArrayList<Knowledge> getKnowledgeAbout(Entity e) {
        ArrayList<Knowledge> knowledgesAboutAnEntity;
        knowledgesAboutAnEntity = new ArrayList<Knowledge>();
        if (this == e) {
            knowledgesAboutAnEntity.add(new Fact(this, "I'm " + e));
        }
        for (Knowledge k : this.getKnowledges()) {
            if (k.getEntityConcerned() == e) {
                knowledgesAboutAnEntity.add(k);
            }
            else if (k instanceof Possession) {
                Possession p = ((Possession) k);
                if (p.getPossession() == e) {
                    knowledgesAboutAnEntity.add(k);
                }
            }
        }
        return knowledgesAboutAnEntity;
    }

    public boolean knowsAbout(Entity e) {
        return getKnowledgeAbout(e).size() != 0;
    }

    protected void updateAutomaticKnowledges() {
        ArrayList<Knowledge> automaticKnowledges = new ArrayList<Knowledge>();
        automaticKnowledges.add(new Location(this, position));
        for (Item i : this.inventory) {
            automaticKnowledges.addAll(i.getKnowledges());
            automaticKnowledges.add(new Possession(this, i));
        }
        knowledgesManager.addKnowledges(automaticKnowledges);
    }

    public void addKnowledge(Knowledge k) {
        this.knowledgesManager.addKnowledge(k);
    }

    @Override
    public String toString() {
        String coffee = name + " (id=" + id + ")";
        if (inventory.size() != 0) {
            coffee += ", inventory=" + inventory;
        }
        // We don't print the knowledge of all object
        // Just the character one
        return coffee;
    }
}
