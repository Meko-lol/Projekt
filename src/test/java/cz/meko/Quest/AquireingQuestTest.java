package cz.meko.Quest;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the AquireingQuest class.
 * This class checks the logic for quests where the player needs to find
 * a specific item to finish the task.
 * * @author Petrák
 */
class AquireingQuestTest {

    /** * The quest object we are testing. */
    private AquireingQuest quest;

    /** * The item the player needs to find. */
    private Item requiredItem;

    /** * The item the player gets for finishing the quest. */
    private Item reward;

    /**
     * Setup method that runs before every test.
     * It creates a "Find Key" quest where the player needs a Key item
     * and gets Gold as a reward.
     */
    @BeforeEach
    void setUp() {
        requiredItem = new Item("Key", "key", 0.1, 1, "Opens door");
        reward = new Item("Gold", "currency", 0.1, 1, "Money");
        quest = new AquireingQuest("Find Key", "Find the key", reward, requiredItem);
    }

    /**
     * Tests if the quest correctly remembers which item is required.
     */
    @Test
    void getRequiredItem() {
        assertEquals(requiredItem, quest.getRequiredItem());
    }

    /**
     * Tests the clone method to make sure we can create a deep copy of the quest.
     * This is useful if we want to give the same quest template to multiple players.
     * Checks if the name and required item stay the same in the copy.
     */
    @Test
    void testClone() {
        Quest cloned = quest.clone();
        assertTrue(cloned instanceof AquireingQuest);
        assertEquals(quest.name, cloned.name);
        assertEquals(((AquireingQuest)cloned).getRequiredItem().getName(), requiredItem.getName());
    }

    /**
     * Tests the completion status of the quest.
     * Makes sure a new quest is not finished by default, and stays finished
     * after calling the complete method.
     */
    @Test
    void isCompleted() {
        assertFalse(quest.isCompleted());
        quest.complete();
        assertTrue(quest.isCompleted());
    }

    /**
     * Simple test to verify that calling complete() actually marks
     * the quest as finished.
     */
    @Test
    void complete() {
        quest.complete();
        assertTrue(quest.isCompleted());
    }
}