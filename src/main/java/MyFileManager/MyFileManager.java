package MyFileManager;

import Characters.Character;
import Items.Item;
import Places.Location;
import Quest.Quest;
import InteractHandler.InteractHandler;
import GameMap.MyMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public List<InteractHandler> interacts;
    public MyMap gameMap; // Changed from List<MyMap>

    public MyFileManager() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        this.characters = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.items = new ArrayList<>();
        this.quests = new ArrayList<>();
        this.interacts = new ArrayList<>();
        this.gameMap = null; // Initialize as null
    }

    public void loadAllData() {
        LoadCharacters.loadData(this);
        LoadLocations.loadData(this);
        LoadItems.loadData(this);
        LoadQuests.loadData(this);
        LoadInteracts.loadData(this);
        this.gameMap = LoadMap.load(this); // Load the single map object
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

    public <T> List<T> loadListFromFile(String filePath, Class<T> classOfElement) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, classOfElement));
        } catch (Exception e) {
            //TODO
            return new ArrayList<>();
        }
    }

    // --- Character Data Access ---
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

    // --- Location Data Access ---
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

    // --- Item Data Access ---
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

    // --- Quest Data Access ---
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

     // --- Interact Data Access ---
    public List<InteractHandler> getInteracts() { return interacts; }
    public void setInteracts(List<InteractHandler> interacts) { this.interacts = interacts; }
    public InteractHandler getInteractByName(String name) {
        for (InteractHandler interact : interacts) {
            // Assuming Interact has a 'name' field or similar identifier
            //if (interact != null && interact.getName().equalsIgnoreCase(name)) {
            //    return interact;
            //}
        }
        return null;
    }

    // --- Map Data Access ---
    public MyMap getGameMap() {
        return gameMap;
    }
}
