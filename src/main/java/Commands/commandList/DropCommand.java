package Commands.commandList;

import Commands.Command;
import Items.Item;
import Places.Location;

public class DropCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to drop?";
        }
        String itemName = args[1];
        
        // Remove the item from the player's inventory
        Item droppedItem = player.getInventory().removeItemByName(itemName); // Assumes removeItemByName exists

        if (droppedItem != null) {
            // Add the item to the ground at the current location
            game.getCurrentLocation().addItem(droppedItem);
            return "You dropped the " + itemName + ".";
        } else {
            return "You don't have a '" + itemName + "'.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
