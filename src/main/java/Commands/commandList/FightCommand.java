package Commands.commandList;

import Commands.Command;

public class FightCommand extends Command {
    @Override
    public String execute() {
        return "You start a fight!";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
