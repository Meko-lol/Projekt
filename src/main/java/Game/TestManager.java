package Game;

import Battle.Battle;
import Characters.NPCs.NPC;
import Characters.Player;
import Interact.InteractHandler;
import Items.EquippableItems.Backpack;
import Items.EquippableItems.Pants;
import Items.Items.EquippableItems.Boots;
import Items.Items.EquippableItems.Chestplate;
import Items.Items.EquippableItems.Helmet;
import Items.Weapons.CloseRangeWeapon;
import ending.boulder.Boulder;
import ending.prison.Prison;

public class TestManager {

    public void runAllTests() {
        System.out.println("\n=== STARTING FULL SYSTEM TEST ===");
        testBattle();
        testPrison();
        testNPC();
        testBoulder();
        testEquipment();
        testWin();
        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }

    public void testBattle() {
        System.out.println("\n--- Testing Battle ---");
        Player testPlayer = new Player("Tester", "Human", 100, 100, 10, 100, 10, 10, 10);
        testPlayer.setEquippedWeapon(new CloseRangeWeapon("Test Sword", 1, 100, "Test", 15));
        NPC testEnemy = new NPC("Test Goblin", "Goblin", 50, 50, 5, 50, 8, 5, 5, null, true);
        
        Battle battle = new Battle(testPlayer, testEnemy);
        battle.start();
        System.out.println("--- Battle Test Complete ---");
    }

    public void testPrison() {
        System.out.println("\n--- Testing Prison Escape ---");
        Prison prison = new Prison();
        boolean success = prison.attemptToBreakOut();
        if (success) System.out.println("Result: Escaped!");
        else System.out.println("Result: Failed!");
        System.out.println("--- Prison Test Complete ---");
    }

    public void testNPC() {
        System.out.println("\n--- Testing NPC Interaction ---");
        MyGame tempGame = new MyGame();
        tempGame.player = new Player("Tester", "Human", 100, 100, 10, 100, 10, 10, 10);
        // We need to load dialogues for this test to work properly
        // Assuming FileManager is accessible or we mock it. 
        // For a unit test, we might mock, but here we use the real loader.
        // Note: MyGame constructor initializes UI and EventManager, but not dialogues.
        // We need to manually load them or call initialize().
        tempGame.initialize(); 
        
        NPC testNPC = new NPC("Test Villager", "Human", 100, 100, 5, 100, 5, 5, 10, "generic_greeting", false);
        
        InteractHandler handler = new InteractHandler();
        handler.startInteraction(testNPC, tempGame);
        System.out.println("--- NPC Test Complete ---");
    }

    public void testWin() {
        System.out.println("\n--- Testing Win Screen ---");
        System.out.println("With the Guardians defeated, you step through the final gate and into the light. You have escaped! Congratulations!");
        System.out.println("******************************************");
        System.out.println("*             VICTORY!                   *");
        System.out.println("******************************************");
        System.out.println("--- Win Test Complete ---");
    }

    public void testBoulder() {
        System.out.println("\n--- Testing Boulder Breaking ---");
        Player testPlayer = new Player("Strongman", "Human", 100, 100, 10, 100, 20, 10, 10);
        testPlayer.setEquippedWeapon(new CloseRangeWeapon("Sledgehammer", 10, 100, "Heavy", 30));
        Boulder boulder = new Boulder(100);
        
        System.out.println("Player Strength: " + testPlayer.getStrength());
        System.out.println("Weapon Damage: " + testPlayer.getEquippedWeapon().getDamage());
        
        while (!boulder.isBroken()) {
            boulder.attemptToBreakBoulder(testPlayer);
        }
        System.out.println("--- Boulder Test Complete ---");
    }

    public void testEquipment() {
        System.out.println("\n--- Testing Equipment ---");
        Player testPlayer = new Player("Knight", "Human", 100, 100, 10, 100, 10, 10, 10);
        
        System.out.println("Before Equip:");
        System.out.println(testPlayer.getPlayerInfo());
        
        System.out.println("\nEquipping full set...");
        testPlayer.equipItem(new Helmet("Test Helmet", 1, 100, "Test", 5));
        testPlayer.equipItem(new Chestplate("Test Chest", 1, 100, "Test", 10));
        testPlayer.equipItem(new Pants("Test Pants", 1, 100, "Test", 5));
        testPlayer.equipItem(new Boots("Test Boots", 1, 100, "Test", 5));
        testPlayer.equipItem(new Backpack("Test Backpack", 1, 100, "Test", 50));
        testPlayer.setEquippedWeapon(new CloseRangeWeapon("Test Sword", 1, 100, "Test", 20));
        
        System.out.println("\nAfter Equip:");
        System.out.println(testPlayer.getPlayerInfo());
        System.out.println("--- Equipment Test Complete ---");
    }
}
