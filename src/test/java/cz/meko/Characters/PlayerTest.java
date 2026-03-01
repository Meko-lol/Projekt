package cz.meko.Characters;

import cz.meko.Items.Item;
import cz.meko.Items.EquippableItems.Helmet;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import cz.meko.Quest.Quest;
import cz.meko.Quest.KillingQuest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the Player class.
 * This class checks player mechanics like managing money, handling quests,
 * and equipping or unequipping items and weapons.
 * @author Petrák
 */
class PlayerTest {

    /** * The player object we use for all the tests.
     */
    private Player player;

    /**
     * Setup method that runs before every test.
     * It creates a fresh "Hero" character with default stats so changes
     * in one test don't affect the others.
     */
    @BeforeEach
    void setUp() {
        player = new Player("Hero", "Human", 100, 100, 10, 100, 10, 10, 10);
    }

    /**
     * Tests adding a new quest to the player.
     * Creates a quest to kill 3 rats and checks if it successfully goes
     * into the player's active quests list.
     */
    @Test
    void addQuest() {
        Quest quest = new KillingQuest("Kill Rats", "Kill 3 rats", null, "Rat", 3);
        player.addQuest(quest);
        assertTrue(player.getActiveQuests().contains(quest));
    }

    /**
     * Edge case test: Tests adding a null quest.
     * Verifies that adding null actually puts a null inside the active quests list.
     */
    @Test
    void addNullQuest() {
        player.addQuest(null);
        assertTrue(player.getActiveQuests().contains(null));
    }

    /**
     * Tests adding money to the player's wallet.
     * Sets money to 50, adds 50 more, and expects the total to be 100.
     */
    @Test
    void addMoney() {
        player.setMoney(50);
        player.addMoney(50);
        assertEquals(100, player.getMoney());
    }

    /**
     * Edge case test: Tests adding a negative amount of money.
     * Sets money to 50, adds -10, and expects the total to drop to 40.
     */
    @Test
    void addNegativeMoney() {
        player.setMoney(50);
        player.addMoney(-10);
        assertEquals(40, player.getMoney());
    }

    /**
     * Tests the spending money logic.
     * Gives the player 100 money, spends 50, and checks if it worked.
     * Then tries to spend 100 when the player only has 50 to make sure it fails.
     */
    @Test
    void spendMoney() {
        player.setMoney(100);
        assertTrue(player.spendMoney(50));
        assertEquals(50, player.getMoney());
        assertFalse(player.spendMoney(100)); // Not enough
        assertEquals(50, player.getMoney());
    }

    /**
     * Edge case test: Tests what happens if the player spends negative money.
     * Because of the math logic (100 - (-50) = 150), spending negative money
     * actually gives the player money.
     */
    @Test
    void spendNegativeMoney() {
        player.setMoney(100);
        // Spending negative money is adding money. Logic: if (100 >= -50) -> 100 - (-50) = 150.
        assertTrue(player.spendMoney(-50));
        assertEquals(150, player.getMoney());
    }

    /**
     * Tests equipping a close-range weapon.
     * Creates a sword and checks if the player holds it after equipping.
     */
    @Test
    void setEquippedWeapon() {
        CloseRangeWeapon sword = new CloseRangeWeapon("Sword", 1, 100, "Test", 10);
        player.setEquippedWeapon(sword);
        assertEquals(sword, player.getEquippedWeapon());
    }

    /**
     * Edge case test: Tests passing null into the weapon slot.
     * Makes sure setting the equipped weapon to null works without crashing.
     */
    @Test
    void setNullWeapon() {
        player.setEquippedWeapon(null);
        assertNull(player.getEquippedWeapon());
    }

    /**
     * Tests equipping an armor item.
     * Creates a helmet and checks if it successfully goes into the head slot.
     */
    @Test
    void equipItem() {
        Helmet helmet = new Helmet("Helmet", 1, 100, "Test", 5);
        player.equipItem(helmet);
        assertEquals(helmet, player.getHeadSlot());
    }

    /**
     * Edge case test: Tests equipping a null item.
     * Ensures that trying to equip null returns null and doesn't crash.
     */
    @Test
    void equipNullItem() {
        assertNull(player.equipItem(null));
    }

    /**
     * Tests unequipping an item the player is already wearing.
     * Equips a helmet, then unequips it, and checks if the head slot is empty.
     */
    @Test
    void unequipIfEquipped() {
        Helmet helmet = new Helmet("Helmet", 1, 100, "Test", 5);
        player.equipItem(helmet);
        player.unequipIfEquipped(helmet);
        assertNull(player.getHeadSlot());
    }

    /**
     * Edge case test: Tests unequipping a null item.
     * Makes sure that trying to unequip nothing doesn't throw a NullPointerException.
     */
    @Test
    void unequipNull() {
        // Should not crash
        player.unequipIfEquipped(null);
    }
}