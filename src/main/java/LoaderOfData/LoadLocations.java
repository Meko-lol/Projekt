package LoaderOfData;

import Places.Location; // Changed from Places.Place
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadLocations { // Renamed from LoadPlaces

    public static void loadData(MyFileManager fileManager) {
        List<Location> loadedList = fileManager.loadListFromFile("Source/Locations.json", Location.class); // Changed to Location
        fileManager.locations.addAll(loadedList); // Changed to locations
    }

    public static Location getByName(MyFileManager fileManager, String name) { // Changed return type
        if (fileManager.locations == null || fileManager.locations.isEmpty()) { // Changed to locations
            loadData(fileManager);
        }
        for (Location location : fileManager.locations) { // Changed loop variable
            if (location != null && location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }
}
