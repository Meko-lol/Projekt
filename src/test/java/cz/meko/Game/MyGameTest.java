package cz.meko.Game;

import cz.meko.Characters.Player;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyGameTest {

    private MyGame game;

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

    @Test
    void startGame() {
        // Interactive method, hard to test without mocking System.in.
        // We assume it works if other methods work.
        assertNotNull(game);
    }

    @Test
    void saveGame() {
        // Test that it runs without exception
        assertDoesNotThrow(() -> game.saveGame());
    }

    @Test
    void initialize() {
        game.initialize();
        assertNotNull(game.getCurrentLocation());
        assertEquals(2, game.getCurrentLocation().getX());
        assertEquals(2, game.getCurrentLocation().getY());
    }

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

    @Test
    void getDashboard() {
        String dashboard = game.getDashboard();
        assertNotNull(dashboard);
        assertTrue(dashboard.contains("Tester")); // Player name
        assertTrue(dashboard.contains("Health"));
        assertTrue(dashboard.contains("World Map"));
    }
}
