package cz.meko.Game;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the ItemFinder utility class.
 * This class checks if the game can correctly search for items in a list
 * and piece together multi-word item names from player commands.
 * @author Petrák
 */
class ItemFinderTest {

    /** * A temporary list of items we use to test the search functions.
     */
    private List<Item> items;

    /**
     * Setup method that runs before every test.
     * Fills our test list with a couple of basic items (a sword and a potion)
     * so we have something to search for.
     */
    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        items.add(new Item("Iron Sword", "weapon", 5.0, 100, "Sharp"));
        items.add(new Item("Health Potion", "potion", 0.5, 1, "Heals"));
    }

    /**
     * Tests the logic for finding an item in a list.
     * Checks a few different player input scenarios:
     * 1. Exact match ("Iron Sword").
     * 2. Partial match (just typing "Potion").
     * 3. Case insensitive match (typing in all lowercase).
     * 4. Not found (searching for a "Shield" that isn't in the list returns null).
     */
    @Test
    void findItem() {
        // Exact match
        Item found = ItemFinder.findItem(items, "Iron Sword");
        assertNotNull(found);
        assertEquals("Iron Sword", found.getName());

        // Partial match
        found = ItemFinder.findItem(items, "Potion");
        assertNotNull(found);
        assertEquals("Health Potion", found.getName());

        // Case insensitive
        found = ItemFinder.findItem(items, "iron sword");
        assertNotNull(found);

        // Not found
        found = ItemFinder.findItem(items, "Shield");
        assertNull(found);
    }

    /**
     * Tests the joinArgs method which helps parse console commands.
     * If the player types "pickup Health Potion", this method should take
     * the words starting from index 1 and combine them into the single
     * string "Health Potion" so we can search for it.
     */
    @Test
    void joinArgs() {
        String[] args = {"pickup", "Health", "Potion"};
        String result = ItemFinder.joinArgs(args, 1);
        assertEquals("Health Potion", result);
    }
}