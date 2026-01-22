package Places;

import Characters.NPCs.NPC;
import java.util.Map;

public class PolulatedPlace extends Place {
    private int villageDensity;

    public PolulatedPlace(String name, String[] obstacles, NPC[] npcs, Map<String, Integer> resources, int villageDensity) {
        super(name, obstacles, npcs, resources);
        this.villageDensity = villageDensity;
    }

    public int getVillageDensity() {
        return villageDensity;
    }

    public void setVillageDensity(int villageDensity) {
        this.villageDensity = villageDensity;
    }
}
