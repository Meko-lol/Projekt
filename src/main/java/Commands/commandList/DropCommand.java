package Commands.commandList;

import Commands.Command;
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

        String itemName = args[1];
        Item droppedItem = player.getInventory().removeItemByName(itemName);

        if (droppedItem != null) {
            // THE FIX: Ensure the item is unequipped if the player was wearing it.
            player.unequipIfEquipped(droppedItem);

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
