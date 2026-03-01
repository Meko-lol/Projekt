package cz.meko.Commands;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the CommandProcessor class.
 * This class just makes sure we can actually create the command processor
 * without any issues or crashes.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class CommandProcessorTest {

    /**
     * Tests the creation (instantiation) of the CommandProcessor.
     * It creates a basic empty player and a basic empty game, passes them
     * into the CommandProcessor constructor, and checks that the resulting
     * processor object is successfully created (meaning it is not null).
     */
    @Test
    void instantiation() {
        Player player = new Player();
        MyGame game = new MyGame();
        CommandProcessor processor = new CommandProcessor(player, game);
        assertNotNull(processor);
    }
}