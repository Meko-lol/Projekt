package LoaderOfData;

import InteractHandler.InteractHandler;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadInteracts {

    public static void loadData(MyFileManager fileManager) {
        List<InteractHandler> loadedList = fileManager.loadListFromFile("Source/Interacts.json", InteractHandler.class);
        fileManager.interacts.addAll(loadedList);
    }

    public static InteractHandler getByName(MyFileManager fileManager, String name) {
        if (fileManager.interacts == null || fileManager.interacts.isEmpty()) {
            loadData(fileManager);
        }
        for (InteractHandler interact : fileManager.interacts) {
            // Assuming Interact has a 'name' field or similar identifier
            // if (interact != null && interact.getName().equalsIgnoreCase(name)) {
            //     return interact;
            // }
        }
        return null;
    }
}
