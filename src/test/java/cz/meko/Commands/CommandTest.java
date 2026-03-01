package cz.meko.Commands;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    private Command command;

    @BeforeEach
    void setUp() {
        command = new Command() {
            @Override
            public String execute() {
                return "Executed";
            }

            @Override
            public boolean exit() {
                return false;
            }
        };
    }

    @Test
    void setContext() {
        Player player = new Player();
        MyGame game = new MyGame();
        String[] args = {"test"};
        
        assertDoesNotThrow(() -> command.setContext(player, game, args));
    }
    
    @Test
    void setContextNulls() {
        assertDoesNotThrow(() -> command.setContext(null, null, null));
    }

    @Test
    void execute() {
        assertEquals("Executed", command.execute());
    }
}
