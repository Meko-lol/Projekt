package Commands.commandList;

import Commands.Command;
import Items.Item;
import Places.Location;
import Places.Obstacle;
import java.util.List;

public class UseCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            List<Item> inventoryItems = player.getInventory().getAllItems();
            if (inventoryItems.isEmpty()) {
                return "You have nothing to use.";
            }
            StringBuilder sb = new StringBuilder("What do you want to use? Items in inventory:\n");
            for (Item item : inventoryItems) {
                sb.append("- ").append(item.getName()).append("\n");
            }
            return sb.toString();
        }

        String itemName = args[1];
        Item itemToUse = player.getInventory().getItemByName(itemName);
        
        if (itemToUse == null) {
            return "You don't have a '" + itemName + "'.";
        }

        // THE FIX: Handle self-use items like potions
        if (args.length < 4) {
            if (itemToUse.getType().equalsIgnoreCase("potion")) {
                // Simple potion logic: heal 25 HP
                player.setHealth(Math.min(100, player.getHealth() + 25));
                player.getInventory().removeItemByName(itemName);
                return "You drank the " + itemName + " and feel refreshed. (Health: " + String.format("%.0f", player.getHealth()) + ")";
            }
            return "Usage: use [item] on [target] (e.g., 'use axe on east')";
        }
        
        String target = args[3];
        Location currentLocation = game.getCurrentLocation();
        Obstacle obstacle = currentLocation.getObstacle(target);

        if (obstacle == null) {
            return "There is nothing to use your " + itemName + " on in that direction.";
        }

        if (obstacle.getRequiredItemName().equalsIgnoreCase(itemToUse.getName())) {
            obstacle.decreaseDurability();
            if (obstacle.getDurability() <= 0) {
                currentLocation.removeObstacle(target);
                return "You use the " + itemName + " and clear the " + obstacle.getDescription().toLowerCase() + " The path is now open!";
            } else {
                return "You use the " + itemName + " on the obstacle. It is damaged but not yet broken. (Durability: " + obstacle.getDurability() + ")";
            }
        } else {
            return "Using a " + itemName + " on that doesn't seem to do anything.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
