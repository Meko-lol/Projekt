package Commands.commandList;

import Commands.Command;
import Items.Item;
import java.util.List;

public class InventoryCommand extends Command {
    @Override
    public String execute() {
        List<Item> items = player.getInventory().getAllItems();
        
        if (items.isEmpty()) {
            return "Your inventory is empty.";
        }

        StringBuilder sb = new StringBuilder("--- Inventory ---\n");
        for (Item item : items) {
            sb.append(String.format("- %-20s (Weight: %.1f)\n", item.getName(), item.getWeight()));
        }
        sb.append("-----------------");
        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
