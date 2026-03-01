package cz.meko.Quest;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the KillingQuest class.
 * This class checks the logic for hunting quests, making sure the kill counter
 * works correctly and the quest completes only when the target goal is reached.
 * * @author Petrák
 */
class KillingQuestTest {

    /** * The killing quest object used for testing. */
    private KillingQuest quest;

    /** * The reward item given upon finishing the quest. */
    private Item reward;

    /**
     * Sets up the test environment before each test.
     * Creates a quest called "Kill Rats" where the player needs to kill
     * 3 rats to earn Gold.
     */
    @BeforeEach
    void setUp() {
        reward = new Item("Gold", "currency", 0.1, 1, "Money");
        quest = new KillingQuest("Kill Rats", "Kill 3 rats", reward, "Rat", 3);
    }

    /**
     * Tests if the kill counter increases properly.
     * Checks that it starts at 0 and goes up by 1 after calling incrementKills.
     */
    @Test
    void incrementKills() {
        assertEquals(0, quest.getCurrentKills());
        quest.incrementKills();
        assertEquals(1, quest.getCurrentKills());
    }

    /**
     * Tests if the quest recognizes the correct enemy type.
     * Verifies that "Rat" is a target, that it handles case sensitivity
     * (matching "rat"), and that other enemies like "Goblin" are ignored.
     */
    @Test
    void isTarget() {
        assertTrue(quest.isTarget("Rat"));
        assertTrue(quest.isTarget("rat")); // Case insensitive
        assertFalse(quest.isTarget("Goblin"));
    }

    /**
     * Edge case test: Tests what happens if we check for a null target.
     * Makes sure the code doesn't crash and just returns false.
     */
    @Test
    void isTargetNull() {
        // equalsIgnoreCase throws NPE if targetName is null, but here targetName is "Rat".
        // If argument is null: "Rat".equalsIgnoreCase(null) -> false.
        assertFalse(quest.isTarget(null));
    }

    /**
     * Tests the completion logic based on the kill count.
     * Verifies that the quest is not finished at the start, but becomes
     * completed exactly after the 3rd kill.
     */
    @Test
    void isCompleted() {
        assertFalse(quest.isCompleted());
        quest.incrementKills();
        quest.incrementKills();
        quest.incrementKills();
        assertTrue(quest.isCompleted());
    }

    /**
     * Tests the cloning functionality.
     * Ensures that a cloned quest is a KillingQuest, has the same name,
     * and targets the same enemy as the original.
     */
    @Test
    void testClone() {
        Quest cloned = quest.clone();
        assertTrue(cloned instanceof KillingQuest);
        assertEquals(quest.name, cloned.name);
        assertEquals(((KillingQuest)cloned).getTargetName(), "Rat");
    }
}