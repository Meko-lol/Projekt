package Commands.commandList;

import Commands.Command;

public class HelpCommand extends Command {
    @Override
    public String execute() {
        // This string contains all the commands the player can use.
        return "Available commands: help, locationinfo, cordinates, move, interact, pickup, drop, inventory, use, equip, fight, save, quit, end";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
