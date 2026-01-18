package Commands.commandList;

import Commands.Command;

public class PickUpCommand extends Command {
    @Override
    public String execute() {
        return "You pick up...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
