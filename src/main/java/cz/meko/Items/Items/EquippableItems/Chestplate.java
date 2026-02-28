package cz.meko.Items.Items.EquippableItems;

import cz.meko.Items.EquippableItems.EquippableItem;

public class Chestplate extends EquippableItem {
    private double defense;

    public Chestplate(String name, double weight, double durability, String description, double defense) {
        super(name, "equippable", weight, durability, description, "chest");
        this.defense = defense;
    }

    public Chestplate() {
        super();
    }
    
    // The equip() and unequip() methods have been REMOVED.
}
