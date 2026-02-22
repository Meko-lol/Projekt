package Items.Items.EquippableItems;

import Items.EquippableItems.EquippableItem;

public class Boots extends EquippableItem {
    private double speedBonus;

    public Boots(String name, double weight, double durability, String description, double speedBonus) {
        super(name, "equippable", weight, durability, description, "feet");
        this.speedBonus = speedBonus;
    }

    public Boots() {
        super();
    }
    
    // The equip() and unequip() methods have been REMOVED.
}
