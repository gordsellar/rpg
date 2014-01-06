package universe.intentions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import universe.Position;
import universe.beliefs.Knowledge;
import universe.beliefs.Location;
import universe.desires.Objective;
import universe.desires.Verb;
import universe.entities.Character;
import universe.entities.Entity;
import universe.entities.Item;

public class PlanLibrary {

    public static List<Task> getTaskList(Character character,
            List<Objective> objectives) {
        List<Task> tasklist = new ArrayList<>();
        List<Knowledge> knowledges = character.getKnowledges();
        Collections.shuffle(knowledges);

        if (!objectives.isEmpty()) {
            Objective desire = objectives.get(new Random().nextInt(objectives
                    .size()));

            if (desire.getDesire().equals(Verb.OWN)) {
                for (Knowledge knowledge : knowledges) {
                    if (knowledge instanceof Location) {
                        Entity e = knowledge.getEntityConcerned();
                        if (e instanceof Item) {
                            if (!character.got((Item) e)) {
                                Position p = e.getPosition();
                                if (!character.getActionZone().contain(p)) {
                                    tasklist.add(new Task("move;" + p));
                                }
                                tasklist.add(new Task("addItem;Item [id="
                                        + e.getId() + "]"));
                            }
                        }
                    }
                }
            }
            else if (desire.getDesire().equals(Verb.LEARN)) {
                for (Knowledge knowledge : knowledges) {
                    if(knowledge instanceof Location) {
                        Entity e = knowledge.getEntityConcerned();
                        if (e instanceof Character && !e.equals(character)) {
                            Position p = e.getPosition();
                            if (!character.getActionZone().contain(p)) {
                                tasklist.add(new Task("move;"+p));
                            }
                            tasklist.add(new Task("discussWith;Character [id="
                                    + e.getId() + "]"));
                        }
                    }
                }
            }
        }

        // if the npc doesn't have anything to do, move around...
        if (tasklist.isEmpty()) {
            Position p = character.getPosition();
            int x = new Random().nextInt(5) - 2;
            int y = new Random().nextInt(5) - 2;
            tasklist.add(new Task("move;Position [x=" + (p.x + x) + ", y="
                    + (p.y + y) + "]"));
        }

        return tasklist;
    }
}
