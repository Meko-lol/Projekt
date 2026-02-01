package Characters.NPCs;

import Characters.Character;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPC extends Character { // Made the class concrete

    private String dialogueNodeName;

    // No-argument constructor for Jackson
    public NPC(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        super();
    }

    public NPC(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence, String dialogueNodeName) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
        this.dialogueNodeName = dialogueNodeName;
    }

    public String getDialogueNodeName() {
        return dialogueNodeName;
    }

    public void setDialogueNodeName(String dialogueNodeName) {
        this.dialogueNodeName = dialogueNodeName;
    }
}
