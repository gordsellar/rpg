package tests.universe.entities;

import org.junit.Assert;
import org.junit.Test;

import universe.World;
import universe.beliefs.Fact;
import universe.beliefs.Possession;
import universe.entities.Character;
import universe.entities.Item;
import universe.utils.DatabaseManager;

public class CharacterTest {

    @Test
    public void create() {
        World w = new World(2, 4);
        Character roger = (Character) DatabaseManager.create(Character.class,
                "Roger", 14);
        roger.setWorld(w);
        Character robert = (Character) DatabaseManager.create(Character.class,
                "Robert", 8);
        robert.setWorld(w);
        Character robert_two = (Character) DatabaseManager.create(
                Character.class, "Robert", 10);
        robert_two.setWorld(w);
        Fact f = new Fact(roger, "Roger is a dragqueen");
        Fact g = new Fact(robert, "Robert is doing drugs");
        Fact h = new Fact(robert, "Life is amazing");
        Fact e = new Fact(roger, "Life sux");
        roger.addKnowledge(f);
        roger.addKnowledge(g);
        roger.addKnowledge(e);
        robert.addKnowledge(h);
        Item matchbox = new Item("Match box", 50, true);
        matchbox.setWorld(w);
        Item match = new Item("Match", 1, true);
        match.setWorld(w);
        Item spoon = new Item("Spoon", 200, true);
        spoon.setWorld(w);
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
        Assert.assertFalse(robert_two.getName().equals("Robert"));
        System.out.println(robert.askInformationAboutAnEntity(roger, matchbox));
        Assert.assertTrue(roger.knows(new Possession(roger, matchbox)));
        Assert.assertTrue(roger.knows(new Possession(roger, match)));
        Assert.assertTrue(roger.knows(f));
        Assert.assertTrue(roger.knows(g));
        Assert.assertTrue(roger.knows(e));
        Assert.assertTrue(robert.knows(h));
    }
}
