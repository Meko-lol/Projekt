package cz.meko.Commands.commandList;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the GoToCommand class.
 * This class ensures that the movement commands correctly update the player's
 * position on the game map when they choose a direction.
 * @author Petrák
 */
class GoToCommandTest {

    /* The movement command being tested. */
    private GoToCommand command;

    /* The game instance holding the map and coordinates. */
    private MyGame game;

    /* The player object performing the movement. */
    private Player player;

    /**
     * Sets up the movement test environment before each test.
     * Creates a small map and places the player at the "Start" location (0,0).
     * Prepares the command to move the player to the "South" location (0,1).
     */
    @BeforeEach
    void setUp() {
        command = new GoToCommand();
        game = new MyGame();
        player = new Player();

        game.gameMap = new MyMap(2, 2);
        game.gameMap.setLocation(0, 0, new Location("Start", null));
        game.gameMap.setLocation(0, 1, new Location("South", null));
        game.xCordinate = 0;
        game.yCordinate = 0;
        game.setCurrentLocation(game.gameMap.getLocation(0, 0));

        command.setContext(player, game, new String[]{"move", "south"});
    }

    /**
     * Tests the execution of the movement command.
     * Verifies that the player receives the correct confirmation message and
     * that their Y-coordinate increases, showing they successfully moved south.
     */
    @Test
    void execute() {
        String result = command.execute();
        assertEquals("You moved south.", result);
        assertEquals(0, game.getXCordinate());
        assertEquals(1, game.getYCordinate());
    }
}