package cz.meko.Commands.commandList;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FightCommandTest {

    private FightCommand command;
    private MyGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        command = new FightCommand();
        game = new MyGame();
        player = new Player("Hero", "Human", 100, 100, 10, 100, 10, 10, 10);
        
        Location loc = new Location("Arena", null);
        loc.addNpc(new NPC("Goblin", "Goblin", 10, 10, 1, 10, 1, 1, 1, null, true));
        game.setCurrentLocation(loc);
        
        command.setContext(player, game, new String[]{"fight", "Goblin"});
    }

    @Test
    void execute() {
        String result = command.execute();
        assertTrue(result.contains("victorious"));
    }
}
