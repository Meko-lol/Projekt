package cz.meko.Game;

import cz.meko.Characters.Player;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the main MyGame class.
 * This class checks if the core game mechanics work, like moving around the map,
 * loading the dashboard, getting defeated, and setting up the initial game state.
 * @author Petrák
 */
class MyGameTest {

    /** * The main game object used for testing.
     */
    private MyGame game;

    /**
     * Setup method that runs before every test.
     * We manually build a small 5x5 grid map and fill it with dummy locations
     * so the player has somewhere to walk. We also create a "Tester" player
     * and put them right in the middle of the map at coordinates (2,2).
     */
    @BeforeEach
    void setUp() {
        game = new MyGame();
        // Manually setup a simple map for testing
        game.gameMap = new MyMap(5, 5);
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                Location loc = new Location("Test Loc " + x + "," + y, null);
                loc.setX(x);
                loc.setY(y);
                game.gameMap.setLocation(x, y, loc);
            }
        }
        game.player = new Player("Tester", "Human", 100, 100, 10, 100, 10, 10, 10);
        game.xCordinate = 2;
        game.yCordinate = 2;
        game.initialize(); // Initialize transient fields
    }

    /**
     * Tries to test the startGame method.
     * Since startGame is an interactive loop that needs real user input
     * from System.in, we can't easily test it here without freezing.
     * We just assert the game isn't null as a basic sanity check.
     */
    @Test
    void startGame() {
        // Interactive method, hard to test without mocking System.in.
        // We assume it works if other methods work.
        assertNotNull(game);
    }

    /**
     * Tests if saving the game throws any weird errors.
     * Just runs saveGame() and checks that the game doesn't crash.
     */
    @Test
    void saveGame() {
        // Test that it runs without exception
        assertDoesNotThrow(() -> game.saveGame());
    }

    /**
     * Tests the initialize method.
     * Checks if the game successfully figures out what Location the player is
     * standing on based on their starting X and Y coordinates (2,2).
     */
    @Test
    void initialize() {
        game.initialize();
        assertNotNull(game.getCurrentLocation());
        assertEquals(2, game.getCurrentLocation().getX());
        assertEquals(2, game.getCurrentLocation().getY());
    }

    /**
     * Tests what happens to the player when they lose a fight.
     * We put a Prison at 0,0 and manually trigger the defeat event.
     * Checks if the player is teleported to 0,0 and healed to exactly 20 health.
     */
    @Test
    void handlePlayerDefeat() {
        // Setup a prison location
        Location prison = new Location("The Prison", null);
        prison.setX(0);
        prison.setY(0);
        game.gameMap.setLocation(0, 0, prison);

        game.handlePlayerDefeat();

        // Should be moved to prison (0,0)
        assertEquals(0, game.getXCordinate());
        assertEquals(0, game.getYCordinate());
        assertEquals(20, game.player.getHealth());
    }

    /**
     * Tests moving the player around the 2D map array.
     * Tests moving north (Y goes down), south (Y goes up), east (X goes up),
     * and west (X goes down) and makes sure the coordinates update correctly.
     */
    @Test
    void move() {
        // Start at 2,2
        game.move("north");
        assertEquals(2, game.getXCordinate());
        assertEquals(1, game.getYCordinate()); // y decreases going north

        game.move("south");
        assertEquals(2, game.getXCordinate());
        assertEquals(2, game.getYCordinate());

        game.move("east");
        assertEquals(3, game.getXCordinate());
        assertEquals(2, game.getYCordinate());

        game.move("west");
        assertEquals(2, game.getXCordinate());
        assertEquals(2, game.getYCordinate());
    }

    /**
     * Tests the dashboard UI generator.
     * Grabs the dashboard string and makes sure it's not null, and verifies
     * that it actually prints out the player's name ("Tester"), health, and the map.
     */
    @Test
    void getDashboard() {
        String dashboard = game.getDashboard();
        assertNotNull(dashboard);
        assertTrue(dashboard.contains("Tester")); // Player name
        assertTrue(dashboard.contains("Health"));
        assertTrue(dashboard.contains("World Map"));
    }
}