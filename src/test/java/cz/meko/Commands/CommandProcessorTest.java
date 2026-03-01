package cz.meko.Commands;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {

    @Test
    void instantiation() {
        Player player = new Player();
        MyGame game = new MyGame();
        CommandProcessor processor = new CommandProcessor(player, game);
        assertNotNull(processor);
    }
}
