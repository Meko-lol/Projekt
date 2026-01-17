package Commands.commandList;

import Commands.Command;

public class PutOnBackpackCommand extends Command {
    @Override
    public String execute() {
        return "You put on the backpack.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
