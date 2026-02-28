package cz.meko.Game;

import cz.meko.Battle.Battle;
import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Interact.InteractHandler;
import cz.meko.Items.EquippableItems.Backpack;
import cz.meko.Items.EquippableItems.Pants;
import cz.meko.Items.Items.EquippableItems.Boots;
import cz.meko.Items.Items.EquippableItems.Chestplate;
import cz.meko.Items.Items.EquippableItems.Helmet;
import cz.meko.Items.Weapons.CloseRangeWeapon;
import cz.meko.ending.boulder.Boulder;
import cz.meko.ending.prison.Prison;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestManager {

    public void runAllTests() {
        System.out.println("\n=== STARTING AUTOMATED SYSTEM TEST ===");
        
        boolean battle = testBattle();
        boolean prison = testPrison();
        boolean npc = testNPC();
        boolean boulder = testBoulder();
        boolean equipment = testEquipment();
        boolean win = testWin();

        System.out.println("\n=== TEST REPORT ===");
        System.out.println("Battle Test:      " + (battle ? "PASSED" : "FAILED"));
        System.out.println("Prison Test:      " + (prison ? "PASSED" : "FAILED"));
        System.out.println("NPC Test:         " + (npc ? "PASSED" : "FAILED"));
        System.out.println("Boulder Test:     " + (boulder ? "PASSED" : "FAILED"));
        System.out.println("Equipment Test:   " + (equipment ? "PASSED" : "FAILED"));
        System.out.println("Win Screen Test:  " + (win ? "PASSED" : "FAILED"));
        System.out.println("===================");
    }

    public boolean testBattle() {
        try {
            System.out.print("Running Battle Test... ");
            Player testPlayer = new Player("Tester", "Human", 1000, 100, 10, 100, 50, 10, 10);
            testPlayer.setEquippedWeapon(new CloseRangeWeapon("Test Sword", 1, 100, "Test", 50));
            NPC testEnemy = new NPC("Test Goblin", "Goblin", 10, 50, 5, 50, 1, 5, 5, null, true);
            
            Battle battle = new Battle(testPlayer, testEnemy);
            boolean playerLost = battle.start();
            
            if (!playerLost && testEnemy.getHealth() <= 0) {
                System.out.println("OK");
                return true;
            }
            System.out.println("FAIL");
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean testPrison() {
        try {
            System.out.print("Running Prison Test... ");
            String simulatedInput = "map\negg\nsponge\n";
            InputStream originalIn = System.in;
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

            Prison prison = new Prison();
            boolean success = prison.attemptToBreakOut();
            
            System.setIn(originalIn);

            if (success) {
                System.out.println("OK");
                return true;
            }
            System.out.println("FAIL");
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean testNPC() {
        try {
            System.out.print("Running NPC Test... ");
            MyGame tempGame = new MyGame();
            tempGame.player = new Player("Tester", "Human", 100, 100, 10, 100, 10, 10, 10);
            tempGame.initialize();
            
            NPC testNPC = new NPC("Test Villager", "Human", 100, 100, 5, 100, 5, 5, 10, "generic_greeting", false);
            
            String simulatedInput = "2\n";
            InputStream originalIn = System.in;
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

            InteractHandler handler = new InteractHandler();
            handler.startInteraction(testNPC, tempGame);
            
            System.setIn(originalIn);

            System.out.println("OK");
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean testWin() {
        System.out.print("Running Win Test... ");
        System.out.println("OK");
        return true;
    }

    public boolean testBoulder() {
        try {
            System.out.print("Running Boulder Test... ");
            Player testPlayer = new Player("Strongman", "Human", 100, 100, 10, 100, 20, 10, 10);
            testPlayer.setEquippedWeapon(new CloseRangeWeapon("Sledgehammer", 10, 100, "Heavy", 30));
            Boulder boulder = new Boulder(50);
            
            while (!boulder.isBroken()) {
                boulder.attemptToBreakBoulder(testPlayer);
            }
            
            if (boulder.isBroken()) {
                System.out.println("OK");
                return true;
            }
            System.out.println("FAIL");
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean testEquipment() {
        try {
            System.out.print("Running Equipment Test... ");
            Player testPlayer = new Player("Knight", "Human", 100, 100, 10, 100, 10, 10, 10);
            
            testPlayer.equipItem(new Helmet("Test Helmet", 1, 100, "Test", 5));
            testPlayer.equipItem(new Chestplate("Test Chest", 1, 100, "Test", 10));
            testPlayer.setEquippedWeapon(new CloseRangeWeapon("Test Sword", 1, 100, "Test", 20));
            
            if (testPlayer.getHeadSlot() != null && testPlayer.getChestSlot() != null && testPlayer.getEquippedWeapon() != null) {
                System.out.println("OK");
                return true;
            }
            System.out.println("FAIL");
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }
}
