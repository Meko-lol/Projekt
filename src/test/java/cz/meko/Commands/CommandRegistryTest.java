package cz.meko.Commands;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the CommandRegistry class.
 * This class makes sure that our game successfully loads and stores
 * the list of available commands that the player can type.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class CommandRegistryTest {

    /**
     * Tests the getCommands method.
     * Grabs the map of all registered commands and checks a few things:
     * 1. The map actually exists (it is not null).
     * 2. The map is not empty.
     * 3. It contains basic commands that we know should be there, like "move" and "help".
     */
    @Test
    void getCommands() {
        Map<String, Command> commands = CommandRegistry.getCommands();
        assertNotNull(commands);
        assertFalse(commands.isEmpty());
        assertTrue(commands.containsKey("move"));
        assertTrue(commands.containsKey("help"));
    }
}