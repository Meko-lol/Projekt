package Items.Items.EquippableItems;

import Items.EquippableItems.EquippableItem;

public class Helmet extends EquippableItem {
    private double defense;

    public Helmet(String name, double weight, double durability, String description, double defense) {
        super(name, "equippable", weight, durability, description, "head");
        this.defense = defense;
    }

    public Helmet() {
        super();
    }
    
    // The equip() and unequip() methods have been REMOVED.
}
