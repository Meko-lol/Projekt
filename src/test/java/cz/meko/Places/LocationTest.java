package cz.meko.Places;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Location class.
 * This class verifies that a specific spot on the map can correctly
 * hold items, NPCs, and obstacles like walls or locked paths.
 * * @author Petrák
 */
class LocationTest {

    /**
     * The location instance used for testing.
     */
    private Location location;

    /**
     * Setup method that runs before each test.
     * Creates a fresh location named "Test Loc" so we can test adding
     * things to it.
     */
    @BeforeEach
    void setUp() {
        location = new Location("Test Loc", null);
    }

    /**
     * Tests dropping an item on the ground in this location.
     * Adds a "Rock" item and checks if it actually appears in the list
     * of items on the ground.
     */
    @Test
    void addItem() {
        Item item = new Item("Rock", "misc", 1, 1, "Rock");
        location.addItem(item);
        assertTrue(location.getItemsOnGround().contains(item));
    }

    /**
     * Tests placing an NPC in this location.
     * Adds a human named "Bob" and verifies that he is successfully
     * added to the list of NPCs in this room.
     */
    @Test
    void addNpc() {
        NPC npc = new NPC("Bob", "Human", 100, 100, 10, 100, 10, 10, 10, "node", false);
        location.addNpc(npc);
        assertTrue(location.getNpcs().contains(npc));
    }

    /**
     * Tests setting a movement obstacle in a specific direction.
     * Places a "Wall" to the north that requires a "Pickaxe" to pass,
     * and checks if the location correctly remembers this obstacle.
     */
    @Test
    void setObstacle() {
        Obstacle obs = new Obstacle("Wall", "A wall", "Pickaxe", 10);
        location.setObstacle("north", obs);
        assertEquals(obs, location.getObstacle("north"));
    }
}