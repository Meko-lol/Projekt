package Quest;

import Items.Item;

public class AquireingQuest extends Quest {
    private Item requiredItem;
    private boolean hasItem;

    public AquireingQuest(String name, String description, Item reward, Item requiredItem) {
        super(name, description, reward);
        this.requiredItem = requiredItem;
        this.hasItem = false;
    }
}
