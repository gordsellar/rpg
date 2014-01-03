package tests.universe.entities;

import org.junit.Assert;
import org.junit.Test;

import universe.World;
import universe.beliefs.Fact;
import universe.beliefs.Possession;
import universe.entities.Character;
import universe.entities.Item;

public class CharacterTest {

    @Test
    public void create() {
	World w = new World(2, 4);
	Character roger = new Character(w, "Roger", 14);
	Character robert = new Character(w, "Robert", 8);
	Character robert_two = new Character(w, "Robert", 10);
	Fact f = new Fact("Roger is a dragqueen");
	Fact g = new Fact("Robert is doing drugs");
	Fact h = new Fact("Life is amazing");
	Fact e = new Fact("Life sux");
	roger.addKnowledge(f);
	roger.addKnowledge(g);
	roger.addKnowledge(e);
	robert.addKnowledge(h);
	Item matchbox = new Item(w, "Match box", 50, true);
	Item match = new Item(w, "Match", 1, true);
	Item spoon = new Item(w, "Spoon", 200, true);
	matchbox.addItem(match);
	robert.addItem(spoon);
	roger.addItem(matchbox);
	System.out.println(w);
	System.out.println(roger.askInformationAboutAnEntity(robert, spoon));
	System.out.println(robert.askInformationAboutAnEntity(roger, match));
	Assert.assertTrue(roger.askInformationAboutAnEntity(robert, spoon)
		.size() == 2);
	Assert.assertTrue(robert.askInformationAboutAnEntity(roger, match)
		.size() == 2);
	Assert.assertFalse(robert_two.getName() == "Robert");
	System.out.println(robert.askInformationAboutAnEntity(roger, matchbox));
	Assert.assertTrue(roger.knows(new Possession(roger, matchbox)));
	Assert.assertTrue(roger.knows(new Possession(roger, match)));
	Assert.assertTrue(roger.knows(f));
	Assert.assertTrue(roger.knows(g));
	Assert.assertTrue(roger.knows(e));
	Assert.assertTrue(robert.knows(h));
    }
}