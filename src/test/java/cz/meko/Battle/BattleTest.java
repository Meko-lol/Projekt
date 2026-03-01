package cz.meko.Battle;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the Battle class.
 * This class verifies that the combat mechanics work correctly and checks
 * the win and lose conditions for the player.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class BattleTest {

    /** * The player character used in the battle tests.
     */
    private Player player;

    /** * The enemy NPC used in the battle tests.
     */
    private NPC enemy;

    /**
     * Sets up the test environment before each test runs.
     * Initializes a basic Hero player and a Goblin enemy with default stats
     * so they don't have to be recreated in every single test method.
     */
    @BeforeEach
    void setUp() {
        // Create a basic player and enemy for testing
        player = new Player("Hero", "Human", 100, 100, 10, 100, 10, 10, 10);
        enemy = new NPC("Goblin", "Goblin", 50, 50, 5, 50, 5, 5, 5, null, true);
    }

    /**
     * Tests a battle scenario where the player is guaranteed to win.
     * Gives the player an overpowered sword and starts the battle.
     * Checks if the start method returns false (meaning the player survived)
     * and if the enemy's health is 0 or less.
     */
    @Test
    void start() {
        // Test a battle where the player should win
        // Give player a strong weapon
        CloseRangeWeapon sword = new CloseRangeWeapon("Strong Sword", 1, 100, "Test", 50);
        player.setEquippedWeapon(sword);

        Battle battle = new Battle(player, enemy);
        boolean result = battle.start();

        // Result should be false because player won (false means player didn't lose)
        assertFalse(result);
        // Enemy should be dead
        assertTrue(enemy.getHealth() <= 0);
    }

    /**
     * Tests a battle scenario where the player is guaranteed to lose.
     * Reduces the player's health to 1 and buffs the enemy's strength to 100.
     * Checks if the start method returns true (meaning the player died)
     * and if the player's health is 0 or less.
     */
    @Test
    void testStart() {
        // Test a battle where the player loses
        // Set player health very low
        player.setHealth(1);
        // Set enemy strength very high
        enemy.setStrength(100);

        Battle battle = new Battle(player, enemy);
        boolean result = battle.start();

        // Result should be true because player lost
        assertTrue(result);
        // Player should be dead
        assertTrue(player.getHealth() <= 0);
    }
}