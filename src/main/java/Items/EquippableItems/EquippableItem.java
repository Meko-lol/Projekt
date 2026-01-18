package Items.EquippableItems;

import Items.Item;

public abstract class EquippableItem extends Item {
    public EquippableItem(String name, double weight, double durability, String description) {
        super(name, weight, durability, description);
    }

    public abstract String equip();
    public abstract String unequip();
}
