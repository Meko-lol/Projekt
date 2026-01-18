package Items.EquippableItems;

import Items.Item;
import java.util.ArrayList;
import java.util.List;

public class Backpack extends EquippableItem {
    private double capacity;
    private List<Item> items;

    public Backpack(String name, double weight, double durability, String description, double capacity) {
        super(name, weight, durability, description);
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    @Override
    public String equip() {
        // Logic to equip the backpack
        return "The " + getName() + " was equipped.";
    }

    @Override
    public String unequip() {
        // Logic to unequip the backpack
        return "The " + getName() + " was unequipped.";
    }

    public boolean addItem(Item item) { return false; }
    public boolean removeItem(Item item) { return false; }
    public double getCurrentWeight() { return 0; }
    public List<Item> getItems() { return null; }
}
