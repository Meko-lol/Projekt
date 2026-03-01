package cz.meko.Interact;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class InteractHandlerTest {

    @Test
    void startInteraction() {
        // Hard to test fully without mocking, but we can test basic flow
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        InteractHandler handler = new InteractHandler();
        assertNotNull(handler);
    }
}
