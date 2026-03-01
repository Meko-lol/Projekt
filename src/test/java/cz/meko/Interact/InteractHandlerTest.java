package cz.meko.Interact;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the InteractHandler class.
 * This class checks if the system for talking to NPCs and choosing
 * dialogue options works correctly.
 * * @author Petrák
 */
class InteractHandlerTest {

    /**
     * Tests starting an interaction with an NPC.
     * Since interactions use the console scanner for choosing dialogue options,
     * we fake the user typing "1" by overriding System.in with a ByteArrayInputStream.
     * Right now, this just tests if the handler is created successfully without crashing.
     */
    @Test
    void startInteraction() {
        // Hard to test fully without mocking, but we can test basic flow
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InteractHandler handler = new InteractHandler();
        assertNotNull(handler);
    }
}