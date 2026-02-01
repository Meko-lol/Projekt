package Commands.commandList;

import Commands.Command;
import Items.Item;
import Items.EquippableItems.EquippableItem;
import Items.Weapons.CloseRangeWeapon;
import java.util.List;

public class EquipItemCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            List<Item> inventoryItems = player.getInventory().getAllItems();
            StringBuilder sb = new StringBuilder("What do you want to equip? Equippable items:\n");
            boolean found = false;
            for (Item item : inventoryItems) {
                if (item instanceof EquippableItem || item instanceof CloseRangeWeapon) {
                    sb.append("- ").append(item.getName()).append("\n");
                    found = true;
                }
            }
            if (!found) return "You have no equippable items.";
            return sb.toString();
        }
        
        String itemName = args[1];
        Item itemToEquip = player.getInventory().getItemByName(itemName);

        if (itemToEquip == null) {
            return "You don't have a '" + itemName + "'.";
        }

        if (itemToEquip instanceof CloseRangeWeapon) {
            player.setEquippedWeapon((CloseRangeWeapon) itemToEquip);
            return "You equip the " + itemName + ".";
        }
        
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
