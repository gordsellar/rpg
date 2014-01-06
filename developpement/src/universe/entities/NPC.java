package universe.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import universe.desires.DesiresManager;
import universe.desires.Objective;
import universe.desires.Verb;
import universe.intentions.PlanLibrary;
import universe.intentions.Task;

/**
 * @author pierre
 * 
 */

public class NPC extends Character implements Runnable {

    private DesiresManager desiresManager = new DesiresManager();
    private List<Task> currentTasks = new ArrayList<>();
    private int currentTaskIndex = 0;

    public NPC(String name, Integer smartness) {
        super(name, smartness);
        this.generateDesires(Verb.OWN);
    }

    public void addDesire(Objective o) {
        desiresManager.addDesire(o);
    }

    public void generateDesires(Verb desire) {
        // TODO
        // Get 500 gold and a significant other ?
        desiresManager.addDesire(new Objective(desire, 1));
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += " " + desiresManager;
        return result;
    }

    @Override
    public void run() {
        do {
            // UI ui = new UI(this.world);
            // ui.display(this.toString());
            // Get new knowledge of the world on line of sight
            updateKnowledges();
            learnFromZone(getUnderstandabilityZone());
            // Use knowledges to choose a desire
            if (currentTaskIndex >= currentTasks.size()) {
                currentTasks = PlanLibrary.getTaskList(this,
                        desiresManager.getObjectives());
                currentTaskIndex = 0;
            }
            // Execute 1 action from the desire
            if (!currentTasks.isEmpty()) {
                Task t = currentTasks.get(currentTaskIndex);
                // ui.display(t.toString());
                currentTaskIndex++;
                List<Object> completeMethod;
                try {
                    completeMethod = t.getMethod();
                    synchronized (world) {
                        ((Method) completeMethod.get(0)).invoke(this,
                                completeMethod.get(1));
                    }
                    // System.out.println(this.name + " : "
                    // + ((Method) completeMethod.get(0)).getName());
                } catch (ClassNotFoundException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
                    // ui.display(e.getMessage());
                    e.printStackTrace();
                }
            }

            // Slow down the NPCs...
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (world.active && !getState().equals(CharacterState.Dead));
    }
}
