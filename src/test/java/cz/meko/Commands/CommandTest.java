package cz.meko.Commands;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the base Command class.
 * Since Command is likely an interface or abstract class, this test uses
 * a dummy implementation to check the standard methods.
 * @author Petrák
 */
class CommandTest {

    /** * A dummy command object used for testing the base functionality.
     */
    private Command command;

    /**
     * Setup method that runs before every test.
     * It creates a fake command on the fly that just returns "Executed"
     * so we have something to test the context methods on.
     */
    @BeforeEach
    void setUp() {
        command = new Command() {
            @Override
            public String execute() {
                return "Executed";
            }

            @Override
            public boolean exit() {
                return false;
            }
        };
    }

    /**
     * Tests setting the context for a command.
     * Passes in a real player, game, and some string arguments to make sure
     * the setContext method doesn't throw any unexpected errors.
     */
    @Test
    void setContext() {
        Player player = new Player();
        MyGame game = new MyGame();
        String[] args = {"test"};

        assertDoesNotThrow(() -> command.setContext(player, game, args));
    }

    /**
     * Edge case test: Tests setting the context with all null values.
     * Verifies that passing nulls into setContext doesn't crash the game
     * with a NullPointerException.
     */
    @Test
    void setContextNulls() {
        assertDoesNotThrow(() -> command.setContext(null, null, null));
    }

    /**
     * Tests the execute method.
     * Just makes sure that our dummy command returns the exact string
     * we programmed it to return during setup.
     */
    @Test
    void execute() {
        assertEquals("Executed", command.execute());
    }
}