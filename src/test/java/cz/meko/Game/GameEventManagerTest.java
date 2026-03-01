package cz.meko.Game;

import cz.meko.Characters.Player;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the GameEventManager class.
 * This class checks the events that happen during the game, like what
 * the game should do when the player loses all their health.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class GameEventManagerTest {

    /**
     * Tests the logic for when the player gets defeated in battle.
     * Sets up a small 2x2 map, puts a "Start" location at 0,0 and
     * "The Prison" at 1,1. It creates a player with 0 health to simulate death.
     * After calling handlePlayerDefeat, it checks if:
     * 1. The player is revived with exactly 20 health.
     * 2. The player's X and Y coordinates are moved to 1,1.
     * 3. The current location is successfully updated to "The Prison".
     */
    @Test
    void handlePlayerDefeat() {
        GameEventManager manager = new GameEventManager();
        MyGame game = new MyGame();
        game.player = new Player("Tester", "Human", 0, 100, 10, 100, 10, 10, 10);
        game.gameMap = new MyMap(2, 2);

        Location prison = new Location("The Prison", null);
        prison.setX(1);
        prison.setY(1);
        game.gameMap.setLocation(1, 1, prison);
        game.gameMap.setLocation(0, 0, new Location("Start", null));

        manager.handlePlayerDefeat(game);

        assertEquals(20, game.player.getHealth());
        assertEquals(1, game.getXCordinate());
        assertEquals(1, game.getYCordinate());
        assertEquals("The Prison", game.getCurrentLocation().getName());
    }
}