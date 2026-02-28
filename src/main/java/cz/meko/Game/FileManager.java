package cz.meko.Game;

import cz.meko.Interact.Node;
import cz.meko.Items.Item;
import cz.meko.Places.Obstacle;
import cz.meko.Quest.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.*;

public class FileManager {

    private static final String HISTORY_FILE = "command_history.txt";
    private static final String SAVE_FILE = "game.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static InputStream getResource(String name) {
        return FileManager.class.getClassLoader().getResourceAsStream(name);
    }

    // --- Load Names ---
    public static List<String> loadNames() {
        List<String> names = new ArrayList<>();

        try (InputStream is = getResource("names.txt");
             Scanner scanner = new Scanner(is)) {

            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }

        } catch (Exception e) {
            System.out.println("Error loading names: " + e.getMessage());
        }

        return names;
    }

    // --- JSON Loading ---
    public static List<Quest> loadQuests() {
        try (InputStream is = getResource("quests.json")) {
            Quest[] array = objectMapper.readValue(is, Quest[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading quests.");
            return new ArrayList<>();
        }
    }

    public static List<Item> loadItems() {
        try (InputStream is = getResource("items.json")) {
            Item[] array = objectMapper.readValue(is, Item[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading items.");
            return new ArrayList<>();
        }
    }

    public static List<Node> loadDialogues() {
        try (InputStream is = getResource("interacts.json")) {
            Node[] array = objectMapper.readValue(is, Node[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading dialogues.");
            return new ArrayList<>();
        }
    }

    public static List<Obstacle> loadObstacles() {
        try (InputStream is = getResource("obstacles.json")) {
            Obstacle[] array = objectMapper.readValue(is, Obstacle[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) {
            System.out.println("Error loading obstacles.");
            return new ArrayList<>();
        }
    }

    // --- Save/Load Game ---
    // NOTE: Saving CANNOT go into resources (resources are read-only in JAR)
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

    // --- Command History ---
    public static void saveCommandToHistory(String command) {
        try (FileWriter fw = new FileWriter(HISTORY_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(command);

        } catch (IOException ignored) {}
    }

    public static void resetCommandHistory() {
        try (FileWriter fw = new FileWriter(HISTORY_FILE, false)) {
            // overwrite file
        } catch (IOException ignored) {}
    }
}
