package universe.intentions;

import java.util.ArrayList;
import java.util.List;

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

        Objective desire = objectives.get(0);

        if (desire.getDesire().equals(Verb.OWN)) {
            for (Knowledge knowledge : knowledges) {
                if (knowledge instanceof Location) {
                    Entity e = knowledge.getEntityConcerned();
                    if (e instanceof Item) {
                        Position p = e.getPosition();
                        if (character.getActionZone().contain(p)) {
                            System.out.println(e);
                            tasklist.add(new Task("addItem;" + e));
                        }
                        else {
                            tasklist.add(new Task("move;" + p));
                        }
                    }
                }
            }
        }
        else if (desire.getDesire().equals(Verb.LEARN)) {
            // TODO implement Learning plan library
        }

        return tasklist;
    }

}
