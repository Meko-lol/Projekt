package Items;

import Items.EquippableItems.*;
import Items.Items.EquippableItems.Boots;
import Items.Items.EquippableItems.Chestplate;
import Items.Items.EquippableItems.Helmet;
import Items.Weapons.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "itemType")
@JsonSubTypes({
    // THE FIX: Register the base Item class with a type name.
    @JsonSubTypes.Type(value = Item.class, name = "item"),
    // List all the concrete subclasses.
    @JsonSubTypes.Type(value = Helmet.class, name = "helmet"),
    @JsonSubTypes.Type(value = Chestplate.class, name = "chestplate"),
    @JsonSubTypes.Type(value = Pants.class, name = "pants"),
    @JsonSubTypes.Type(value = Boots.class, name = "boots"),
    @JsonSubTypes.Type(value = Backpack.class, name = "backpack"),
    @JsonSubTypes.Type(value = CloseRangeWeapon.class, name = "closeRangeWeapon"),
    @JsonSubTypes.Type(value = RangedWeapon.class, name = "rangedWeapon")
})
public class Item {
    protected String name;
    protected String type;
    protected double weight;
    protected double durability;
    protected String description;

    public Item() {}

    public Item(String name, String type, double weight, double durability, String description) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.durability = durability;
        this.description = description;
    }

    // Getters and Setters
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
