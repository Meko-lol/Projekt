package Commands.commandList;

import Commands.Command;
import Items.Item;
import Places.Location;
import java.util.List;

public class PickUpCommand extends Command {
    
    private static final double MAX_WEIGHT = 50.0; // Define a weight limit

    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        List<Item> itemsOnGround = currentLocation.getItemsOnGround();

        if (args.length < 2) {
            if (itemsOnGround == null || itemsOnGround.isEmpty()) {
                return "There is nothing here to pick up.";
            }
            StringBuilder sb = new StringBuilder("What do you want to pick up? Available items:\n");
            for (Item item : itemsOnGround) {
                sb.append("- ").append(item.getName()).append(" (").append(item.getWeight()).append(" kg)\n");
            }
            return sb.toString();
        }

        String itemName = args[1];
        Item itemToPickUp = null;
        
        for (Item item : itemsOnGround) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToPickUp = item;
                break;
            }
        }

        if (itemToPickUp == null) {
            return "There is no '" + itemName + "' here.";
        }

        // THE FIX: Check weight limit
        double currentWeight = player.getInventory().getTotalWeight();
        if (currentWeight + itemToPickUp.getWeight() > MAX_WEIGHT) {
            return "You cannot pick that up. It's too heavy! (Current: " + currentWeight + "/" + MAX_WEIGHT + ")";
        }

        boolean addedSuccessfully = player.getInventory().addItem(itemToPickUp);

        if (addedSuccessfully) {
            currentLocation.removeItem(itemToPickUp);
            return "You picked up the " + itemName + ".";
        } else {
            return "Your inventory is full (no slots left).";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
