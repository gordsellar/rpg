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
            System.out.println("task.getAction() = " + task.getAction());
            try {
                List<Object> completeMethod = task.getMethod();
                System.out.println("task.getMethod()= " + completeMethod);
                System.out.println("size " + completeMethod.size());
                if(completeMethod.size() == 1 ) {
                    ((Method) completeMethod.get(0)).invoke(this);
                }
                else {
                    ((Method) completeMethod.get(0)).invoke(this, completeMethod.get(1));
                }
            } catch (InvocationTargetException e) {
                System.out.println("InvocationTargetException");
                System.out.println("e.getMessage() = " + e.getMessage());
                System.out.println("e.getStackTrace() = " + e.getStackTrace());

                UI userInterface = new UI(this.world);
                userInterface.displayMethodError();
            }
            catch (IllegalAccessException e) {
                System.out.println("IllegalAccessException");
                System.out.println("e.getMessage() = " + e.getMessage());
                System.out.println("e.getStackTrace() = " + e.getStackTrace());

                UI userInterface = new UI(this.world);
                userInterface.displayMethodError();
            }
            catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException");
                System.out.println("e.getMessage() = " + e.getMessage());
                System.out.println("e.getStackTrace() = " + e.getStackTrace());

                UI userInterface = new UI(this.world);
                userInterface.displayMethodError();
            }
        }
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
