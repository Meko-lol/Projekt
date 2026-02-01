package Commands.commandList;

import Characters.NPCs.NPC;
import Characters.NPCs.TraderNPC;
import Commands.Command;
import Items.Item;
import Places.Location;
import java.util.Map;

public class BuyCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        TraderNPC trader = null;

        // Find a trader at the current location
        for (NPC npc : currentLocation.getNpcs()) {
            if (npc instanceof TraderNPC) {
                trader = (TraderNPC) npc;
                break;
            }
        }

        if (trader == null) {
            return "There is no one to buy from here.";
        }

        Map<Item, Integer> itemsForSale = trader.getItemsForSale();

        // If the player just typed "buy", show the items for sale
        if (args.length < 2) {
            StringBuilder sb = new StringBuilder("--- " + trader.getName() + "'s Wares ---\n");
            for (Map.Entry<Item, Integer> entry : itemsForSale.entrySet()) {
                sb.append(String.format("- %s (%d Gold)\n", entry.getKey().getName(), entry.getValue()));
            }
            sb.append("---------------------\n");
            sb.append("To buy an item, type 'buy [item name]'.");
            return sb.toString();
        }

        // If the player specified an item to buy
        String itemName = args[1];
        for (Map.Entry<Item, Integer> entry : itemsForSale.entrySet()) {
            if (entry.getKey().getName().equalsIgnoreCase(itemName)) {
                Item itemToBuy = entry.getKey();
                int price = entry.getValue();

                if (player.getMoney() >= price) {
                    if (player.getInventory().addItem(itemToBuy)) {
                        player.spendMoney(price);
                        return "You bought the " + itemToBuy.getName() + " for " + price + " gold.";
                    } else {
                        return "Your inventory is full!";
                    }
                } else {
                    return "You don't have enough money. You need " + price + " gold.";
                }
            }
        }

        return "That item is not for sale.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
