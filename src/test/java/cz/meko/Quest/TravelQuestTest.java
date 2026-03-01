package cz.meko.Quest;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TravelQuestTest {

    private TravelQuest quest;
    private Item reward;

    @BeforeEach
    void setUp() {
        reward = new Item("Gold", "currency", 0.1, 1, "Money");
        quest = new TravelQuest("Go to Town", "Visit the town", reward, "Town Square");
    }

    @Test
    void getDestinationName() {
        assertEquals("Town Square", quest.getDestinationName());
    }

    @Test
    void testClone() {
        Quest cloned = quest.clone();
        assertTrue(cloned instanceof TravelQuest);
        assertEquals(quest.name, cloned.name);
        assertEquals(((TravelQuest)cloned).getDestinationName(), "Town Square");
    }
}
