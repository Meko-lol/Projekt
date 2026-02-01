package Commands.commandList;

import Commands.Command;
import Items.Item;
import Places.Location;
import Places.Obstacle;

public class UseCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 4 || !args[2].equalsIgnoreCase("on")) {
            return "What do you want to use, and on what? (e.g., 'use axe on east')";
        }
        String itemName = args[1];
        String target = args[3];

        Item itemToUse = player.getInventory().getItemByName(itemName);
        if (itemToUse == null) {
            return "You don't have a '" + itemName + "'.";
        }

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
