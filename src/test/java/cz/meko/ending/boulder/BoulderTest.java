package cz.meko.ending.boulder;

import cz.meko.Characters.Player;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoulderTest {

    @Test
    void attemptToBreakBoulder() {
        Boulder boulder = new Boulder(10);
        Player player = new Player();
        player.setEquippedWeapon(new CloseRangeWeapon("Hammer", 1, 100, "Test", 10));
        
        boulder.attemptToBreakBoulder(player); // 10 dmg + str
        assertTrue(boulder.isBroken());
    }
}
