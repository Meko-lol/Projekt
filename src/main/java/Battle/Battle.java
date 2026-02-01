package Battle;

import Characters.Character;
import Characters.Player;
import Items.Weapons.CloseRangeWeapon;
import java.util.Random;

public class Battle {
    private Player player;
    private Character opponent;
    private Random random = new Random();

    public Battle(Player player, Character opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    /**
     * Starts and manages a simple, turn-based battle.
     * @return True if the player was defeated, false otherwise.
     */
    public boolean start() {
        System.out.println("\n--- BATTLE START ---");
        System.out.println("You are fighting a " + opponent.getName() + "!");

        while (player.getHealth() > 0 && opponent.getHealth() > 0) {
            // --- Player's Turn ---
            int playerBaseDamage = player.getStrength();
            CloseRangeWeapon weapon = player.getEquippedWeapon();
            if (weapon != null) {
                playerBaseDamage += weapon.getDamage();
            }
            
            int playerDamage = playerBaseDamage + random.nextInt(5);
            opponent.setHealth(opponent.getHealth() - playerDamage);
            System.out.println("You hit the " + opponent.getName() + " for " + playerDamage + " damage. (Opponent's Health: " + String.format("%.0f", opponent.getHealth()) + ")");

            if (opponent.getHealth() <= 0) {
                System.out.println("--- You defeated the " + opponent.getName() + "! ---");
                return false; // Player won
            }

            // --- Opponent's Turn ---
            int opponentDamage = opponent.getStrength() + random.nextInt(3);
            player.setHealth(player.getHealth() - opponentDamage);
            System.out.println("The " + opponent.getName() + " hits you for " + opponentDamage + " damage. (Your Health: " + String.format("%.0f", player.getHealth()) + ")");

            if (player.getHealth() <= 0) {
                System.out.println("--- You have been defeated! ---");
                return true; // Player was defeated
            }
        }
        return false;
    }
}
