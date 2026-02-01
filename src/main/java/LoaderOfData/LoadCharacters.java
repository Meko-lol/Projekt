package LoaderOfData;

import Characters.Character;
import MyFileManager.MyFileManager;
import java.util.List;

public class LoadCharacters {

    public static void loadData(MyFileManager fileManager) {
        // Pass Character[].class which is simpler to understand
        List<Character> loadedList = fileManager.loadListFromFile("Source/Characters.json", Character[].class);
        fileManager.characters.addAll(loadedList);
    }

    public static Character getByName(MyFileManager fileManager, String name) {
        if (fileManager.characters == null || fileManager.characters.isEmpty()) {
            loadData(fileManager);
        }
        for (Character character : fileManager.characters) {
            if (character != null && character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }
}
