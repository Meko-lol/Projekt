package Items.Weapons;

import Items.Item; // Import Item directly

public class RangedWeapon extends Item { // Extend Item directly
    private int damage;
    private int range;

    public RangedWeapon(String name, double weight, double durability, String description, int damage, int range) {
        super(name, "weapon", weight, durability, description);
        this.damage = damage;
        this.range = range;
    }

    public RangedWeapon() {
        super();
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
