package Commands.commandList;

import Commands.Command;
import Items.Item;
import Places.Location;
import java.util.List;

public class PickUpCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to pick up?";
        }
        String itemName = args[1];
        Location currentLocation = game.getCurrentLocation();
        List<Item> itemsOnGround = currentLocation.getItemsOnGround();

        Item itemToPickUp = null;
        for (Item item : itemsOnGround) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToPickUp = item;
                break;
            }
        }

        if (itemToPickUp == null) {
            return "There is no '" + itemName + "' here.";
        }

        // Try to add the item to the player's inventory
        boolean added = player.getInventory().addItem(itemToPickUp); // Assumes addItem exists

        if (added) {
            currentLocation.removeItem(itemToPickUp);
            return "You picked up the " + itemName + ".";
        } else {
            return "Your inventory is full.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
