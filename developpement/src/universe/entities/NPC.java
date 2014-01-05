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
import universe.utils.UI;

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
    }

    public void generateDesires(Verb desire) {
	// TODO
	// Get 500 gold and a significant other ?
	desiresManager.addDesire(new Objective(desire, 1));
    }

    @Override
    public void run() {
	UI ui = new UI(this.world);
	ui.display(this.toString());
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
	    ui.display(t.toString());
	    currentTaskIndex++;
	    List<Object> completeMethod;
	    try {
		completeMethod = t.getMethod();
		((Method) completeMethod.get(0)).invoke(this,
			completeMethod.get(1));
	    } catch (ClassNotFoundException | IllegalAccessException
		    | IllegalArgumentException | InvocationTargetException e) {
		ui.display(e.getMessage());
	    }
	}

	// Slow down the NPCs...
	// try {
	// Thread.sleep(1000 * 2);
	// }
	// catch (InterruptedException e) {
	// e.printStackTrace();
	// }
    }
}
