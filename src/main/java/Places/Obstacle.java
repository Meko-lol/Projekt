package Places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Obstacle {
    private String name;
    private String description;
    private String requiredItemName;
    private int durability;

    public Obstacle() {}

    public Obstacle(String name, String description, String requiredItemName, int durability) {
        this.name = name;
        this.description = description;
        this.requiredItemName = requiredItemName;
        this.durability = durability;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getRequiredItemName() { return requiredItemName; }
    public int getDurability() { return durability; }
    public void decreaseDurability() { this.durability--; }
}
