package Commands.commandList;

import Commands.Command;

public class RemoveBackpackCommand extends Command {
    @Override
    public String execute() {
        return "You take off the backpack.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
