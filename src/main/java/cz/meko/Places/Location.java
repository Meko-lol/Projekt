package cz.meko.Places;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Items.Item;
import cz.meko.ending.boulder.Boulder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a single location on the game map.
 * Contains NPCs, items, and obstacles.
 * 
 * @author Jan Petrak
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name;
    private List<NPC> npcs = new ArrayList<>();
    private List<Item> itemsOnGround = new ArrayList<>();
    private Map<String, Obstacle> obstacles = new HashMap<>();
    private Map<String, Obstacle> clearedObstacles = new HashMap<>();
    private Boulder boulder;

    @JsonIgnore private int x;
    @JsonIgnore private int y;

    public Location() {}

    public Location(String name, List<NPC> npcs) {
        this.name = name;
        if (npcs != null) {
            this.npcs = npcs;
        }
    }

    // Getters
    public String getName() { return name; }
    public List<NPC> getNpcs() { return npcs; }
    public List<Item> getItemsOnGround() { return itemsOnGround; }
    public Map<String, Obstacle> getObstacles() { return obstacles; }
    public Obstacle getObstacle(String direction) { return obstacles.get(direction); }
    public Obstacle getClearedObstacle(String direction) { return clearedObstacles.get(direction); }
    public int getX() { return x; }
    public int getY() { return y; }
    public Boulder getBoulder() { return boulder; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setNpcs(List<NPC> npcs) { this.npcs = (npcs != null) ? npcs : new ArrayList<>(); }
    public void setItemsOnGround(List<Item> items) { this.itemsOnGround = (items != null) ? items : new ArrayList<>(); }
    public void setObstacles(Map<String, Obstacle> obstacles) { this.obstacles = (obstacles != null) ? obstacles : new HashMap<>(); }
    public void setClearedObstacles(Map<String, Obstacle> clearedObstacles) { this.clearedObstacles = (clearedObstacles != null) ? clearedObstacles : new HashMap<>(); }
    public void setBoulder(Boulder boulder) { this.boulder = boulder; }

    /**
     * Adds an item to the ground in this location.
     * @param item The item to add.
     */
    public void addItem(Item item) { this.itemsOnGround.add(item); }

    /**
     * Removes an item from the ground.
     * @param item The item to remove.
     */
    public void removeItem(Item item) { this.itemsOnGround.remove(item); }

    /**
     * Adds an NPC to this location.
     * @param npc The NPC to add.
     */
    public void addNpc(NPC npc) { this.npcs.add(npc); }

    /**
     * Removes an NPC from this location.
     * @param npc The NPC to remove.
     */
    public void removeNpc(NPC npc) { this.npcs.remove(npc); }

    /**
     * Sets an obstacle in a specific direction.
     * @param direction The direction of the obstacle.
     * @param obstacle The obstacle to set.
     */
    public void setObstacle(String direction, Obstacle obstacle) { this.obstacles.put(direction, obstacle); }
    
    /**
     * Removes an obstacle from a specific direction.
     * @param direction The direction of the obstacle to remove.
     */
    public void removeObstacle(String direction) {
        Obstacle cleared = this.obstacles.remove(direction);
        if (cleared != null) {
            this.clearedObstacles.put(direction, cleared);
        }
    }
}
