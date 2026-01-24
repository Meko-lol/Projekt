package Places;

import Characters.NPCs.NPC;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location { // Renamed from Place
    private String name;
    private String[] obstacles;
    private NPC[] npcs;
    private Map<String, Integer> resources;

    public Location() {
        // No-argument constructor for Jackson
    }

    public Location(String name, String[] obstacles, NPC[] npcs, Map<String, Integer> resources) {
        this.name = name;
        this.obstacles = obstacles;
        this.npcs = npcs;
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getObstacles() {
        return obstacles;
    }

    public void setObstacles(String[] obstacles) {
        this.obstacles = obstacles;
    }

    public NPC[] getNpcs() {
        return npcs;
    }

    public void setNpcs(NPC[] npcs) {
        this.npcs = npcs;
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void setResources(Map<String, Integer> resources) {
        this.resources = resources;
    }
}
