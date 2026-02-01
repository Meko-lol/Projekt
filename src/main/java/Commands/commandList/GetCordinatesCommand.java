package Commands.commandList;

import Commands.Command;

public class GetCordinatesCommand extends Command {
    @Override
    public String execute() {
        // Get the coordinates from the game instance.
        int x = game.getXCordinate();
        int y = game.getYCordinate();
        
        // THE FIX: Only return the string. Do not print it here.
        // The CommandProcessor is responsible for printing the final result.
        return "Your current cordinates are: [" + x + ", " + y + "]";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
