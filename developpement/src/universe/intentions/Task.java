package universe.intentions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import universe.entities.NPC;
import universe.utils.DatabaseManager;

/**
 * Class representing an intention for a BDI system.
 * 
 * @author Sylvain
 * 
 */
public class Task {

    private String action;

    /**
     * Creates a new Task, with a given action.
     * 
     * @param action
     *            A method of an NPC followed by a toString representation of
     *            its parameters, all separated by a semicolon. <br>
     *            Example: "move;Position [x=5, y=10]"
     */
    public Task(String action) {
        this.action = action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    /**
     * Retrieves the method to be invoked on the NPC for a specific intention
     * 
     * @return List containing the method to be invoked, followed by its
     *         parameters (if any)
     * @throws ClassNotFoundException
     */
    public List<Object> getMethod() throws ClassNotFoundException {
        List<Object> completeMethod = new ArrayList<>();

        String[] components = action.split(";");
        String methodName = components[0];
        Class<?>[] parametersTypes = new Class[components.length - 1];
        List<Object> parameters = new ArrayList<>();
        for (int i = 1; i < components.length; i++) {
            String param = components[i];
            Object obj = DatabaseManager.findFromString(param);
            // TODO Exception if no object found
            parametersTypes[i - 1] = obj.getClass();
            parameters.add(obj);
        }

        Method method = null;
        try {
            method = NPC.class.getMethod(methodName, parametersTypes);
        }
        catch (NoSuchMethodException e) {
            System.out.println("method not found");
            Method[] methods = NPC.class.getMethods();
            int i = 0;
            boolean found = false;
            while (!found && i < methods.length) {

                if (methods[i].getName().equals(methodName)) {
                    method = methods[i];
                    found = true;
                }
                i++;
            }
        }

        completeMethod.add(method);
        completeMethod.addAll(parameters);

        return completeMethod;
    }

    @Override
    public String toString() {
        return "Task [action=" + action + "]";
    }
}
