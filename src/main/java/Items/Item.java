package Items;

import Items.EquippableItems.Backpack;
import Items.EquippableItems.Pants;
import Items.Items.EquippableItems.Boots;
import Items.Items.EquippableItems.Chestplate;
import Items.Items.EquippableItems.Helmet;
import Items.Weapons.CloseRangeWeapon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "itemType", defaultImpl = Item.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Item.class, name = "item"),
    @JsonSubTypes.Type(value = CloseRangeWeapon.class, name = "closeRangeWeapon"),
    @JsonSubTypes.Type(value = Helmet.class, name = "helmet"),
    @JsonSubTypes.Type(value = Chestplate.class, name = "chestplate"),
    @JsonSubTypes.Type(value = Pants.class, name = "pants"),
    @JsonSubTypes.Type(value = Boots.class, name = "boots"),
    @JsonSubTypes.Type(value = Backpack.class, name = "backpack")
})
public class Item {
    public String name;
    public String type;
    public double weight;
    public double durability;
    public String description;

    public Item() {}

    public Item(String name, String type, double weight, double durability, String description) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.durability = durability;
        this.description = description;
    }

    // THE FIX: Add the standard public getter methods back to the class.
    public String getName() { return name; }
    public String getType() { return type; }
    public double getWeight() { return weight; }
    public double getDurability() { return durability; }
    public String getDescription() { return description; }
}
