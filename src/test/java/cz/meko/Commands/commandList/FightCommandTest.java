package cz.meko.Commands.commandList;

import cz.meko.Characters.NPCs.NPC;
import cz.meko.Characters.Player;
import cz.meko.Game.MyGame;
import cz.meko.Places.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the FightCommand class.
 * This class verifies that the "fight" command correctly identifies
 * an enemy in the current location and triggers a battle.
 * @author Petrák
 */
class FightCommandTest {

    /** * The fight command instance being tested. */
    private FightCommand command;

    /** * The game instance containing the current location data. */
    private MyGame game;

    /** * The player character starting the fight. */
    private Player player;

    /**
     * Sets up the combat scenario before each test.
     * Creates a player and an "Arena" location containing a weak Goblin.
     * Then, it prepares the command with the context of fighting that specific Goblin.
     */
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

    /**
     * Tests the execution of the fight command.
     * Since the Goblin is very weak and the player is strong, the battle
     * should end quickly. This checks if the returned message contains
     * the word "victorious" to confirm the player won the fight.
     */
    @Test
    void execute() {
        String result = command.execute();
        assertTrue(result.contains("victorious"));
    }
}