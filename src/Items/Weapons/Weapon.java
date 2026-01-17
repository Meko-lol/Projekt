package Items.Weapons;

import Items.Item;

public abstract class Weapon extends Item {
    public Weapon(String name, double weight, double durability, String description) {
        super(name, weight, durability, description);
    }
}
