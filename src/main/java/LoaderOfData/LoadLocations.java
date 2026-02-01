package LoaderOfData;

import Places.Location;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadLocations {

    public static void loadData(MyFileManager fileManager) {
        List<Location> loadedList = fileManager.loadListFromFile("Source/Locations.json", Location[].class);
        fileManager.locations.addAll(loadedList);
    }

    public static Location getByName(MyFileManager fileManager, String name) {
        if (fileManager.locations == null || fileManager.locations.isEmpty()) {
            loadData(fileManager);
        }
        for (Location location : fileManager.locations) {
            if (location != null && location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }
}
