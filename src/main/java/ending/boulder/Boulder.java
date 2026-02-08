package ending.boulder;

import Characters.Player;
import Items.Weapons.CloseRangeWeapon;
import com.fasterxml.jackson.annotation.JsonIgnore; // Import JsonIgnore

public class Boulder {
    private int boulderHealth;

    public Boulder() {} // No-arg constructor for JSON

    public Boulder(int initialHealth) {
        this.boulderHealth = initialHealth;
    }

    public void attemptToBreakBoulder(Player player) {
        System.out.println("\n--- THE FINAL OBSTACLE ---");
        System.out.println("A massive magical boulder blocks the light. It has " + boulderHealth + " HP.");
        
        int damage = player.getStrength();
        CloseRangeWeapon weapon = player.getEquippedWeapon();
        if (weapon != null) {
            damage += weapon.getDamage();
            System.out.println("You smash it with your " + weapon.getName() + "!");
        } else {
            System.out.println("You hit it with your fists.");
        }
        
        boulderHealth -= damage;
        
        if (boulderHealth <= 0) {
            System.out.println("With a thunderous crash, the boulder crumbles! The exit is open!");
        } else {
            System.out.println("Chips of stone fly, but the boulder remains. (HP: " + boulderHealth + ")");
        }
    }

    public int getBoulderHealth() {
        return boulderHealth;
    }
    
    // THE FIX: Ignore this computed property during serialization
    @JsonIgnore
    public boolean isBroken() {
        return boulderHealth <= 0;
    }
}
