package cz.meko.ending.boulder;

import cz.meko.Characters.Player;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Boulder class.
 * This class checks if the player is able to successfully break the boulder
 * that blocks the path using their equipped weapon.
 * @author Petrák
 */
class BoulderTest {

    /**
     * Tests breaking a boulder with a weapon.
     * Creates a boulder with 10 health and a player equipped with a Hammer
     * that deals 10 damage. When the player attempts to break it, the total
     * damage (weapon + player strength) should be enough.
     * Finally, it checks if the boulder is actually marked as broken.
     */
    @Test
    void attemptToBreakBoulder() {
        Boulder boulder = new Boulder(10);
        Player player = new Player();
        player.setEquippedWeapon(new CloseRangeWeapon("Hammer", 1, 100, "Test", 10));

        boulder.attemptToBreakBoulder(player); // 10 dmg + str
        assertTrue(boulder.isBroken());
    }
}