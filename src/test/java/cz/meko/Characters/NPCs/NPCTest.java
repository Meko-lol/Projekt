package cz.meko.Characters.NPCs;

import cz.meko.Items.Item;
import cz.meko.Quest.KillingQuest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NPCTest {

    private NPC npc;

    @BeforeEach
    void setUp() {
        npc = new NPC("Bob", "Human", 100, 100, 10, 100, 10, 10, 10, "start", false);
    }

    @Test
    void setDialogueNodeName() {
        npc.setDialogueNodeName("end");
        assertEquals("end", npc.getDialogueNodeName());
    }

    @Test
    void setQuestToGive() {
        KillingQuest quest = new KillingQuest("Kill", "Desc", null, "Rat", 1);
        npc.setQuestToGive(quest);
        assertEquals(quest, npc.getQuestToGive());
    }

    @Test
    void addLoot() {
        Item item = new Item("Gold", "currency", 0.1, 1, "Money");
        npc.addLoot(item);
        assertTrue(npc.getLoot().contains(item));
    }
}
