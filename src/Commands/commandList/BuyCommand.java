package Commands.commandList;

import Commands.Command;

public class BuyCommand extends Command {
    @Override
    public String execute() {
        return "You buy...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
