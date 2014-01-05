import universe.*;
import universe.entities.Item;
import universe.entities.NPC;
import universe.utils.UI;

class Main {
    public static void main(String [] arg) {
        World world = new World(10, 20);
        NPC maxime = new NPC("Maxime", 2);
        NPC pierre = new NPC("Pierre", 10);
        Item ordinateur = new Item("Computer", 10000, true);
        Item boiteChocolat = new Item("Boite de chocolats", 50, true);
        world.addEntity(maxime, new Position(2, 2));
        world.addEntity(pierre, new Position(5, 2));
        world.addEntity(ordinateur, new Position(8, 10));
        world.addEntity(boiteChocolat, new Position(9, 1));
        UI ui = new UI(world);
        ui.displayWorld();
        }
}
