package cz.meko.Game;

import cz.meko.Characters.Player;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    private UserInterface ui;
    private MyGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        ui = new UserInterface();
        game = new MyGame();
        game.gameMap = new MyMap(2, 2);
        for(int y=0; y<2; y++) {
            for(int x=0; x<2; x++) {
                game.gameMap.setLocation(x, y, new Location("Loc", null));
            }
        }
        player = new Player("Tester", "Human", 100, 100, 10, 100, 10, 10, 10);
    }

    @Test
    void getDashboard() {
        String dashboard = ui.getDashboard(game, player);
        assertNotNull(dashboard);
        assertTrue(dashboard.contains("Tester"));
        assertTrue(dashboard.contains("World Map"));
        assertTrue(dashboard.contains("[P]")); // Player position marker
    }
}
