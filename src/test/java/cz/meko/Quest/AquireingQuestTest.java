package cz.meko.Quest;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquireingQuestTest {

    private AquireingQuest quest;
    private Item requiredItem;
    private Item reward;

    @BeforeEach
    void setUp() {
        requiredItem = new Item("Key", "key", 0.1, 1, "Opens door");
        reward = new Item("Gold", "currency", 0.1, 1, "Money");
        quest = new AquireingQuest("Find Key", "Find the key", reward, requiredItem);
    }

    @Test
    void getRequiredItem() {
        assertEquals(requiredItem, quest.getRequiredItem());
    }

    @Test
    void testClone() {
        Quest cloned = quest.clone();
        assertTrue(cloned instanceof AquireingQuest);
        assertEquals(quest.name, cloned.name);
        assertEquals(((AquireingQuest)cloned).getRequiredItem().getName(), requiredItem.getName());
    }

    @Test
    void isCompleted() {
        assertFalse(quest.isCompleted());
        quest.complete();
        assertTrue(quest.isCompleted());
    }

    @Test
    void complete() {
        quest.complete();
        assertTrue(quest.isCompleted());
    }
}
