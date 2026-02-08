package Commands.commandList;

import Battle.Battle;
import Characters.NPCs.NPC;
import Commands.Command;
import Game.NPCFinder; // Import NPCFinder
import Items.Item;
import Places.Location;
import Quest.Quest;
import Quest.KillingQuest;
import java.util.List;

public class FightCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        List<NPC> npcs = currentLocation.getNpcs();

        if (args.length < 2) {
            if (npcs == null || npcs.isEmpty()) {
                return "There is no one here to fight.";
            }
            StringBuilder sb = new StringBuilder("Who do you want to fight? Hostile targets:\n");
            boolean found = false;
            for (NPC npc : npcs) {
                if (npc.isHostile()) {
                    sb.append("- ").append(npc.getName()).append("\n");
                    found = true;
                }
            }
            if (!found) return "There are no hostile enemies here.";
            return sb.toString();
        }

        String targetName = args[1];
        // THE FIX: Use NPCFinder
        NPC targetEnemy = NPCFinder.findNPC(npcs, targetName);

        if (targetEnemy == null || !targetEnemy.isHostile()) {
            return "There is no hostile '" + targetName + "' here.";
        }

        Battle battle = new Battle(player, targetEnemy);
        boolean playerWasDefeated = battle.start();

        if (playerWasDefeated) {
            game.handlePlayerDefeat();
            return "";
        } else {
            currentLocation.removeNpc(targetEnemy);
            
            int goldReward = 75;
            player.addMoney(goldReward);
            System.out.println("You received " + goldReward + " gold!");
            
            List<Item> loot = targetEnemy.getLoot();
            if (loot != null && !loot.isEmpty()) {
                for (Item item : loot) {
                    currentLocation.addItem(item);
                }
                System.out.println("The " + targetEnemy.getName() + " dropped loot!");
            }
            
            for (Quest quest : player.getActiveQuests()) {
                if (quest instanceof KillingQuest) {
                    KillingQuest kQuest = (KillingQuest) quest;
                    if (kQuest.isTarget(targetEnemy.getName())) {
                        kQuest.incrementKills();
                        System.out.println("Quest progress: " + kQuest.getCurrentKills() + "/" + kQuest.getRequiredKills());
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
