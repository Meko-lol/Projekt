package cz.meko.Commands.commandList;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import cz.meko.GameMap.MyMap;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToCommandTest {

    private GoToCommand command;
    private MyGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        command = new GoToCommand();
        game = new MyGame();
        player = new Player();
        
        game.gameMap = new MyMap(2, 2);
        game.gameMap.setLocation(0, 0, new Location("Start", null));
        game.gameMap.setLocation(0, 1, new Location("South", null));
        game.xCordinate = 0;
        game.yCordinate = 0;
        game.setCurrentLocation(game.gameMap.getLocation(0, 0));
        
        command.setContext(player, game, new String[]{"move", "south"});
    }

    @Test
    void execute() {
        String result = command.execute();
        assertEquals("You moved south.", result);
        assertEquals(0, game.getXCordinate());
        assertEquals(1, game.getYCordinate());
    }
}
