package cz.meko.Items.EquippableItems;

import cz.meko.Items.EquippableItems.Helmet; // THE FIX: Correct package
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EquippableItemTest {

    @Test
    void getSlot() {
        Helmet helmet = new Helmet("Helmet", 1, 100, "Test", 5);
        assertEquals("head", helmet.getSlot());
    }
}
