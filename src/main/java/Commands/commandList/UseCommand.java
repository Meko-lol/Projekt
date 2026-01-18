package Commands.commandList;

import Commands.Command;

public class UseCommand extends Command {
    @Override
    public String execute() {
        return "You use...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
