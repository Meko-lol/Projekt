package Commands.commandList;

import Commands.Command;
import Game.ItemFinder;
import Items.Item;
import java.util.List;

public class DropCommand extends Command {
    @Override
    public String execute() {
        List<Item> inventoryItems = player.getInventory().getAllItems();

        if (args.length < 2) {
            if (inventoryItems.isEmpty()) {
                return "You have nothing to drop.";
            }
            StringBuilder sb = new StringBuilder("What do you want to drop? Items in inventory:\n");
            for (Item item : inventoryItems) {
                sb.append("- ").append(item.getName()).append("\n");
            }
            return sb.toString();
        }

        String itemName = ItemFinder.joinArgs(args, 1);
        Item itemToDrop = ItemFinder.findItem(inventoryItems, itemName);

        if (itemToDrop == null) {
            return "You don't have a '" + itemName + "'.";
        }
        
        // We need to remove it by exact object reference or name, ItemFinder gives us the object.
        // Inventory.removeItemByName uses name, which is fine.
        Item droppedItem = player.getInventory().removeItemByName(itemToDrop.getName());

        if (droppedItem != null) {
            player.unequipIfEquipped(droppedItem);
            game.getCurrentLocation().addItem(droppedItem);
            return "You dropped the " + droppedItem.getName() + ".";
        } else {
            return "Error dropping item.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
