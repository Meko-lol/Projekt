package cz.meko.Characters.NPCs;

import cz.meko.Items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TraderNPCTest {

    @Test
    void addItemForSale() {
        TraderNPC trader = new TraderNPC("Trader", "start");
        Item item = new Item("Apple", "food", 0.1, 1, "Yum");
        trader.addItemForSale(item, 10);
        
        assertTrue(trader.getItemsForSale().containsKey(item));
        assertEquals(10, trader.getItemsForSale().get(item));
    }
}
