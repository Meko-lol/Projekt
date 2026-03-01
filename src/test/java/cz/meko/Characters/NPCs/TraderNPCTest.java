package cz.meko.Characters.NPCs;

import cz.meko.Items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the TraderNPC class.
 * This class checks if shop mechanics, like adding items for sale,
 * work correctly for the trader.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class TraderNPCTest {

    /**
     * Tests adding a new item to the trader's shop.
     * Creates a TraderNPC and an Apple item, adds the apple for sale at a price of 10,
     * and verifies that the trader's shop inventory contains the apple with the correct price.
     */
    @Test
    void addItemForSale() {
        TraderNPC trader = new TraderNPC("Trader", "start");
        Item item = new Item("Apple", "food", 0.1, 1, "Yum");
        trader.addItemForSale(item, 10);

        assertTrue(trader.getItemsForSale().containsKey(item));
        assertEquals(10, trader.getItemsForSale().get(item));
    }
}