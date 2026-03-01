package cz.meko.Commands;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CommandRegistryTest {

    @Test
    void getCommands() {
        Map<String, Command> commands = CommandRegistry.getCommands();
        assertNotNull(commands);
        assertFalse(commands.isEmpty());
        assertTrue(commands.containsKey("move"));
        assertTrue(commands.containsKey("help"));
    }
}
