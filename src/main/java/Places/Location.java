package Places;

import Characters.NPCs.NPC;
import Items.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name;
    private String[] obstacles;
    private NPC[] npcs;
    private Map<String, Integer> resources;
    private List<Item> itemsOnGround; // Added list for items

    public Location() {
        this.itemsOnGround = new ArrayList<>();
    }

    public Location(String name, String[] obstacles, NPC[] npcs, Map<String, Integer> resources) {
        this.name = name;
        this.obstacles = obstacles;
        this.npcs = npcs;
        this.resources = resources;
        this.itemsOnGround = new ArrayList<>();
    }

    // --- Getters and Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String[] getObstacles() { return obstacles; }
    public void setObstacles(String[] obstacles) { this.obstacles = obstacles; }
    public NPC[] getNpcs() { return npcs; }
    public void setNpcs(NPC[] npcs) { this.npcs = npcs; }
    public Map<String, Integer> getResources() { return resources; }
    public void setResources(Map<String, Integer> resources) { this.resources = resources; }
    
    // --- Item Management ---
    public List<Item> getItemsOnGround() { return itemsOnGround; }
    public void addItem(Item item) { this.itemsOnGround.add(item); }
    public void removeItem(Item item) { this.itemsOnGround.remove(item); }
}
