package Interact;

import Items.Item;

public class Node {
    private String name;
    private String monologue;
    private String[] answers;
    private String[] nextNodes;
    private Item giveItem; // An item to give the player at this node
    private boolean offerQuest; // A flag to trigger a quest offer at this node

    // Getters
    public String getName() { return name; }
    public String getMonologue() { return monologue; }
    public String[] getAnswers() { return answers; }
    public String[] getNextNodes() { return nextNodes; }
    public Item getGiveItem() { return giveItem; }
    public boolean isOfferQuest() { return offerQuest; }
}
