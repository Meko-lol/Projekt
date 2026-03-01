package cz.meko.Characters.NPCs;

import cz.meko.Items.Item;
import cz.meko.Quest.KillingQuest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the NPC class.
 * This class checks if we can correctly set dialogue nodes, assign quests,
 * and add loot items to an NPC.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class NPCTest {

    /** * The NPC object used for testing.
     */
    private NPC npc;

    /**
     * Setup method that runs before every test.
     * Initializes a standard Human NPC named Bob so we have a fresh
     * object for each test method.
     */
    @BeforeEach
    void setUp() {
        npc = new NPC("Bob", "Human", 100, 100, 10, 100, 10, 10, 10, "start", false);
    }

    /**
     * Tests changing the dialogue node name of the NPC.
     * Sets the dialogue node to "end" and checks if the getter
     * returns the updated string.
     */
    @Test
    void setDialogueNodeName() {
        npc.setDialogueNodeName("end");
        assertEquals("end", npc.getDialogueNodeName());
    }

    /**
     * Tests assigning a quest to the NPC.
     * Creates a dummy KillingQuest, gives it to the NPC, and
     * verifies that the NPC holds exactly the same quest object.
     */
    @Test
    void setQuestToGive() {
        KillingQuest quest = new KillingQuest("Kill", "Desc", null, "Rat", 1);
        npc.setQuestToGive(quest);
        assertEquals(quest, npc.getQuestToGive());
    }

    /**
     * Tests adding a dropped item to the NPC's loot list.
     * Creates a Gold item, adds it to the NPC, and checks if the
     * NPC's loot list actually contains that specific item.
     */
    @Test
    void addLoot() {
        Item item = new Item("Gold", "currency", 0.1, 1, "Money");
        npc.addLoot(item);
        assertTrue(npc.getLoot().contains(item));
    }
}