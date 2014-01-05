import java.util.ArrayList;

import universe.Position;
import universe.World;
import universe.entities.Item;
import universe.entities.NPC;
import universe.entities.Player;
import universe.utils.DatabaseManager;
import universe.utils.UI;

class Main {
    public static void main(String[] arg) {
	// Creating world
	int numberOfUselessNPC = 10;
	int numberOfGoldONtheGround = 500;
	World world = new World(100, 100);
	DatabaseManager.emptyEntities();
	UI ui = new UI(world);
	// Creating player
	Player player = new Player("Joueur", 20);
	Position playerPosition = new Position(50, 50);
	// Creating NPCs
	NPC kulvan = (NPC) DatabaseManager.create(NPC.class, "Kulvan", 20);
	NPC roger = (NPC) DatabaseManager.create(NPC.class, "Roger", 5);
	NPC allayn = (NPC) DatabaseManager.create(NPC.class, "Allayn", 10);
	NPC allaynSister = (NPC) DatabaseManager.create(NPC.class,
		"Allayn'sSister", 18);
	NPC orc = (NPC) DatabaseManager.create(NPC.class,
		"AnOrcWithInformation", 12);
	NPC bandit = (NPC) DatabaseManager.create(NPC.class,
		"BanditThatCapturedAllayn", 18);
	kulvan.setWorld(world);
	allayn.setWorld(world);
	roger.setWorld(world);
	ArrayList<NPC> uselessNPCs = new ArrayList<NPC>();
	for (int i = 0; i < numberOfUselessNPC; i++) {
	    uselessNPCs.add((NPC) DatabaseManager.create(NPC.class, "Robert"
		    + i, 5));
	    uselessNPCs.get(i).setWorld(world);
	}
	ArrayList<Item> goldcoins = new ArrayList<Item>();
	for (int i = 0; i < numberOfGoldONtheGround; i++) {
	    goldcoins.add((Item) DatabaseManager.create(Item.class,
		    "Goldcoinn°" + i, 100, true));
	    goldcoins.get(i).setWorld(world);
	}
	// Creating Items
	Item sword = (Item) DatabaseManager.create(Item.class, "Iron Sword",
		100, true);
	Item parchement = (Item) DatabaseManager.create(Item.class,
		"Parchement", 1, true);
	sword.setWorld(world);
	parchement.setWorld(world);
	world.addEntity(player, playerPosition);

	while (true) {
	    System.out.println(world.toStringInfoEntities());
	    ui.displayWorld(playerPosition);
	    player.run();
	}
    }
}
