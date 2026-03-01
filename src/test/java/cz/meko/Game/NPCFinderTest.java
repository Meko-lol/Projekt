package cz.meko.Game;

import cz.meko.Characters.NPCs.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the NPCFinder utility class.
 * This class checks if the game can correctly search through a list of NPCs
 * and find the right one based on the player's text input.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class NPCFinderTest {

    /** * A temporary list of NPCs that we use to run our search tests.
     */
    private List<NPC> npcs;

    /**
     * Setup method that runs before every test.
     * Fills our test list with a couple of basic NPCs (a human named Barnaby
     * and a Goblin King) so the search method actually has something to look for.
     */
    @BeforeEach
    void setUp() {
        npcs = new ArrayList<>();
        npcs.add(new NPC("Barnaby", "Human", 100, 100, 10, 100, 10, 10, 10, "node", false));
        npcs.add(new NPC("Goblin King", "Goblin", 100, 100, 10, 100, 10, 10, 10, "node", true));
    }

    /**
     * Tests the logic for finding an NPC in a list.
     * Checks multiple scenarios based on how a player might type a name:
     * 1. Exact match (typing "Barnaby").
     * 2. Partial match (just typing "Goblin" to find the Goblin King).
     * 3. Case insensitive match (typing "barnaby" in all lowercase).
     * 4. Not found (searching for a "Dragon" that isn't there returns null).
     * 5. Edge case: Searching inside a completely empty list returns null without crashing.
     */
    @Test
    void findNPC() {
        // Exact match
        NPC found = NPCFinder.findNPC(npcs, "Barnaby");
        assertNotNull(found);
        assertEquals("Barnaby", found.getName());

        // Partial match
        found = NPCFinder.findNPC(npcs, "Goblin");
        assertNotNull(found);
        assertEquals("Goblin King", found.getName());

        // Case insensitive
        found = NPCFinder.findNPC(npcs, "barnaby");
        assertNotNull(found);

        // Not found
        found = NPCFinder.findNPC(npcs, "Dragon");
        assertNull(found);

        // Empty list
        assertNull(NPCFinder.findNPC(new ArrayList<>(), "Barnaby"));
    }
}