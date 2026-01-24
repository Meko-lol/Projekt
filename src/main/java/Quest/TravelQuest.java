package Quest;

import Items.Item;
import Places.Location; // Changed from Places.Place

public class TravelQuest extends Quest {
    private Location destination; // Changed from Place
    private boolean hasArrived;

    public TravelQuest(String name, String description, Item reward, Location destination) { // Changed from Place
        super(name, description, reward);
        this.destination = destination;
        this.hasArrived = false;
    }
}
