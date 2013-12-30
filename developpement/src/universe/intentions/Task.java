package universe.intentions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import universe.Position;
import universe.Zone;
import universe.entities.NPC;

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
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
    public List<Object> getMethod() throws NoSuchMethodException,
    ClassNotFoundException {
        List<Object> completeMethod = new ArrayList<>();

        String[] components = action.split(";");
        String methodName = components[0];
        Class<?>[] parametersTypes = new Class[components.length - 1];
        List<Object> parameters = new ArrayList<>();
        for (int i = 1; i < components.length; i++) {
            String param = components[i];
            // "Position [x=1, y=2]"
            String[] splitParam = param.split(" ", 2);
            // { "Position", "[x=1, y=2]" }
            switch (splitParam[0]) {
            case "Character":
                parametersTypes[i - 1] = Character.class;
                // TODO look up to retrieve the right Character
                break;
            case "Position":
                String constructParam = splitParam[1];
                Pattern p = Pattern.compile("\\[x=(\\d+), y=(\\d+)\\]");
                Matcher m = p.matcher(constructParam);
                if (m.matches()) {
                    parametersTypes[i - 1] = Position.class;
                    int x = Integer.valueOf(m.group(1));
                    int y = Integer.valueOf(m.group(2));

                    parameters.add(new Position(x, y));
                }
                break;
            case "Object":
                parametersTypes[i - 1] = universe.entities.Item.class;
                // TODO look up to retrieve the right Object
                break;
            case "Zone":
                parametersTypes[i - 1] = Zone.class;
                // TODO create a Zone fromString
                break;
            }
        }

        Method method = NPC.class.getMethod(methodName, parametersTypes);
        completeMethod.add(method);
        completeMethod.addAll(parameters);

        return completeMethod;
    }

    public static void main(String[] args) {
        Task t = new Task("move;Position [x=3, y=5]");

        try {
            List<Object> completeMethod = t.getMethod();

            NPC c = new NPC("Azu", 0, 5);
            System.out.println("Appel");
            ((Method) completeMethod.get(0)).invoke(c, completeMethod.get(1));
            System.out.println("Pas d'erreur.");
        } catch (NoSuchMethodException | ClassNotFoundException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
