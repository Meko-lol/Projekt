package Commands.commandList;

import Commands.Command;

public class SellCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to sell?";
        }
        String itemName = args[1];

        //TODO: Check if the current interaction is with a trader NPC.
        //TODO: Check if the player has the item in their inventory.
        //TODO: Handle the transaction (exchange currency for item).

        return "There is no one here to sell to.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
