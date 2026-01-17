package Quest;

import Items.Item;
import Places.Place;

public class TravelQuest extends Quest {
    private Place destination;
    private boolean hasArrived;

    public TravelQuest(String name, String description, Item reward, Place destination) {
        super(name, description, reward);
        this.destination = destination;
        this.hasArrived = false;
    }
}
