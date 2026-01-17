package Commands.commandList;

import Commands.Command;

public class InteractCommand extends Command {
    @Override
    public String execute() {
        return "You interact with...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
