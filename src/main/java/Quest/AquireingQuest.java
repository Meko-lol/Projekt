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

    public AquireingQuest() {
        super();
    }

    // THE FIX: Added the missing getter method.
    public Item getRequiredItem() {
        return requiredItem;
    }

    @Override
    public Quest clone() {
        return new AquireingQuest(this.name, this.description, this.reward, this.requiredItem);
    }

    @Override
    public boolean isCompleted() {
        return hasItem;
    }
    
    @Override
    public void complete() {
        this.hasItem = true;
        super.complete();
    }
}
