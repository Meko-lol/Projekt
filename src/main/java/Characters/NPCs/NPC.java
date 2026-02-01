package Characters.NPCs;

import Characters.Character;
import Items.Item; // Import Item
import Quest.Quest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList; // Import ArrayList
import java.util.List; // Import List

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPC extends Character {

    private String dialogueNodeName;
    private boolean isHostile;
    private Quest questToGive;
    private List<Item> loot; // THE FIX: A list of items this NPC will drop on defeat.

    public NPC() {
        super();
        this.loot = new ArrayList<>(); // Initialize the list
    }

    public NPC(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence, String dialogueNodeName, boolean isHostile) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
        this.dialogueNodeName = dialogueNodeName;
        this.isHostile = isHostile;
        this.loot = new ArrayList<>(); // Initialize the list
    }

    // Getters and Setters
    public String getDialogueNodeName() { return dialogueNodeName; }
    public void setDialogueNodeName(String dialogueNodeName) { this.dialogueNodeName = dialogueNodeName; }
    public boolean isHostile() { return isHostile; }
    public void setHostile(boolean hostile) { isHostile = hostile; }
    public Quest getQuestToGive() { return questToGive; }
    public void setQuestToGive(Quest quest) { this.questToGive = quest; }
    public List<Item> getLoot() { return loot; }
    public void setLoot(List<Item> loot) { this.loot = loot; }
    public void addLoot(Item item) { this.loot.add(item); }
}
