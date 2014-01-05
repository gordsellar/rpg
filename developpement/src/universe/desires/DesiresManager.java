package universe.desires;

import java.util.ArrayList;
import java.util.List;

public class DesiresManager {

    private List<Objective> objectives = new ArrayList<>();

    public void addDesire(Objective objective) {
        objectives.add(objective);
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    @Override
    public String toString() {
	String result= "";
	for(Objective o : this.objectives){
	    result += o + ", ";
	}
	return result;
    }
}
