package Items;

import java.util.ArrayList;
import java.util.List;

public class Backpack extends Item {
    private double capacity;
    private List<Item> items;

    public Backpack(String name, double weight, double durability, String description, double capacity) {
        super(name, weight, durability, description);
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) { return false; }
    public boolean removeItem(Item item) { return false; }
    public double getCurrentWeight() { return 0; }
    public List<Item> getItems() { return null; }
}
