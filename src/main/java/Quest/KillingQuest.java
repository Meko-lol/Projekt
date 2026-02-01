package Quest;

import Items.Item;

public class KillingQuest extends Quest {
    private String targetName; 
    private int requiredKills;
    private int currentKills;

    public KillingQuest(String name, String description, Item reward, String targetName, int requiredKills) {
        super(name, description, reward);
        this.targetName = targetName;
        this.requiredKills = requiredKills;
        this.currentKills = 0;
    }

    public KillingQuest() {
        super();
    }

    public String getTargetName() { return targetName; }
    public int getRequiredKills() { return requiredKills; }
    public int getCurrentKills() { return currentKills; }
    public void incrementKills() { this.currentKills++; }

    public boolean isTarget(String enemyName) {
        return this.targetName.equalsIgnoreCase(enemyName);
    }

    @Override
    public boolean isCompleted() {
        return currentKills >= requiredKills;
    }

    @Override
    public Quest clone() {
        return new KillingQuest(this.name, this.description, this.reward, this.targetName, this.requiredKills);
    }
}
