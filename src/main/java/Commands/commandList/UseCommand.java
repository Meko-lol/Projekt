package Commands.commandList;

import Commands.Command;
import Items.Item;

public class UseCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "What do you want to use?";
        }
        String itemName = args[1];
        
        Item itemToUse = player.getInventory().getItemByName(itemName); // Assumes getItemByName exists

        if (itemToUse == null) {
            return "You don't have a '" + itemName + "'.";
        }

        if ("potion".equalsIgnoreCase(itemToUse.getType())) {
            player.setHealth(player.getHealth() + 25); // Heal the player
            player.getInventory().removeItemByName(itemName); // Consume the potion
            return "You drink the " + itemName + " and feel refreshed. Your health is now " + player.getHealth() + ".";
        }
        
        return "You can't use the " + itemName + " that way.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
