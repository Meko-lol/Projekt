package Commands.commandList;

import Commands.Command;

public class GetCordinatesCommand extends Command {
    @Override
    public String execute() {
        // Access the game object provided by the Command base class
        int x = game.getXCordinate();
        int y = game.getYCordinate();

        System.out.println("Your current cordinates are: [" + x + ", " + y + "]");
        // The execute method should return a string to be printed by the CommandProcessor.
        return "Your current cordinates are: [" + x + ", " + y + "]";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
