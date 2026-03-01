package cz.meko.Game;

import cz.meko.Characters.NPCs.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NPCFinderTest {

    private List<NPC> npcs;

    @BeforeEach
    void setUp() {
        npcs = new ArrayList<>();
        npcs.add(new NPC("Barnaby", "Human", 100, 100, 10, 100, 10, 10, 10, "node", false));
        npcs.add(new NPC("Goblin King", "Goblin", 100, 100, 10, 100, 10, 10, 10, "node", true));
    }

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
