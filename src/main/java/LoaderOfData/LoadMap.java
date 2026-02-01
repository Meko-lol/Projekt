package LoaderOfData;

import GameMap.MyMap;
import MyFileManager.MyFileManager;

public class LoadMap {

    /**
     * This static method loads the map.
     * It uses the file manager's tools to get the map object from the file.
     * @param fileManager The file manager that knows how to read files.
     * @return The loaded MyMap object.
     */
    public static MyMap load(MyFileManager fileManager) {
        return fileManager.loadFromFile("Source/map.json", MyMap.class);
    }
}
