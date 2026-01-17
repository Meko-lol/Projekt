package Commands.commandList;

import Commands.Command;

public class HelpCommand extends Command {
    @Override
    public String execute() {
        // This will be populated later with all available commands
        return "Available commands: ";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
