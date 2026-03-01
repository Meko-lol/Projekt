package cz.meko.Items.EquippableItems;

public class Pants extends EquippableItem {
    private double defense;

    public Pants(String name, double weight, double durability, String description, double defense) {
        super(name, "equippable", weight, durability, description, "legs");
        this.defense = defense;
    }

    public Pants() {
        super();
    }
}
