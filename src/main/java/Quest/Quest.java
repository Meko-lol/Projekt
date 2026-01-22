package Quest;

import Items.Item;

public abstract class Quest {
    protected String name;
    protected String description;
    protected boolean isCompleted;
    protected Item reward;

    public Quest(String name, String description, Item reward) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
        this.reward = reward;
    }

    public void complete() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Item getReward() {
        return reward;
    }

    public void setReward(Item reward) {
        this.reward = reward;
    }
}
