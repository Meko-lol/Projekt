package Commands.commandList;

import Commands.Command;

public class EndCommand extends Command {
    @Override
    public String execute() {
        return "Ending the process. Goodbye!";
    }

    @Override
    public boolean exit() {
        return false;
    }

}
