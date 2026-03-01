package cz.meko.ending.prison;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class PrisonTest {

    @Test
    void attemptToBreakOutSuccess() {
        String input = "map\negg\nsponge\nwrong\nwrong\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Prison prison = new Prison();
        assertTrue(prison.attemptToBreakOut());
    }

    @Test
    void attemptToBreakOutFail() {
        String input = "wrong\nwrong\nwrong\nwrong\nwrong\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Prison prison = new Prison();
        assertFalse(prison.attemptToBreakOut());
    }
    
    @Test
    void attemptToBreakOutEmptyInput() {
        // Simulating empty answers (user just presses enter)
        String input = "\n\n\n\n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Prison prison = new Prison();
        assertFalse(prison.attemptToBreakOut());
    }
}
