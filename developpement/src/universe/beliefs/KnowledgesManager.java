package universe.beliefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Class organizing knowledges of an Entity
 * 
 * @author sguingoin
 * 
 */
public class KnowledgesManager {
    List<Knowledge> knowledges = new ArrayList<>();

    /**
     * Add multiple Knowledges
     * 
     * @param newKnowledges
     */
    public void addKnowledges(List<Knowledge> newKnowledges) {
        for (Knowledge knowledge : newKnowledges) {
            addKnowledge(knowledge);
        }
    }

    /**
     * Add a new Knowledge, or update it if already known
     * 
     * @param newKnowledge
     */
    public void addKnowledge(Knowledge newKnowledge) {
        if (knowledges.contains(newKnowledge)) {
            int index = knowledges.indexOf(newKnowledge);
            if (knowledges.get(index).getAge() < newKnowledge.getAge()) {
                knowledges.set(index, newKnowledge);
            }
        }
        else {
            knowledges.add(newKnowledge);
        }
    }

    /**
     * @return all the Knowledges known
     */
    public List<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void removeKnowledge(Knowledge knowledge) {
        knowledges.remove(knowledge);
    }
}
