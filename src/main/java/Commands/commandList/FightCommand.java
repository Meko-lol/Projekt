package Commands.commandList;

import Battle.Battle;
import Characters.NPCs.NPC;
import Commands.Command;
import Items.Item; // Import Item
import Places.Location;
import Quest.Quest;
import Quest.KillingQuest;
import java.util.List; // Import List

public class FightCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        
        NPC targetEnemy = null;
        if (currentLocation.getNpcs() != null) {
            for (NPC npc : currentLocation.getNpcs()) {
                if (npc.isHostile()) {
                    targetEnemy = npc;
                    break;
                }
            }
        }

        if (targetEnemy == null) {
            return "There is nothing to fight here.";
        }

        Battle battle = new Battle(player, targetEnemy);
        boolean playerWasDefeated = battle.start();

        if (playerWasDefeated) {
            game.handlePlayerDefeat();
            return "";
        } else {
            // --- Handle Victory ---
            currentLocation.removeNpc(targetEnemy);
            
            // Drop Loot
            List<Item> loot = targetEnemy.getLoot();
            if (loot != null && !loot.isEmpty()) {
                for (Item item : loot) {
                    currentLocation.addItem(item);
                }
                System.out.println("The " + targetEnemy.getName() + " dropped its loot on the ground!");
            }
            
            // Update Quests
            for (Quest quest : player.getActiveQuests()) {
                if (quest instanceof KillingQuest) {
                    KillingQuest kQuest = (KillingQuest) quest;
                    if (kQuest.isTarget(targetEnemy.getName())) {
                        kQuest.incrementKills();
                        System.out.println("Quest progress: " + kQuest.getCurrentKills() + "/" + kQuest.getRequiredKills() + " " + kQuest.getTargetName() + "s killed.");
                    }
                }
            }
            
            return "You are victorious!";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
