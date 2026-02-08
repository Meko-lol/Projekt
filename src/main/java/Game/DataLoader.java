package Game;

import Interact.Node;
import Items.Item;
import Places.Obstacle;
import Quest.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    
    public static List<String> loadNames() {
        try {
            return Files.readAllLines(Paths.get("Source/names.txt"));
        } catch (Exception e) {
            System.out.println("Error loading names: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Quest> loadQuests() {
        return loadList("Source/quests.json", Quest[].class);
    }

    public static List<Item> loadItems() {
        return loadList("Source/items.json", Item[].class);
    }

    public static List<Node> loadDialogues() {
        return loadList("Source/interacts.json", Node[].class);
    }

    public static List<Obstacle> loadObstacles() {
        return loadList("Source/obstacles.json", Obstacle[].class);
    }

    private static <T> List<T> loadList(String path, Class<T[]> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(new File(path), clazz)));
        } catch (Exception e) {
            System.out.println("Error loading " + path + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
