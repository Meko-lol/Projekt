package cz.meko.Items.EquippableItems;

import cz.meko.Items.EquippableItems.Helmet; // THE FIX: Correct package
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the EquippableItem classes.
 * This class makes sure that items you can wear, like a helmet,
 * actually know which body part or equipment slot they belong to.
 * @author Petrák
 */
class EquippableItemTest {

    /**
     * Tests the getSlot method for a piece of armor.
     * Creates a dummy Helmet and checks if it correctly says it
     * belongs in the "head" equipment slot.
     */
    @Test
    void getSlot() {
        Helmet helmet = new Helmet("Helmet", 1, 100, "Test", 5);
        assertEquals("head", helmet.getSlot());
    }
}