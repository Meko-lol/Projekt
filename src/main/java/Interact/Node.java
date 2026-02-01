package Interact;

import Items.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {
    private String name;       // The unique name of this node (e.g., "village_elder_start")
    private String monologue;  // The text the NPC says
    private String[] answers;    // The choices the player can make
    private String[] nextNodes;  // The names of the nodes that correspond to the answers
    private Item reward;       // An optional item reward

    // No-argument constructor for Jackson
    public Node() {}

    // Getters
    public String getName() { return name; }
    public String getMonologue() { return monologue; }
    public String[] getAnswers() { return answers; }
    public String[] getNextNodes() { return nextNodes; }
    public Item getReward() { return reward; }
}
