import java.util.ArrayList;

import universe.Position;
import universe.World;
import universe.beliefs.Location;
import universe.desires.Objective;
import universe.desires.Verb;
import universe.entities.Item;
import universe.entities.NPC;
import universe.entities.Player;
import universe.utils.DatabaseManager;
import universe.utils.UI;

class Main {
    public static void main(String[] arg) {
	// Creating world
	int numberOfUselessNPC = 3;
	int numberOfGoldONtheGround = 500;
	World world = new World(100, 100);
	DatabaseManager.emptyEntities();
	UI ui = new UI(world);
	// Creating player
	Player player = new Player("Player", 20);
	player.setWorld(world);
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
	Objective seeAllayn = new Objective(Verb.LEARN, 2, allayn);
	kulvan.setWorld(world);
	roger.setWorld(world);
	allayn.setWorld(world);
	allaynSister.setWorld(world);
	orc.setWorld(world);
	bandit.setWorld(world);
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
	Position allaynPosition = new Position(1, 1);
	Location alaynLocation = new Location(allayn, allaynPosition);
	Item sword = (Item) DatabaseManager.create(Item.class, "IronSword",
		100, true);
	Item parchement = (Item) DatabaseManager.create(Item.class,
		"Parchement", 1, true);
	Objective ownSword = new Objective(Verb.OWN, 5, sword);
	Objective ownparch = new Objective(Verb.OWN, 10, parchement);
	kulvan.addDesire(seeAllayn);
	allaynSister.addDesire(seeAllayn);
	roger.addDesire(ownparch);
	roger.addDesire(ownSword);
	orc.addKnowledge(alaynLocation);
	sword.setWorld(world);
	parchement.setWorld(world);

	new Thread(kulvan).start();
	new Thread(roger).start();
	new Thread(allayn).start();
	new Thread(allaynSister).start();
	new Thread(orc).start();
	new Thread(bandit).start();
	for (NPC npc : uselessNPCs) {
	    new Thread(npc).start();
	}

	while (world.active) {
	    ui.displayWorld(player.getPosition());
	    System.out.println("Your state : " + player);
	    System.out.println("Current Character in the world : \n"
		    + world.toStringNameEntities());
	    player.run();
	}
    }
}
