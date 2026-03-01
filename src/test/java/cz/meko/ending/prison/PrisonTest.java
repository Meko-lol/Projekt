package cz.meko.ending.prison;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Prison class.
 * This class checks the logic for breaking out of prison, specifically
 * testing how the game handles user input for the escape sequence (like riddles).
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class PrisonTest {

    /**
     * Tests a successful attempt to break out of prison.
     * We fake the user's console input by putting "map", "egg", and "sponge"
     * into a ByteArrayInputStream and overriding System.in.
     * Checks if the method returns true when the player gets enough right answers.
     */
    @Test
    void attemptToBreakOutSuccess() {
        String input = "map\negg\nsponge\nwrong\nwrong\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Prison prison = new Prison();
        assertTrue(prison.attemptToBreakOut());
    }

    /**
     * Tests a failed attempt to break out.
     * We simulate the user typing "wrong" for every answer by faking the System.in.
     * Checks if the method returns false because the player failed the sequence.
     */
    @Test
    void attemptToBreakOutFail() {
        String input = "wrong\nwrong\nwrong\nwrong\nwrong\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Prison prison = new Prison();
        assertFalse(prison.attemptToBreakOut());
    }

    /**
     * Edge case test: Tests what happens if the user inputs nothing.
     * Simulates the user just pressing the "Enter" key 5 times (\n\n\n\n\n).
     * Makes sure the game handles empty strings correctly and returns false
     * instead of crashing.
     */
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