package cz.meko.Characters;

import cz.meko.Items.Item;
import cz.meko.Items.EquippableItems.Helmet;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import cz.meko.Quest.Quest;
import cz.meko.Quest.KillingQuest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Hero", "Human", 100, 100, 10, 100, 10, 10, 10);
    }

    @Test
    void addQuest() {
        Quest quest = new KillingQuest("Kill Rats", "Kill 3 rats", null, "Rat", 3);
        player.addQuest(quest);
        assertTrue(player.getActiveQuests().contains(quest));
    }
    
    @Test
    void addNullQuest() {
        player.addQuest(null);
        assertTrue(player.getActiveQuests().contains(null));
    }

    @Test
    void addMoney() {
        player.setMoney(50);
        player.addMoney(50);
        assertEquals(100, player.getMoney());
    }
    
    @Test
    void addNegativeMoney() {
        player.setMoney(50);
        player.addMoney(-10);
        assertEquals(40, player.getMoney());
    }

    @Test
    void spendMoney() {
        player.setMoney(100);
        assertTrue(player.spendMoney(50));
        assertEquals(50, player.getMoney());
        assertFalse(player.spendMoney(100)); // Not enough
        assertEquals(50, player.getMoney());
    }
    
    @Test
    void spendNegativeMoney() {
        player.setMoney(100);
        // Spending negative money is adding money. Logic: if (100 >= -50) -> 100 - (-50) = 150.
        assertTrue(player.spendMoney(-50));
        assertEquals(150, player.getMoney());
    }

    @Test
    void setEquippedWeapon() {
        CloseRangeWeapon sword = new CloseRangeWeapon("Sword", 1, 100, "Test", 10);
        player.setEquippedWeapon(sword);
        assertEquals(sword, player.getEquippedWeapon());
    }
    
    @Test
    void setNullWeapon() {
        player.setEquippedWeapon(null);
        assertNull(player.getEquippedWeapon());
    }

    @Test
    void equipItem() {
        Helmet helmet = new Helmet("Helmet", 1, 100, "Test", 5);
        player.equipItem(helmet);
        assertEquals(helmet, player.getHeadSlot());
    }
    
    @Test
    void equipNullItem() {
        assertNull(player.equipItem(null));
    }

    @Test
    void unequipIfEquipped() {
        Helmet helmet = new Helmet("Helmet", 1, 100, "Test", 5);
        player.equipItem(helmet);
        player.unequipIfEquipped(helmet);
        assertNull(player.getHeadSlot());
    }
    
    @Test
    void unequipNull() {
        // Should not crash
        player.unequipIfEquipped(null);
    }
}
