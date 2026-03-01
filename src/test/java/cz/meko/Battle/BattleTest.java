package cz.meko.Battle;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    private Player player;
    private NPC enemy;

    @BeforeEach
    void setUp() {
        // Create a basic player and enemy for testing
        player = new Player("Hero", "Human", 100, 100, 10, 100, 10, 10, 10);
        enemy = new NPC("Goblin", "Goblin", 50, 50, 5, 50, 5, 5, 5, null, true);
    }

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
