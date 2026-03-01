package cz.meko.Game;

import cz.meko.Characters.Player;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEventManagerTest {

    @Test
    void handlePlayerDefeat() {
        GameEventManager manager = new GameEventManager();
        MyGame game = new MyGame();
        game.player = new Player("Tester", "Human", 0, 100, 10, 100, 10, 10, 10);
        game.gameMap = new MyMap(2, 2);
        
        Location prison = new Location("The Prison", null);
        prison.setX(1);
        prison.setY(1);
        game.gameMap.setLocation(1, 1, prison);
        game.gameMap.setLocation(0, 0, new Location("Start", null));

        manager.handlePlayerDefeat(game);

        assertEquals(20, game.player.getHealth());
        assertEquals(1, game.getXCordinate());
        assertEquals(1, game.getYCordinate());
        assertEquals("The Prison", game.getCurrentLocation().getName());
    }
}
