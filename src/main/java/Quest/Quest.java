package Quest;

import Items.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = KillingQuest.class, name = "killing"),
    @JsonSubTypes.Type(value = TravelQuest.class, name = "travel"),
    @JsonSubTypes.Type(value = AquireingQuest.class, name = "aquireing")
})
public abstract class Quest {
    public String name;
    public String description;
    public Item reward;
    public String questGiverName; // The name of the NPC who gave the quest
    protected boolean isCompleted;

    public Quest() {}

    public Quest(String name, String description, Item reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.isCompleted = false;
    }

    public String getName() { return name; }
    public boolean isCompleted() { return isCompleted; }
    public void complete() { this.isCompleted = true; }

    public abstract Quest clone();
}
