package Quest;

import Items.Item;

public class TravelQuest extends Quest {
    private String destinationName;
    private boolean hasArrived;

    public TravelQuest(String name, String description, Item reward, String destinationName) {
        super(name, description, reward);
        this.destinationName = destinationName;
        this.hasArrived = false;
    }

    public TravelQuest() {
        super();
    }

    public String getDestinationName() {
        return destinationName;
    }

    @Override
    public boolean isCompleted() {
        return hasArrived;
    }

    /**
     * Creates a new instance of this quest with the same properties.
     */
    @Override
    public Quest clone() {
        return new TravelQuest(this.name, this.description, this.reward, this.destinationName);
    }
}
