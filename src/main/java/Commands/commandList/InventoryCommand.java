package Commands.commandList;

import Commands.Command;
import Inventory.Inventory;
import Items.Item;
import java.util.List;

public class InventoryCommand extends Command {
    @Override
    public String execute() {
        Inventory inventory = player.getInventory();
        if (inventory == null) {
            return "You have no inventory.";
        }

        // Use the new, clean method to get all items.
        List<Item> items = inventory.getAllItems();

        if (items.isEmpty()) {
            return "Your inventory is empty.";
        }

        StringBuilder sb = new StringBuilder("Your Inventory:\n");
        for (Item item : items) {
            sb.append("- ").append(item.getName()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
