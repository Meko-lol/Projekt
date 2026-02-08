package Game;

import Interact.Node;
import Items.Item;
import Places.Obstacle;
import Quest.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    
    private static final String HISTORY_FILE = "command_history.txt";
    private static final String SAVE_FILE = "Source/game.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // --- Data Loading using simple Scanner ---
    public static List<String> loadNames() {
        List<String> names = new ArrayList<>();
        try {
            File file = new File("Source/names.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                names.add(line);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading names: " + e.getMessage());
        }
        return names;
    }

    // For JSON loading, ObjectMapper is standard, but we can wrap it simply.
    public static List<Quest> loadQuests() {
        try {
            Quest[] array = objectMapper.readValue(new File("Source/quests.json"), Quest[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading quests.");
            return new ArrayList<>();
        }
    }

    public static List<Item> loadItems() {
        try {
            Item[] array = objectMapper.readValue(new File("Source/items.json"), Item[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading items.");
            return new ArrayList<>();
        }
    }

    public static List<Node> loadDialogues() {
        try {
            Node[] array = objectMapper.readValue(new File("Source/interacts.json"), Node[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading dialogues.");
            return new ArrayList<>();
        }
    }

    public static List<Obstacle> loadObstacles() {
        try {
            Obstacle[] array = objectMapper.readValue(new File("Source/obstacles.json"), Obstacle[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading obstacles.");
            return new ArrayList<>();
        }
    }

    // --- Game Save/Load ---
    public static void saveGame(MyGame game) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(SAVE_FILE), game);
            System.out.println("Game saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public static MyGame loadGame() {
        try {
            return objectMapper.readValue(new File(SAVE_FILE), MyGame.class);
        } catch (Exception e) {
            System.out.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }

    // --- Command History using FileWriter/PrintWriter ---
    public static void saveCommandToHistory(String command) {
        try {
            FileWriter fw = new FileWriter(HISTORY_FILE, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(command);
            pw.close();
        } catch (IOException e) {
            // Ignore
        }
    }

    public static void resetCommandHistory() {
        try {
            FileWriter fw = new FileWriter(HISTORY_FILE, false);
            fw.close();
        } catch (IOException e) {
            // Ignore
        }
    }
}
