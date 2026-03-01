package cz.meko.Game;

import cz.meko.Characters.Player;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the UserInterface class.
 * This class checks if the text-based UI, specifically the dashboard,
 * generates correctly and displays the right information to the player.
 * * @author Petrák
 */
class UserInterfaceTest {

    /** * The UserInterface object we are testing.
     */
    private UserInterface ui;

    /** * A dummy game object to provide map and coordinate context to the UI.
     */
    private MyGame game;

    /** * A dummy player object to provide stats and name to the UI.
     */
    private Player player;

    /**
     * Setup method that runs before every test.
     * We initialize a tiny 2x2 map grid and a basic "Tester" player
     * so that the UI actually has some data to print out when we generate the dashboard.
     */
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

    /**
     * Tests the dashboard text generator.
     * Grabs the dashboard string from the UI and checks a few key things:
     * 1. It actually generated a string (not null).
     * 2. It shows the player's name ("Tester").
     * 3. It prints out the "World Map" section.
     * 4. It successfully placed the player marker "[P]" on the map grid.
     */
    @Test
    void getDashboard() {
        String dashboard = ui.getDashboard(game, player);
        assertNotNull(dashboard);
        assertTrue(dashboard.contains("Tester"));
        assertTrue(dashboard.contains("World Map"));
        assertTrue(dashboard.contains("[P]")); // Player position marker
    }
}