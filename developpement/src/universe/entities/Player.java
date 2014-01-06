package universe.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import universe.intentions.Task;
import universe.utils.UI;

/**
 * @author claire
 * 
 */

public class Player extends Character implements Runnable {

    public Player(String name, Integer smartness) {
	super(name, smartness);
	this.generateDesires();
    }

    private void generateDesires() {
    }

    @Override
    public void run() {
	// A completer
	String action = this.getAction();
	if (!(action.equals(""))) {
	    Task task = new Task(action);
	    try {
		List<Object> completeMethod = task.getMethod();
		synchronized (world) {
		    if (completeMethod.size() == 1) {
			((Method) completeMethod.get(0)).invoke(this);
		    } else {
			((Method) completeMethod.get(0)).invoke(this,
				completeMethod.get(1));
		    }
		}
	    } catch (NullPointerException e) {
		System.out.println("Did your entity exists ?");
	    } catch (InvocationTargetException | ClassNotFoundException
		    | IllegalAccessException | IllegalArgumentException e) {
		UI userInterface = new UI(this.world);
		userInterface.displayException(e.getMessage());
		userInterface.displayMethodError();
	    }
	}
    }

    @Override
    public String toString() {
	String result = super.toString() + " Item :";
	for(Item i: this.getInventory()){
	    result += i;
	}
	return result;
    }

    private String getAction() {
	String result;
	UI userInterface = new UI(this.world);
	userInterface.displayQuestion();
	userInterface.displayPossibilities();
	result = userInterface.getUserAction();
	return result;
    }
}
