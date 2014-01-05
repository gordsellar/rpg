package tests.universe.entities;

import java.io.*;

import universe.*;
import universe.beliefs.*;
import universe.desires.*;
import universe.entities.*;
import universe.intentions.*;
import universe.utils.DatabaseManager;

public class PlayerTest {

    public static void main(String[] args) {

        World w = new World(5,5);
        System.out.println(w);
        NPC natsu = (NPC) DatabaseManager.create(NPC.class, "Natsu", 14);
        natsu.setWorld(w);
       
        Player lucy = (Player) DatabaseManager.create(Player.class, "Lucy", 20);
        lucy.setWorld(w);

        Fact f = new Fact(natsu, "Natsu can eat fire");
        Fact g = new Fact(lucy, "Lucy is the player");
        natsu.addKnowledge(f);
        natsu.addKnowledge(g);


        Item spoon = new Item("Spoon", 200, true);
        spoon.setWorld(w);
        
        System.out.println(w);
        while(true) {
            lucy.run();
            System.out.println(w);
        }
    }
}
