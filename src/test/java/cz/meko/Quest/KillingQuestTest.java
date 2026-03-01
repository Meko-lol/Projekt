package cz.meko.Quest;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KillingQuestTest {

    private KillingQuest quest;
    private Item reward;

    @BeforeEach
    void setUp() {
        reward = new Item("Gold", "currency", 0.1, 1, "Money");
        quest = new KillingQuest("Kill Rats", "Kill 3 rats", reward, "Rat", 3);
    }

    @Test
    void incrementKills() {
        assertEquals(0, quest.getCurrentKills());
        quest.incrementKills();
        assertEquals(1, quest.getCurrentKills());
    }

    @Test
    void isTarget() {
        assertTrue(quest.isTarget("Rat"));
        assertTrue(quest.isTarget("rat")); // Case insensitive
        assertFalse(quest.isTarget("Goblin"));
    }
    
    @Test
    void isTargetNull() {
        // equalsIgnoreCase throws NPE if targetName is null, but here targetName is "Rat".
        // If argument is null: "Rat".equalsIgnoreCase(null) -> false.
        assertFalse(quest.isTarget(null));
    }

    @Test
    void isCompleted() {
        assertFalse(quest.isCompleted());
        quest.incrementKills();
        quest.incrementKills();
        quest.incrementKills();
        assertTrue(quest.isCompleted());
    }

    @Test
    void testClone() {
        Quest cloned = quest.clone();
        assertTrue(cloned instanceof KillingQuest);
        assertEquals(quest.name, cloned.name);
        assertEquals(((KillingQuest)cloned).getTargetName(), "Rat");
    }
}
