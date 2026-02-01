package LoaderOfData;

import Items.Item;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadItems {

    public static void loadData(MyFileManager fileManager) {
        List<Item> loadedList = fileManager.loadListFromFile("Source/Items.json", Item[].class);
        fileManager.items.addAll(loadedList);
    }

    public static Item getByName(MyFileManager fileManager, String name) {
        if (fileManager.items == null || fileManager.items.isEmpty()) {
            loadData(fileManager);
        }
        for (Item item : fileManager.items) {
            if (item != null && item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
