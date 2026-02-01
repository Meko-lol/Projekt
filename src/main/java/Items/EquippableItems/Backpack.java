package Items.EquippableItems;

import Items.Item;
import java.util.ArrayList;
import java.util.List;

public class Backpack extends EquippableItem {
    private double capacity;
    private List<Item> items;

    public Backpack(String name, double weight, double durability, String description, double capacity) {
        super(name, "equippable", weight, durability, description, "backpack");
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public Backpack() {
        super();
        this.items = new ArrayList<>();
    }
    
    // The equip() and unequip() methods have been REMOVED.
}
