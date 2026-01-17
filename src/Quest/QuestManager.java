package Quest;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {
    private List<Quest> activeQuests;

    public QuestManager() {
        this.activeQuests = new ArrayList<>();
    }

    public void addQuest(Quest quest) {
        if (quest != null && !quest.isCompleted) {
            this.activeQuests.add(quest);
        }
    }

    public void removeQuest(Quest quest) {
        this.activeQuests.remove(quest);
    }

    public List<Quest> getActiveQuests() {
        return activeQuests;
    }

    public void updateQuests() {
    }
}
