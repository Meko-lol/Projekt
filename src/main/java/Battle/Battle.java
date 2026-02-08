package Battle;

import Characters.Character;
import Characters.Player;
import Commands.commandList.DevCommand; // Import DevCommand
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

    public boolean start() {
        System.out.println("\n--- BATTLE START ---");
        System.out.println("You are fighting a " + opponent.getName() + "!");
        wait(500);

        while (player.getHealth() > 0 && opponent.getHealth() > 0) {
            // --- Player's Turn ---
            int playerBaseDamage = player.getStrength();
            CloseRangeWeapon weapon = player.getEquippedWeapon();
            if (weapon != null) {
                playerBaseDamage += weapon.getDamage();
            }
            
            // God Mode: 10x Damage
            if (DevCommand.isGodMode()) {
                playerBaseDamage *= 10;
            }
            
            int playerDamage = playerBaseDamage + random.nextInt(5);
            opponent.setHealth(opponent.getHealth() - playerDamage);
            System.out.println("You hit the " + opponent.getName() + " for " + playerDamage + " damage. (Opponent's Health: " + String.format("%.0f", opponent.getHealth()) + ")");
            wait(200);

            if (opponent.getHealth() <= 0) {
                System.out.println("--- You defeated the " + opponent.getName() + "! ---");
                wait(1000);
                return false; // Player won
            }

            // --- Opponent's Turn ---
            int opponentDamage = opponent.getStrength() + random.nextInt(3);
            
            // God Mode: No Damage taken
            if (DevCommand.isGodMode()) {
                opponentDamage = 0;
                System.out.println("The " + opponent.getName() + " hits you, but you are INVINCIBLE! (0 damage)");
            } else {
                player.setHealth(player.getHealth() - opponentDamage);
                System.out.println("The " + opponent.getName() + " hits you for " + opponentDamage + " damage. (Your Health: " + String.format("%.0f", player.getHealth()) + ")");
            }
            wait(200);

            if (player.getHealth() <= 0) {
                System.out.println("--- You have been defeated! ---");
                wait(1000);
                return true; // Player was defeated
            }
        }
        return false;
    }

    private void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
