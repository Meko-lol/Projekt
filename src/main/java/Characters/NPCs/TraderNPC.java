package Characters.NPCs;

import Items.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraderNPC extends NPC {
    
    private Map<Item, Integer> itemsForSale; // Item and its price

    public TraderNPC(String name, String dialogueNodeName) {
        super(name, "Human", 100, 70, 5, 100, 5, 5, 10, dialogueNodeName, false);
        this.itemsForSale = new HashMap<>();
    }

    public Map<Item, Integer> getItemsForSale() {
        return itemsForSale;
    }

    public void addItemForSale(Item item, int price) {
        this.itemsForSale.put(item, price);
    }
}
