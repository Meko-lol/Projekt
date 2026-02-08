package Commands.commandList;

import Commands.Command;
import Game.ItemFinder;
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

        String itemName = "";
        String targetName = "";
        boolean hasTarget = false;

        int onIndex = -1;
        for (int i = 1; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("on")) {
                onIndex = i;
                break;
            }
        }

        if (onIndex != -1) {
            itemName = ItemFinder.joinArgs(args, 1).substring(0, ItemFinder.joinArgs(args, 1).indexOf(" on")).trim(); // Rough split
            // Better: reconstruct from args
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<onIndex; i++) sb.append(args[i]).append(" ");
            itemName = sb.toString().trim();
            
            sb = new StringBuilder();
            for(int i=onIndex+1; i<args.length; i++) sb.append(args[i]).append(" ");
            targetName = sb.toString().trim();
            hasTarget = true;
        } else {
            // "use [item]" or "use [item] [target]" without "on"
            // We'll use ItemFinder to try to match the item from the full string
            String fullArg = ItemFinder.joinArgs(args, 1);
            Item bestMatch = ItemFinder.findItem(player.getInventory().getAllItems(), fullArg);
            
            if (bestMatch != null) {
                itemName = bestMatch.getName();
                if (fullArg.length() > itemName.length()) {
                    targetName = fullArg.substring(itemName.length()).trim(); // This is risky if partial match
                    // Let's rely on the fact that ItemFinder finds the item.
                    // If the user typed "use pickaxe boulder", ItemFinder finds "Pickaxe".
                    // The rest "boulder" is the target.
                    // We need to be careful about partial matches.
                    // For simplicity in this refactor, let's assume if "on" isn't used, 
                    // we try to find the item in the first part.
                }
                 // Re-implementing the logic from before but using ItemFinder where possible
            }
            
            // ... (The previous logic was actually quite specific to splitting strings. 
            // ItemFinder helps find the item object, but splitting the string is the hard part.
            // I will keep the robust logic from the previous fix but use ItemFinder for the lookup.)
            
            // Re-using the logic from the previous successful fix for string parsing:
             fullArg = "";
            for (int i = 1; i < args.length; i++) {
                fullArg += args[i] + " ";
            }
            fullArg = fullArg.trim();

            bestMatch = null;
            for (Item item : player.getInventory().getAllItems()) {
                if (fullArg.toLowerCase().startsWith(item.getName().toLowerCase())) {
                    if (bestMatch == null || item.getName().length() > bestMatch.getName().length()) {
                        bestMatch = item;
                    }
                }
            }

            if (bestMatch != null) {
                itemName = bestMatch.getName();
                if (fullArg.length() > itemName.length()) {
                    targetName = fullArg.substring(itemName.length()).trim();
                    hasTarget = true;
                }
            } else {
                itemName = args[1];
                if (args.length > 2) {
                    targetName = ItemFinder.joinArgs(args, 2);
                    hasTarget = true;
                }
            }
        }

        Item itemToUse = ItemFinder.findItem(player.getInventory().getAllItems(), itemName);
        
        if (itemToUse == null) {
            return "You don't have anything like '" + itemName + "'.";
        }

        if (!hasTarget) {
            if (itemToUse.getType().equalsIgnoreCase("potion")) {
                player.setHealth(Math.min(100, player.getHealth() + 25));
                player.getInventory().removeItemByName(itemToUse.getName());
                return "You drank the " + itemToUse.getName() + ".";
            }
            return "Usage: use [item] on [target]";
        }
        
        Location currentLocation = game.getCurrentLocation();
        Obstacle obstacle = currentLocation.getObstacle(targetName);
        
        if (obstacle == null) {
             // Try finding obstacle by name
             for (String dir : currentLocation.getObstacles().keySet()) {
                Obstacle obs = currentLocation.getObstacles().get(dir);
                if (obs.getName().equalsIgnoreCase(targetName) || obs.getDescription().toLowerCase().contains(targetName.toLowerCase())) {
                    obstacle = obs;
                    targetName = dir;
                    break;
                }
            }
        }

        if (obstacle == null) {
            return "There is no '" + targetName + "' here.";
        }

        if (obstacle.getRequiredItemName().equalsIgnoreCase(itemToUse.getName())) {
            obstacle.decreaseDurability();
            if (obstacle.getDurability() <= 0) {
                currentLocation.removeObstacle(targetName);
                return "You use the " + itemToUse.getName() + " and clear the path!";
            } else {
                return "You use the " + itemToUse.getName() + ". It is damaged. (Durability: " + obstacle.getDurability() + ")";
            }
        } else {
            return "That doesn't work.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
