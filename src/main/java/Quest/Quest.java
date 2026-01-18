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
}
