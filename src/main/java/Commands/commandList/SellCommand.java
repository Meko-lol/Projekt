package Commands.commandList;

import Commands.Command;

public class SellCommand extends Command {
    @Override
    public String execute() {
        return "You sell...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
