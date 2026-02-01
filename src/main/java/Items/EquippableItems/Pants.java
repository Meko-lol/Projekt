package Items.EquippableItems;

import Items.EquippableItems.EquippableItem;

public class Pants extends EquippableItem {
    private double defense;

    public Pants(String name, double weight, double durability, String description, double defense) {
        super(name, "equippable", weight, durability, description, "legs");
        this.defense = defense;
    }

    public Pants() {
        super();
    }
    
    // The equip() and unequip() methods have been REMOVED.
}
