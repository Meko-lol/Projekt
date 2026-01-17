package Commands.commandList;

import Commands.Command;

public class GoToCommand extends Command {
    @Override
    public String execute() {
        return "You go to...";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
