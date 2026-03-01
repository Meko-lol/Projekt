package cz.meko.Commands.commandList;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import cz.meko.Items.Item;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PickUpCommandTest {

    private PickUpCommand command;
    private MyGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        command = new PickUpCommand();
        game = new MyGame();
        player = new Player();
        
        Location loc = new Location("Loc", null);
        loc.addItem(new Item("Rock", "misc", 1, 1, "Rock"));
        game.setCurrentLocation(loc);
        
        command.setContext(player, game, new String[]{"pickup", "Rock"});
    }

    @Test
    void execute() {
        String result = command.execute();
        assertTrue(result.contains("picked up"));
        assertNotNull(player.getInventory().getItemByName("Rock"));
    }
}
