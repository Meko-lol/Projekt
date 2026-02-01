package Items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    protected String name;
    protected String type; // Added for identifying item's purpose
    protected double weight;
    protected double durability;
    protected String description;

    public Item() {
    }

    public Item(String name, double weight, double durability, String description) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.durability = durability;
        this.description = description;
    }

    // Copy constructor
    public Item(Item other) {
        this.name = other.name;
        this.type = other.type;
        this.weight = other.weight;
        this.durability = other.durability;
        this.description = other.description;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getDurability() { return durability; }
    public void setDurability(double durability) { this.durability = durability; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
