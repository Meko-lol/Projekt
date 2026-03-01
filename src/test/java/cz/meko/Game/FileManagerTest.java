package cz.meko.Game;

import cz.meko.Interact.Node;
import cz.meko.Items.Item;
import cz.meko.Places.Obstacle;
import cz.meko.Quest.Quest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the FileManager class.
 * This class checks if our game correctly reads data like items, quests,
 * and dialogues from external files, and tests if saving and loading
 * the game state actually works.
 * * @author [Your Name/Student ID]
 * @version 1.0
 */
class FileManagerTest {

    /**
     * Tests loading random character or NPC names from a file.
     * Grabs the list of names and checks that the list actually exists (not null)
     * and that it has at least one name inside it (not empty).
     */
    @Test
    void loadNames() {
        List<String> names = FileManager.loadNames();
        assertNotNull(names);
        // Assuming names.txt exists and has content
        assertFalse(names.isEmpty());
    }

    /**
     * Tests loading all the quests from the game files.
     * Verifies that the FileManager returns a valid, non-empty list of Quest objects.
     */
    @Test
    void loadQuests() {
        List<Quest> quests = FileManager.loadQuests();
        assertNotNull(quests);
        assertFalse(quests.isEmpty());
    }

    /**
     * Tests loading all the items from the game files.
     * Verifies that the FileManager returns a valid, non-empty list of Item objects.
     */
    @Test
    void loadItems() {
        List<Item> items = FileManager.loadItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    /**
     * Tests loading the dialogue nodes for NPC conversations.
     * Verifies that the FileManager returns a valid, non-empty list of Node objects.
     */
    @Test
    void loadDialogues() {
        List<Node> nodes = FileManager.loadDialogues();
        assertNotNull(nodes);
        assertFalse(nodes.isEmpty());
    }

    /**
     * Tests loading the map obstacles from the game files.
     * Verifies that the FileManager returns a valid, non-empty list of Obstacle objects.
     */
    @Test
    void loadObstacles() {
        List<Obstacle> obstacles = FileManager.loadObstacles();
        assertNotNull(obstacles);
        assertFalse(obstacles.isEmpty());
    }

    /**
     * Tests the core save and load functionality of the game.
     * Creates a new game instance, sets the player's coordinates to X:5 and Y:5,
     * and saves the game. Then it loads that save file back into a new game
     * object and checks if those coordinates remained exactly the same.
     */
    @Test
    void saveAndLoadGame() {
        MyGame game = new MyGame();
        game.xCordinate = 5;
        game.yCordinate = 5;

        FileManager.saveGame(game);

        MyGame loadedGame = FileManager.loadGame();
        assertNotNull(loadedGame);
        assertEquals(5, loadedGame.xCordinate);
        assertEquals(5, loadedGame.yCordinate);
    }
}