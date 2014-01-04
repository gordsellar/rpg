package universe.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import universe.Position;
import universe.Zone;
import universe.entities.Entity;

/**
 * This class is to be used each time an object must be found or created. It
 * keeps track of every Entity objects.
 * 
 * @author sguingoin
 * 
 */
public class DatabaseManager {

    private static List<Entity> entities = new ArrayList<>();

    /**
     * Creates a new instance of a sub-class of Entity, using given parameters.
     * 
     * @param className
     *            The class to be instantiated
     * @param parameters
     *            The parameters for the constructor
     * @return The new instance
     */
    public static Entity create(Class<? extends Entity> className,
            Object... parameters) {
        Class<?>[] parametersTypes = new Class<?>[parameters.length];
        int i = 0;
        for (Object object : parameters) {
            parametersTypes[i] = object.getClass();
            i++;
        }

        Constructor<? extends Entity> constructor;
        Entity entity = null;
        try {
            constructor = className.getConstructor(parametersTypes);
            entity = constructor.newInstance(parameters);
            entity.setId(entities.size());
            entities.add(entity);

        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return entity;
    }

    /**
     * Retrieves or creates a new instance of a class by decoding the given
     * String representation. Does the opposite of the toString method.
     * 
     * @param inputString
     * @return The new instance
     */
    public static Object findFromString(String inputString) {
        String[] splitParam = inputString.split(" ", 2);
        Pattern p;
        Matcher m;
        switch (splitParam[0]) {
        case "Character":
            System.out.println("Character find not implemented");
            // TODO look up to retrieve the right Character
            break;
        case "Position":
            p = Pattern.compile("\\[x=(\\d+), y=(\\d+)\\]");
            m = p.matcher(splitParam[1]);
            if (m.matches()) {
                int x = Integer.valueOf(m.group(1));
                int y = Integer.valueOf(m.group(2));

                return new Position(x, y);
            }
            break;
        case "Item":
            p = Pattern.compile("\\[id=(\\d+)\\]");
            m = p.matcher(splitParam[1]);
            if(m.matches()) {
                return findById(Integer.valueOf(m.group(1)));
            }
            break;
        case "Zone":
            // TODO create a Zone fromString
            p = Pattern.compile("\\[position=(.+), radius=(\\d+)\\]");
            m = p.matcher(splitParam[1]);
            if (m.matches()) {
                Position position = (Position) findFromString(m.group(1));
                int radius = Integer.valueOf(m.group(2));

                return new Zone(position, radius);
            }
            break;
        }

        return null;
    }

    /**
     * Retrieve an Entity by its unique id.
     * 
     * @param id
     *            The id of an Entity
     * @return The Entity found
     */
    public static Entity findById(Integer id) {
        // TODO Exception id not found
        return entities.get(id);
    }

    /**
     * Retrieve an existing Entity using given attributes. If multiple
     * occurrences, returns the first found. Arrays must have the same size.
     * 
     * @param entityClass
     *            Class of the looked up Entity
     * 
     * @param attributes
     *            The list of attributes to match
     * @param values
     *            The values for each attribute
     * @return The first Entity found
     */
    public static Entity findBy(Class<? extends Entity> entityClass,
            String[] attributes, String[] values) {
        for (Entity entity : entities) {
            if (entityClass.isInstance(entity)) {
                Boolean found = true;
                for (int i = 0; i < attributes.length; i++) {
                    String attribute = Character.toUpperCase(attributes[i]
                            .charAt(0)) + attributes[i].substring(1);
                    String value = values[i];
                    try {
                        Method getter = entity.getClass().getMethod(
                                "get" + attribute);
                        found &= (getter.invoke(entity).toString()
                                .equals(value));
                    } catch (NoSuchMethodException e) {
                        try {
                            Method getter;
                            getter = entity.getClass().getMethod(
                                    "is" + attribute);

                            found &= (getter.invoke(entity).toString()
                                    .equals(value));
                        } catch (NoSuchMethodException | IllegalAccessException
                                | IllegalArgumentException
                                | InvocationTargetException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } catch (IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (found) {
                    return entity;
                }
            }
        }
        // TODO Exception no Entity found
        return null;
    }

    public static void emptyEntities() {
        entities.clear();

    }

    public static List<Entity> getEntities() {
        return entities;
    }
}
