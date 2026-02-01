package Items.EquippableItems;

import Items.Item;

// This class is now just a simple data container. It has no methods that depend on Player.
public abstract class EquippableItem extends Item {
    private String slot;

    public EquippableItem() {
        super();
        this.type = "equippable";
    }

    public EquippableItem(String name, String type, double weight, double durability, String description, String slot) {
        super(name, type, weight, durability, description);
        this.slot = slot;
        this.type = "equippable";
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
    
    // The abstract equip() and unequip() methods have been REMOVED to break the circular dependency.
}
