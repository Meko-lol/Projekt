package MyFileManager;

import Characters.Character;
import Items.Item;
import Places.Location;
import Quest.Quest;
import Interact.Node; // Corrected import
import GameMap.MyMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Import loader classes
import LoaderOfData.LoadCharacters;
import LoaderOfData.LoadItems;
import LoaderOfData.LoadLocations;
import LoaderOfData.LoadQuests;
import LoaderOfData.LoadInteracts;
import LoaderOfData.LoadMap;

public class MyFileManager {

    private ObjectMapper objectMapper;

    // --- Data Storage ---
    public List<Character> characters;
    public List<Location> locations;
    public List<Item> items;
    public List<Quest> quests;
    public List<Node> interacts; // Changed to Node
    public MyMap gameMap;

    public MyFileManager() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.characters = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.items = new ArrayList<>();
        this.quests = new ArrayList<>();
        this.interacts = new ArrayList<>();
        this.gameMap = null;
    }

    public void loadAllData() {
        LoadCharacters.loadData(this);
        LoadLocations.loadData(this);
        LoadItems.loadData(this);
        LoadQuests.loadData(this);
        LoadInteracts.loadData(this);
        this.gameMap = LoadMap.load(this);
    }

    public void saveToFile(String filePath, Object objectToSave) {
        try {
            String jsonString = objectMapper.writeValueAsString(objectToSave);
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (Exception e) {
            //TODO
        }
    }

    public <T> T loadFromFile(String filePath, Class<T> classOfObject) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            return objectMapper.readValue(jsonString, classOfObject);
        } catch (Exception e) {
            //TODO
            return null;
        }
    }

    public <T> List<T> loadListFromFile(String filePath, Class<T[]> classOfArray) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(jsonString, classOfArray)));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // --- Getters and Setters ---
    public List<Character> getCharacters() { return characters; }
    public void setCharacters(List<Character> characters) { this.characters = characters; }
    public Character getCharacterByName(String name) {
        for (Character character : characters) {
            if (character != null && character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }

    public List<Location> getLocations() { return locations; }
    public void setLocations(List<Location> locations) { this.locations = locations; }
    public Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location != null && location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item != null && item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public List<Quest> getQuests() { return quests; }
    public void setQuests(List<Quest> quests) { this.quests = quests; }
    public Quest getQuestByName(String name) {
        for (Quest quest : quests) {
            if (quest != null && quest.getName().equalsIgnoreCase(name)) {
                return quest;
            }
        }
        return null;
    }

    public List<Node> getInteracts() { return interacts; }
    public void setInteracts(List<Node> interacts) { this.interacts = interacts; }
    public Node getInteractByName(String name) { // Changed return type
        for (Node interact : interacts) {
            if (interact != null && interact.getName().equalsIgnoreCase(name)) {
                return interact;
            }
        }
        return null;
    }

    public MyMap getGameMap() {
        return gameMap;
    }
}
