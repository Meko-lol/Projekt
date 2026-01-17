package Commands.commandList;

import Commands.Command;

public class DropCommand extends Command {
    @Override
    public String execute() {
        return "You drop...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
