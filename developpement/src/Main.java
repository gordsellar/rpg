import universe.*;
import universe.utils.*;
import universe.entities.Item;
import universe.entities.NPC;
import universe.entities.Player;

class Main {
    public static void main(String[] arg) {
	// Creating world
	World world = new World(6, 6);
	DatabaseManager.emptyEntities();
	UI ui = new UI(world);
	// Creating player
	Player player = new Player("Joueur", 20);
	Position playerPosition = new Position(3, 3);
	// Creating NPCs
	NPC maxime = (NPC) DatabaseManager.create(NPC.class, "Maxime", 2);
	NPC pierre = (NPC) DatabaseManager.create(NPC.class, "Pierre", 10);
	// Creating Items
	Item sword = (Item) DatabaseManager.create(Item.class, "Iron Sword",
		100, true);
	Item parchement = (Item) DatabaseManager.create(Item.class,
		"Parchement", 1, true);
	// Adding all to World
	maxime.setWorld(world);
	pierre.setWorld(world);
	sword.setWorld(world);
	parchement.setWorld(world);
	world.addEntity(player, playerPosition);

	while (true) {
	    ui.displayWorld(playerPosition);
	    player.run();
	}
    }
}
