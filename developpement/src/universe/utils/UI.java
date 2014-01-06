package universe.utils;

import java.util.Scanner;

import universe.Position;
import universe.World;
import universe.entities.Entity;

/**
 * @author claire
 * 
 */

public class UI {
    private World world;
    private static final String question = "What do you want to do ?\n";
    private static final String methodError = "this action can not be carried out\n";
    private static final String exception = "An exception has been raised :\n";
    private static final String unexpected = "Unexpected answer\n";
    private static final String possibilities = "Possible actions : \n"
            + " -get informations from the zone (enter : getinfo)\n"
            + " -talk to someone in particular (enter : talkto <name_of_the_person>)\n"
            + " -ask information about an entity to someone (enter askinfo <name_of_the_entity> <name_of_the_person_to_ask>)\n"
            + " -give an objet to someone (enter : give <name_of_the_object> <name_of_the_person>)\n"
            + " -kill someone (enter : kill <name_of_the_person>)\n"
            + " -loot someone (enter : loot <name_of_the_person>)\n"
            + " -pick up an item (enter : pickup <name_of_the_item>"
            + " -read an object you have (enter : read <name_of_the_object>)\n"
            + " -go to a position in particular (enter : goto <x> <y>)\n";

    public UI(World world) {
        this.world = world;
    }

    public UI() {
        this.world = null;
    }

    public void display(String message) {
        System.out.println(message);
    }

    private String readUserMessage() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayQuestion() {
        this.display(question);
    }

    public void displayWorld(Position p) {
        this.display(this.world.toAsciiArt(p));
    }

    public void displayPossibilities() {
        this.display(possibilities);
    }

    public void displayMethodError() {
        this.display(methodError);
    }

    public void displayException(String m) {
        this.display(this.exception + m);
    }

    public String getUserAction() {
        String result = "";
        String action = this.readUserMessage();
        if (action.equals("getinfo")) {
            result = "learnFromUnderstandabilityZone";
        } else if (action.startsWith("talkto")) {
            String npcName = action.split(" ")[1];
            Entity npc = DatabaseManager.findByName(npcName);
            result = "discussWith;Character [id=" + npc.getId() + "]";
        } else if (action.startsWith("askinfo")) {
            String entityName = action.split(" ")[1];
            Entity entity = DatabaseManager.findByName(entityName);
            String npcName = action.split(" ")[2];
            Entity npc = DatabaseManager.findByName(npcName);
            result = "askInformationAboutAnEntity;Character [id=" + npc.getId()
                    + "];Entity [id=" + entity.getId() + "]";
        } else if (action.startsWith("give")) {
            String itemName = action.split(" ")[1];
            Entity item = DatabaseManager.findByName(itemName);
            String npcName = action.split(" ")[2];
            Entity npc = DatabaseManager.findByName(npcName);
            result = "give;Character [id=" + npc.getId() + "];Item [id="
                    + item.getId() + "]";
        } else if (action.startsWith("askobject")) {
            String itemName = action.split(" ")[1];
            Entity item = DatabaseManager.findByName(itemName);
            String npcName = action.split(" ")[2];
            Entity npc = DatabaseManager.findByName(npcName);
            result = "askObject;Character [id=" + npc.getId() + "];Item [id="
                    + item.getId() + "]";
        } else if (action.startsWith("kill")) {
            String npcName = action.split(" ", 2)[1];
            Entity npc = DatabaseManager.findByName(npcName);
            result = "kill;Character [id=" + npc.getId() + "]";
        } else if (action.startsWith("loot")) {
            String npcName = action.split(" ")[1];
            Entity npc = DatabaseManager.findByName(npcName);
            result = "loot;Character [id=" + npc.getId() + "]";
        } else if (action.startsWith("read")) {
            String itemName = action.split(" ")[1];
            Entity item = DatabaseManager.findByName(itemName);
            result = "readObject;Item [id=" + item.getId() + "]";
        } else if (action.startsWith("goto")) {
            String x = action.split(" ")[1];
            String y = action.split(" ")[2];
            result = "move;Position [x=" + x + ", y=" + y + "]";
        }
        else if (action.startsWith("pickup")) {
            String itemName = action.split(" ")[1];
            Entity item = DatabaseManager.findByName(itemName);
            result = "addItem;Item [id=" + item.getId() + "]";
        } else {
            this.display(unexpected);
        }
        return result;
    }
}
