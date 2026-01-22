package MyFileManager;

import Characters.Character;
import Items.Item;
import Places.Place;
import Quest.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Import loader classes
import LoaderOfData.LoadCharacters;
import LoaderOfData.LoadItems;
import LoaderOfData.LoadPlaces;
import LoaderOfData.LoadQuests;

public class MyFileManager {

    private ObjectMapper objectMapper;
    
    public List<Character> characters;
    public List<Place> places;
    public List<Item> items;
    public List<Quest> quests;

    public MyFileManager() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        this.characters = new ArrayList<>();
        this.places = new ArrayList<>();
        this.items = new ArrayList<>();
        this.quests = new ArrayList<>();
    }

    /**
     * Calls all the individual data loaders to populate the lists in this manager.
     * This should be called once when the game starts.
     */
    public void loadAllData() {
        LoadCharacters.loadData(this);
        LoadPlaces.loadData(this);
        LoadItems.loadData(this);
        LoadQuests.loadData(this);
    }

    public void saveToFile(String filePath, Object objectToSave) {
        try {
            String jsonString = objectMapper.writeValueAsString(objectToSave);
            Files.write(Paths.get(filePath), jsonString.getBytes());
        } catch (Exception e) {
            //TODO
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

    // --- Getters and Setters ---
    public List<Character> getCharacters() { return characters; }
    public void setCharacters(List<Character> characters) { this.characters = characters; }
    public List<Place> getPlaces() { return places; }
    public void setPlaces(List<Place> places) { this.places = places; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public List<Quest> getQuests() { return quests; }
    public void setQuests(List<Quest> quests) { this.quests = quests; }

    // --- Get by Name methods ---
    public Character getCharacterByName(String name) {
        for (Character character : characters) {
            if (character != null && character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }

    public Place getPlaceByName(String name) {
        for (Place place : places) {
            if (place != null && place.getName().equalsIgnoreCase(name)) {
                return place;
            }
        }
        return null;
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item != null && item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Quest getQuestByName(String name) {
        for (Quest quest : quests) {
            if (quest != null && quest.getName().equalsIgnoreCase(name)) {
                return quest;
            }
        }
        return null;
    }
}
