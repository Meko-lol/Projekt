package cz.meko.Game;

import cz.meko.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemFinderTest {

    private List<Item> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        items.add(new Item("Iron Sword", "weapon", 5.0, 100, "Sharp"));
        items.add(new Item("Health Potion", "potion", 0.5, 1, "Heals"));
    }

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

    @Test
    void joinArgs() {
        String[] args = {"pickup", "Health", "Potion"};
        String result = ItemFinder.joinArgs(args, 1);
        assertEquals("Health Potion", result);
    }
}
