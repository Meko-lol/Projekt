package LoaderOfData;

import Quest.Quest;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadQuests {

    public static void loadData(MyFileManager fileManager) {
        List<Quest> loadedList = fileManager.loadListFromFile("Source/Quests.json", Quest.class);
        fileManager.quests.addAll(loadedList);
    }

    public static Quest getByName(MyFileManager fileManager, String name) {
        if (fileManager.quests == null || fileManager.quests.isEmpty()) {
            loadData(fileManager);
        }
        for (Quest quest : fileManager.quests) {
            if (quest != null && quest.getName().equalsIgnoreCase(name)) { // Assuming Quest has a 'name' field
                return quest;
            }
        }
        return null;
    }
}
