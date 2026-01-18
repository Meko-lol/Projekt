package Commands.commandList;

import Commands.Command;

public class OpenBackpackCommand extends Command {
    @Override
    public String execute() {
        return "You open your backpack.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
