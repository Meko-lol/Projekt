package Commands.commandList;

import Commands.Command;

public class BuyCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to buy?";
        }
        String itemName = args[1];

        //TODO: Check if the current interaction is with a trader NPC.
        //TODO: Check if the trader has the item in stock.
        //TODO: Check if the player has enough currency.
        //TODO: Handle the transaction (exchange item for currency).

        return "You can't buy things right now.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
