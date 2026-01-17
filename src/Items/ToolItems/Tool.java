package Items.ToolItems;

import Items.Item;

public abstract class Tool extends Item {
    public Tool(String name, double weight, double durability, String description) {
        super(name, weight, durability, description);
    }
}
