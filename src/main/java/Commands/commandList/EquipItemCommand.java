package Commands.commandList;

import Commands.Command;
import Items.Item;
import Items.EquippableItems.EquippableItem;

public class EquipItemCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to equip?";
        }
        String itemName = args[1];

        //TODO: Find the item in the player's inventory.
        //TODO: Check if the item is an instance of EquippableItem.
        //TODO: Call an 'equip' method on the item and handle the result.
        
        return "You try to equip the " + itemName + ", but you can't.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
