package tests.universe.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import universe.Position;
import universe.Zone;
import universe.entities.Item;
import universe.entities.NPC;
import universe.utils.DatabaseManager;

public class DatabaseManagerTest {

    @Before
    public void setUp() {
        DatabaseManager.emptyEntities();
    }

    @Test
    public void testCreate() {
        DatabaseManager.create(NPC.class, "Azuryus", 5);
        NPC npc = (NPC) DatabaseManager.findById(0);
        Assert.assertNotNull(npc);
        Assert.assertEquals("Azuryus", npc.getName());
    }

    @Test
    public void testFindFromString() {
        Zone zOrig = new Zone(new Position(2, 3), 5);

        Zone zFound = (Zone) DatabaseManager.findFromString(zOrig.toString());

        Assert.assertNotNull(zFound);
        Assert.assertEquals(zOrig.getPosition().x, zFound.getPosition().x);
        Assert.assertEquals(zOrig.getPosition().y, zFound.getPosition().y);
    }

    @Test
    public void testFindBy() {
        // First test: NPC
        NPC npcCreated = (NPC) DatabaseManager.create(NPC.class, "Azuryus", 5);

        System.out.println(DatabaseManager.getEntities());

        NPC npcFound = (NPC) DatabaseManager.findBy(NPC.class,
                new String[] { "name" }, new String[] { "Azuryus" });

        Assert.assertNotNull(npcFound);
        Assert.assertEquals(npcCreated.getName(), npcFound.getName());

        // Second test: Item
        Item itemCreated = (Item) DatabaseManager.create(Item.class, "Sword",
                50, true);

        Item itemFound = (Item) DatabaseManager.findBy(Item.class,
                new String[] { "carriable" }, new String[] { "true" });

        Assert.assertNotNull(itemFound);
        Assert.assertEquals(itemCreated.getName(), itemFound.getName());
    }

}
