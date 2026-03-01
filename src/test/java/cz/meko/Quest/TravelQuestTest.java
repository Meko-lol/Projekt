package cz.meko.Quest;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TravelQuest class.
 * This class ensures that quests related to visiting specific map locations
 * are working correctly and can be cloned properly.
 * * @author Petrák
 */
class TravelQuestTest {

    /** * The travel quest instance used for testing. */
    private TravelQuest quest;

    /** * The reward item for completing the journey. */
    private Item reward;

    /**
     * Sets up the test case before each method.
     * Creates a "Go to Town" quest where the objective is to reach
     * the "Town Square" location.
     */
    @BeforeEach
    void setUp() {
        reward = new Item("Gold", "currency", 0.1, 1, "Money");
        quest = new TravelQuest("Go to Town", "Visit the town", reward, "Town Square");
    }

    /**
     * Tests if the quest correctly returns the name of the destination.
     */
    @Test
    void getDestinationName() {
        assertEquals("Town Square", quest.getDestinationName());
    }

    /**
     * Tests the cloning of a travel quest.
     * Checks if the cloned object is still a TravelQuest and keeps
     * the same quest name and destination name as the original.
     */
    @Test
    void testClone() {
        Quest cloned = quest.clone();
        assertTrue(cloned instanceof TravelQuest);
        assertEquals(quest.name, cloned.name);
        assertEquals(((TravelQuest)cloned).getDestinationName(), "Town Square");
    }
}