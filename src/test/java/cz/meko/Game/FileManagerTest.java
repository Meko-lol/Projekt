package cz.meko.Game;

import cz.meko.Interact.Node;
import cz.meko.Items.Item;
import cz.meko.Places.Obstacle;
import cz.meko.Quest.Quest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void loadNames() {
        List<String> names = FileManager.loadNames();
        assertNotNull(names);
        // Assuming names.txt exists and has content
        assertFalse(names.isEmpty());
    }

    @Test
    void loadQuests() {
        List<Quest> quests = FileManager.loadQuests();
        assertNotNull(quests);
        assertFalse(quests.isEmpty());
    }

    @Test
    void loadItems() {
        List<Item> items = FileManager.loadItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    void loadDialogues() {
        List<Node> nodes = FileManager.loadDialogues();
        assertNotNull(nodes);
        assertFalse(nodes.isEmpty());
    }

    @Test
    void loadObstacles() {
        List<Obstacle> obstacles = FileManager.loadObstacles();
        assertNotNull(obstacles);
        assertFalse(obstacles.isEmpty());
    }

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
