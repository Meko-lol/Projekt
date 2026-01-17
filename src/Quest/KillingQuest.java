package Quest;

import Characters.NPCs.Enemy;
import Items.Item;

public class KillingQuest extends Quest {
    private Enemy target;
    private int requiredKills;
    private int currentKills;

    public KillingQuest(String name, String description, Item reward, Enemy target, int requiredKills) {
        super(name, description, reward);
        this.target = target;
        this.requiredKills = requiredKills;
        this.currentKills = 0;
    }
}
