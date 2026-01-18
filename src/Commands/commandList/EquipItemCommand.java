package Commands.commandList;

import Commands.Command;
import Items.EquippableItems.EquippableItem;

public class EquipItemCommand extends Command {
    @Override
    public String execute() {
        // In a real implementation, you would get the item from the player's inventory
        // based on the command arguments (e.g., "equip steel_helmet").
        // For example:
        // if (args.length > 1) {
        //     String itemName = args[1];
        //     Item itemToEquip = player.inventory.findItem(itemName);
        //     if (itemToEquip instanceof EquippableItem) {
        //         return ((EquippableItem) itemToEquip).equip();
        //     } else {
        //         return "You can't equip that!";
        //     }
        // }
        return "What do you want to equip?";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
