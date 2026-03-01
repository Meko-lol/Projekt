package cz.meko.Commands.commandList;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import cz.meko.Items.Item;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the PickUpCommand class.
 * This class verifies that the player can successfully pick up items
 * from the ground and add them to their inventory.
 * @author Petrák
 */
class PickUpCommandTest {

    /* The pickup command being tested. */
    private PickUpCommand command;

    /* The game instance providing the location context. */
    private MyGame game;

    /* The player who will be picking up the item. */
    private Player player;

    /**
     * Sets up the pickup scenario before each test.
     * Creates a location with a "Rock" item on the ground and sets the
     * command context to target that specific item.
     */
    @BeforeEach
    void setUp() {
        command = new PickUpCommand();
        game = new MyGame();
        player = new Player();

        Location loc = new Location("Loc", null);
        loc.addItem(new Item("Rock", "misc", 1, 1, "Rock"));
        game.setCurrentLocation(loc);

        command.setContext(player, game, new String[]{"pickup", "Rock"});
    }

    /**
     * Tests the execution of the pickup command.
     * Checks if the game returns a "picked up" confirmation message and
     * verifies that the "Rock" is actually present in the player's inventory.
     */
    @Test
    void execute() {
        String result = command.execute();
        assertTrue(result.contains("picked up"));
        assertNotNull(player.getInventory().getItemByName("Rock"));
    }
}