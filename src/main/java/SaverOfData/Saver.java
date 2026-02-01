package SaverOfData;

import MyFileManager.MyFileManager;

public class Saver {
    MyFileManager myFileManager = new MyFileManager();

    /**
     * Saves the provided game state object to the specified file.
     * @param gameState The object containing all data to be saved.
     * @param filePath The path to the save file.
     */
    public void save(Object gameState, String filePath) {
        myFileManager.saveToFile(filePath, gameState);
    }
}
