package LoaderOfData;

import Places.Place;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadPlaces {

    public static void loadData(MyFileManager fileManager) {
        List<Place> loadedList = fileManager.loadListFromFile("Source/Places.json", Place.class);
        fileManager.places.addAll(loadedList);
    }

    public static Place getByName(MyFileManager fileManager, String name) {
        if (fileManager.places == null || fileManager.places.isEmpty()) {
            loadData(fileManager);
        }
        for (Place place : fileManager.places) {
            if (place != null && place.getName().equalsIgnoreCase(name)) {
                return place;
            }
        }
        return null;
    }
}
