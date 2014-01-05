package tests.universe.intentions;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import universe.entities.NPC;
import universe.intentions.Task;
import universe.utils.DatabaseManager;

public class TaskTest {

    @Test
    public void testGetMethod() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("move;Position [x=3, y=5]"));

        for (Task t : tasks) {
            try {
                List<Object> completeMethod = t.getMethod();

                NPC c = (NPC) DatabaseManager.create(NPC.class, "Azuryus", 5);
                ((Method) completeMethod.get(0)).invoke(c,
                        completeMethod.get(1));
            }
            catch (ClassNotFoundException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | SecurityException e) {
                e.printStackTrace();
                fail("Exception");
            }
        }
    }

}
