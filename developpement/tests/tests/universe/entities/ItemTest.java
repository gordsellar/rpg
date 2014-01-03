package tests.universe.entities;

import org.junit.Assert;
import org.junit.Test;

import universe.World;
import universe.entities.Item;

public class ItemTest {

    @Test
    public void create() {
	World w = new World(1, 1);
	Item matchbox = new Item(w, "Match box", 50, true);
	Item match = new Item(w, "Match", 1, true);
	matchbox.addItem(match);
	System.out.println(w);
	Assert.assertEquals(matchbox.getValue(), 51);
	Assert.assertEquals(match.getValue(), 1);
    }
}