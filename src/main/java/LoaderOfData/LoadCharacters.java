package LoaderOfData;

import Characters.Character;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadCharacters {

    public static void loadData(MyFileManager fileManager) {
        // Load characters into MyFileManager's list
        List<Character> loadedList = fileManager.loadListFromFile("Source/Characters.json", Character.class);
        fileManager.characters.addAll(loadedList);
    }

    // getByName will now operate on the list inside MyFileManager
    public static Character getByName(MyFileManager fileManager, String name) {
        if (fileManager.characters == null || fileManager.characters.isEmpty()) {
            loadData(fileManager); // Ensure data is loaded if not already
        }
        for (Character character : fileManager.characters) {
            if (character != null && character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }
}
