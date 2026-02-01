package Commands.commandList;

import Commands.Command;
import Items.Item;
import Items.EquippableItems.EquippableItem;
import Items.Weapons.CloseRangeWeapon;

public class EquipItemCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to equip?";
        }
        String itemName = args[1];

        Item itemToEquip = player.getInventory().getItemByName(itemName);

        if (itemToEquip == null) {
            return "You don't have a '" + itemName + "'.";
        }

        // Handle weapons
        if (itemToEquip instanceof CloseRangeWeapon) {
            player.setEquippedWeapon((CloseRangeWeapon) itemToEquip);
            return "You equip the " + itemName + ".";
        }
        
        // THE FIX: Use the single, all-purpose equipItem method.
        if (itemToEquip instanceof EquippableItem) {
            EquippableItem equippable = (EquippableItem) itemToEquip;
            EquippableItem oldItem = player.equipItem(equippable);
            
            String message = "You equip the " + itemName + ".";
            if (oldItem != null) {
                message += " You unequipped the " + oldItem.getName() + ".";
            }
            return message;
        }

        return "You can't equip that.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
