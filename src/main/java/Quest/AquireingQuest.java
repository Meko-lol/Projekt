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

    // A no-argument constructor is needed for Jackson.
    public AquireingQuest() {
        super();
    }

    /**
     * Creates a new instance of this quest with the same properties.
     */
    @Override
    public Quest clone() {
        return new AquireingQuest(this.name, this.description, this.reward, this.requiredItem);
    }

    @Override
    public boolean isCompleted() {
        // In a real game, you would check the player's inventory for the requiredItem.
        return hasItem;
    }
}
