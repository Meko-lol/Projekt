package Commands.commandList;

import Commands.Command;
import Items.Item;
import Items.EquippableItems.Backpack;
import Items.EquippableItems.Pants;
import Items.Items.EquippableItems.Boots;
import Items.Items.EquippableItems.Chestplate;
import Items.Items.EquippableItems.Helmet;
import Items.Weapons.CloseRangeWeapon;
import Places.Location;

public class DevCommand extends Command {
    
    private static boolean godMode = false;

    @Override
    public String execute() {
        if (args.length < 2) {
            return "Dev Commands:\n" +
                   "- dev give [item name]\n" +
                   "- dev heal\n" +
                   "- dev teleport [x] [y]\n" +
                   "- dev god\n" +
                   "- dev money [amount]\n" +
                   "- dev reveal (shows map)";
        }

        String subCommand = args[1].toLowerCase();

        switch (subCommand) {
            case "give":
                return handleGive();
            case "heal":
                player.setHealth(100);
                player.setStamina(100);
                return "Player healed to full.";
            case "teleport":
                return handleTeleport();
            case "god":
                godMode = !godMode;
                return "God Mode is now " + (godMode ? "ON" : "OFF");
            case "money":
                return handleMoney();
            case "reveal":
                return "Map revealed (not implemented yet, but you can see coordinates in dashboard).";
            default:
                return "Unknown dev command.";
        }
    }

    private String handleGive() {
        if (args.length < 3) return "Usage: dev give [item name]";
        
        String itemName = "";
        for (int i = 2; i < args.length; i++) {
            itemName += args[i] + " ";
        }
        itemName = itemName.trim();

        // Simple factory for testing items
        Item item = null;
        if (itemName.equalsIgnoreCase("sword")) item = new CloseRangeWeapon("Dev Sword", 1, 999, "OP Sword", 100);
        else if (itemName.equalsIgnoreCase("helmet")) item = new Helmet("Dev Helmet", 1, 999, "OP Helmet", 100);
        else if (itemName.equalsIgnoreCase("potion")) item = new Item("Health Potion", "potion", 0.5, 1, "Restores 25 health.");
        else if (itemName.equalsIgnoreCase("backpack")) item = new Backpack("Dev Backpack", 1, 999, "Big Bag", 100);
        else {
            // Try to find a template item
            // This would require access to the item templates list, which isn't easily accessible here.
            // So we'll just create a generic item.
            item = new Item(itemName, "misc", 1, 1, "Spawned item");
        }

        player.getInventory().addItem(item);
        return "Gave " + item.getName();
    }

    private String handleTeleport() {
        if (args.length < 4) return "Usage: dev teleport [x] [y]";
        try {
            int x = Integer.parseInt(args[2]);
            int y = Integer.parseInt(args[3]);
            
            Location target = game.getGameMap().getLocation(x, y);
            if (target != null) {
                game.xCordinate = x;
                game.yCordinate = y;
                game.setCurrentLocation(target);
                return "Teleported to " + x + ", " + y;
            } else {
                return "Invalid coordinates.";
            }
        } catch (NumberFormatException e) {
            return "Coordinates must be numbers.";
        }
    }

    private String handleMoney() {
        if (args.length < 3) return "Usage: dev money [amount]";
        try {
            int amount = Integer.parseInt(args[2]);
            player.addMoney(amount);
            return "Added " + amount + " gold.";
        } catch (NumberFormatException e) {
            return "Amount must be a number.";
        }
    }

    public static boolean isGodMode() {
        return godMode;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
