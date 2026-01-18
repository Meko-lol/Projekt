package Commands.commandList;

import Commands.Command;

public class QuitCommand extends Command {
    @Override
    public String execute() {
        return "Exiting game. Goodbye!";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
