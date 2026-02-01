package LoaderOfData;

import Interact.Node; // Corrected import
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadInteracts {

    public static void loadData(MyFileManager fileManager) {
        // Load the JSON data into the correct list in the file manager
        List<Node> loadedList = fileManager.loadListFromFile("Source/Interacts.json", Node[].class);
        fileManager.interacts.addAll(loadedList);
    }

    // This method is no longer needed here, as the logic is in MyFileManager
    // public static Node getByName(MyFileManager fileManager, String name) { ... }
}
