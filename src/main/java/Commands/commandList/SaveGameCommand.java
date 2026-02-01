package Commands.commandList;

import Commands.Command;

public class SaveGameCommand extends Command {
    @Override
    public String execute() {
        // Call the public saveGame() method on the game instance
        game.saveGame();
        return "Game saved successfully. Quitting.";
    }

    @Override
    public boolean exit() {
        // Return true to tell the CommandProcessor to stop the game loop.
        return true;
    }
}
