package cz.meko.Places;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location("Test Loc", null);
    }

    @Test
    void addItem() {
        Item item = new Item("Rock", "misc", 1, 1, "Rock");
        location.addItem(item);
        assertTrue(location.getItemsOnGround().contains(item));
    }

    @Test
    void addNpc() {
        NPC npc = new NPC("Bob", "Human", 100, 100, 10, 100, 10, 10, 10, "node", false);
        location.addNpc(npc);
        assertTrue(location.getNpcs().contains(npc));
    }

    @Test
    void setObstacle() {
        Obstacle obs = new Obstacle("Wall", "A wall", "Pickaxe", 10);
        location.setObstacle("north", obs);
        assertEquals(obs, location.getObstacle("north"));
    }
}
