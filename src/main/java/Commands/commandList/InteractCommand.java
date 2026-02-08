package Commands.commandList;

import Commands.Command;
import Game.NPCFinder; // Import NPCFinder
import Places.Location;
import Characters.NPCs.NPC;
import Interact.InteractHandler;
import Quest.Quest;
import Quest.AquireingQuest;
import Items.Item;
import java.util.List;

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
        // THE FIX: Use NPCFinder
        NPC targetNpc = NPCFinder.findNPC(npcs, npcName);

        if (targetNpc == null) {
            return "There is no one here by that name.";
        }

        Quest quest = targetNpc.getQuestToGive();
        if (quest != null && player.getActiveQuests().contains(quest)) {
            if (quest instanceof AquireingQuest) {
                AquireingQuest aQuest = (AquireingQuest) quest;
                Item requiredItem = player.getInventory().getItemByName(aQuest.getRequiredItem().getName());
                if (requiredItem != null) {
                    player.getInventory().removeItemByName(requiredItem.getName());
                    aQuest.complete();
                    System.out.println("You handed over the " + requiredItem.getName() + ".");
                }
            }

            if (quest.isCompleted()) {
                player.getInventory().addItem(quest.reward);
                String rewardName = quest.reward.getName();
                targetNpc.setQuestToGive(null);
                player.getActiveQuests().remove(quest);
                return targetNpc.getName() + " thanks you and gives you: " + rewardName;
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
