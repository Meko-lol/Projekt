package cz.meko.Quest;

import cz.meko.Items.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract base class for all quests.
 * Defines common attributes like name, description, and reward.
 * Uses Jackson annotations for polymorphism.
 * 
 * @author Jan Petrak
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = KillingQuest.class, name = "killing"),
    @JsonSubTypes.Type(value = AquireingQuest.class, name = "aquireing"),
    @JsonSubTypes.Type(value = TravelQuest.class, name = "travel")
})
public abstract class Quest {
    public String name;
    public String description;
    public Item reward;
    public String questGiverName;
    private boolean completed;

    public Quest(String name, String description, Item reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.completed = false;
    }

    public Quest() {}

    /**
     * Checks if the quest is completed.
     * @return True if completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the quest as completed.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * Creates a clone of the quest.
     * @return A new instance of the quest.
     */
    @JsonIgnore
    public abstract Quest clone();
}
