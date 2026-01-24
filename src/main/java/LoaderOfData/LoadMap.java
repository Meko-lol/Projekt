package LoaderOfData;

import GameMap.MyMap;
import MyFileManager.MyFileManager;

public class LoadMap {

    public static MyMap load(MyFileManager fileManager) {
        return fileManager.loadFromFile("Source/map.json", MyMap.class);
    }
}
