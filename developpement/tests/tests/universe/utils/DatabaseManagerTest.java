package tests.universe.utils;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

import universe.Position;
import universe.Zone;
import universe.entities.Item;
import universe.entities.NPC;
import universe.utils.DatabaseManager;

public class DatabaseManagerTest {

    @Test
    public void testCreate() {
        try {
            DatabaseManager.create(NPC.class, "Azuryus", 5);
            NPC npc = (NPC) DatabaseManager.findById(0);
            Assert.assertNotNull(npc);
            Assert.assertEquals("Azuryus", npc.getName());
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            fail("Exception");
        }
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
        try {
            // First test: NPC
            NPC npcCreated = (NPC) DatabaseManager.create(NPC.class, "Azuryus",
                    5);

            NPC npcFound = (NPC) DatabaseManager.findBy(NPC.class,
                    new String[] { "name" }, new String[] { "Azuryus" });

            Assert.assertNotNull(npcFound);
            Assert.assertEquals(npcCreated.getName(), npcFound.getName());

            // Second test: Item
            Item itemCreated = (Item) DatabaseManager.create(Item.class,
                    "Sword", true);

            Item itemFound = (Item) DatabaseManager.findBy(Item.class,
                    new String[] { "carriable" }, new String[] { "true" });

            Assert.assertNotNull(itemFound);
            Assert.assertEquals(itemCreated.getName(), itemFound.getName());
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Exception");
        }
    }

}
