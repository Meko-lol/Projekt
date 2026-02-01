package Items.Weapons;

import Items.Item; // Import Item directly

public class CloseRangeWeapon extends Item { // Extend Item directly
    private int damage;

    public CloseRangeWeapon(String name, double weight, double durability, String description, int damage) {
        super(name, "weapon", weight, durability, description);
        this.damage = damage;
    }

    public CloseRangeWeapon() {
        super();
    }

    public int getDamage() {
        return damage;
    }
}
