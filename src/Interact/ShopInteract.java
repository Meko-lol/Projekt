package Interact;

import Items.Item;
import java.util.List;

public class ShopInteract extends Interact {
    public ShopInteract(String prompt, List<Answer> answers) {
        super(prompt, answers);
    }

    public void displayShopInventory() {}
    public void buy(Item item) {}
    public void sell(Item item) {}
}
