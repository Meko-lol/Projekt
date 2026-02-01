package Characters;

import Inventory.Inventory; // Import the Inventory class

public class Player extends Character {

    public Inventory inventory; // The player's inventory

    // No-argument constructor for Jackson
    public Player() {
        super();
        this.inventory = new Inventory();
    }

    public Player(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
        this.inventory = new Inventory(); // Create a new inventory for the player
    }

    public Inventory getInventory() {
        return inventory;
    }
}
