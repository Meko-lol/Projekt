package Commands.commandList;

import Commands.Command;

public class OpenInventoryCommand extends Command {
    @Override
    public String execute() {
        return "You open your inventory.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
