package Characters.NPCs;

import Characters.Character;
import Items.Item;
import Quest.Quest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPC extends Character {

    private String dialogueNodeName;
    private boolean isHostile;
    private Quest questToGive;
    private List<Item> loot = new ArrayList<>();

    public NPC() {
        super();
    }

    public NPC(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence, String dialogueNodeName, boolean isHostile) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
        this.dialogueNodeName = dialogueNodeName;
        this.isHostile = isHostile;
    }

    // Getters
    public String getDialogueNodeName() { return dialogueNodeName; }
    public boolean isHostile() { return isHostile; }
    public Quest getQuestToGive() { return questToGive; }
    public List<Item> getLoot() { return loot; }

    // Setters
    public void setDialogueNodeName(String dialogueNodeName) { this.dialogueNodeName = dialogueNodeName; }
    public void setHostile(boolean hostile) { this.isHostile = hostile; }
    public void setQuestToGive(Quest quest) { this.questToGive = quest; }
    public void setLoot(List<Item> loot) { this.loot = (loot != null) ? loot : new ArrayList<>(); }
    
    // Logic
    public void addLoot(Item item) { this.loot.add(item); }
}
