package Commands.commandList;

import Commands.Command;
import Places.Location;
import Characters.NPCs.NPC;
import Interact.InteractHandler;
import Interact.Node;
import Quest.Quest;
import Quest.AquireingQuest; // Import the specific quest type
import Items.Item;
import java.util.List;
import java.util.Scanner;

public class InteractCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        List<NPC> npcs = currentLocation.getNpcs();

        if (args.length < 2) {
            if (npcs == null || npcs.isEmpty()) {
                return "There is no one here to interact with.";
            }
            StringBuilder sb = new StringBuilder("Who do you want to interact with? People here:\n");
            for (NPC npc : npcs) {
                if (!npc.isHostile()) {
                    sb.append("- ").append(npc.getName()).append("\n");
                }
            }
            return sb.toString();
        }

        String npcName = args[1];
        NPC targetNpc = null;
        if (npcs != null) {
            for (NPC npc : npcs) {
                if (npc.getName().equalsIgnoreCase(npcName)) {
                    targetNpc = npc;
                    break;
                }
            }
        }

        if (targetNpc == null) {
            return "There is no one here by that name.";
        }

        // --- Quest Completion Check ---
        Quest quest = targetNpc.getQuestToGive();
        if (quest != null && player.getActiveQuests().contains(quest)) {
            
            // THE FIX: Check for "Acquire" quests
            if (quest instanceof AquireingQuest) {
                AquireingQuest aQuest = (AquireingQuest) quest;
                // Check if player has the item
                Item requiredItem = player.getInventory().getItemByName(aQuest.getRequiredItem().getName());
                
                if (requiredItem != null) {
                    // Take the item and complete the quest
                    player.getInventory().removeItemByName(requiredItem.getName());
                    aQuest.complete();
                    System.out.println("You handed over the " + requiredItem.getName() + ".");
                }
            }

            if (quest.isCompleted()) {
                player.getInventory().addItem(quest.reward);
                String rewardName = quest.reward.getName();
                targetNpc.setQuestToGive(null);
                // Remove from active quests
                player.getActiveQuests().remove(quest);
                return targetNpc.getName() + " thanks you for your help and gives you your reward: " + rewardName;
            }
        }

        InteractHandler handler = new InteractHandler();
        handler.startInteraction(targetNpc, game);

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
