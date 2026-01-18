package Items.EquippableItems;

public class Pants extends EquippableItem {
    private double defense;

    public Pants(String name, double weight, double durability, String description, double defense) {
        super(name, weight, durability, description);
        this.defense = defense;
    }

    @Override
    public String equip() {
        // Logic to equip pants
        return "The " + getName() + " was equipped.";
    }

    @Override
    public String unequip() {
        // Logic to unequip pants
        return "The " + getName() + " was unequipped.";
    }
}
