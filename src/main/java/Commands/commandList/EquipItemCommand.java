package Commands.commandList;

import Commands.Command;
import Items.EquippableItems.EquippableItem;

public class EquipItemCommand extends Command {
    @Override
    public String execute() {
        // In a real implementation, you would get the item from the player's inventory
        // and check if it's an instance of EquippableItem.
        // For example:
        // if (args.length > 1) {
        //     String itemName = args[1];
        //     Item itemToEquip = player.inventory.findItem(itemName);
        //     if (itemToEquip instanceof EquippableItem) {
        //         ((EquippableItem) itemToEquip).equip();
        //         return "You equip the " + itemName;
        //     } else {
        //         return "You can't equip that!";
        //     }
        // }
        return "What do you want to equip?";
    }

    @Override
    public boolean exit() {
        return false;
    }

}
