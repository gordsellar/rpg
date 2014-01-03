package tests.universe.entities;

import org.junit.Assert;
import org.junit.Test;

import universe.Position;
import universe.World;
import universe.entities.Item;
import universe.entities.NPC;
import universe.utils.DatabaseManager;

public class NPCTest {

    @Test
    public void test() {
        World w = new World(1, 2);
        NPC npc1 = (NPC) DatabaseManager.create(NPC.class, "Azu", 50);
        npc1.setWorld(w);
        npc1.setPosition(new Position(1, 1));
        Item item1 = (Item) DatabaseManager.create(Item.class, "Sword", 20,
                true);
        item1.setWorld(w);
        item1.setPosition(new Position(1, 2));

        // Azu doesn't know about the Sword
        Assert.assertFalse(npc1.knowsAbout(item1));
        // Sword knows about itself
        Assert.assertTrue(item1.knowsAbout(item1));

        // run 1 turn to make Azu learn things around him
        npc1.run();

        // Azu knows about the Sword
        Assert.assertTrue(npc1.knowsAbout(item1));
    }
}
