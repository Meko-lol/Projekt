package Commands.commandList;

import Commands.Command;
import Places.Location;
import Characters.NPCs.NPC;
import Quest.Quest;
import java.util.Scanner;

public class InteractCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "Who do you want to interact with?";
        }
        String npcName = args[1];
        Location currentLocation = game.getCurrentLocation();
        
        if (currentLocation == null || currentLocation.getNpcs() == null || currentLocation.getNpcs().isEmpty()) {
            return "There is no one here to interact with.";
        }

        NPC targetNpc = null;
        for (NPC npc : currentLocation.getNpcs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                targetNpc = npc;
                break;
            }
        }

        if (targetNpc == null) {
            return "There is no one here by that name.";
        }

        // --- Quest Logic ---
        Quest quest = targetNpc.getQuestToGive();
        if (quest != null) {
            // Check if the player already has this quest.
            if (player.getActiveQuests().contains(quest)) {
                if (quest.isCompleted()) {
                    player.getInventory().addItem(quest.reward);
                    targetNpc.setQuestToGive(null);
                    return targetNpc.getName() + " thanks you and gives you your reward: " + quest.reward.getName();
                } else {
                    return targetNpc.getName() + " reminds you: '" + quest.description + "'";
                }
            } else {
                // Offer the quest.
                System.out.println("\n" + targetNpc.getName() + " has a quest for you: '" + quest.name + "'");
                System.out.println("Description: " + quest.description);
                System.out.println("Reward: " + quest.reward.getName());
                System.out.print("Do you accept? (yes/no) >> ");
                
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine().trim().toLowerCase();
                
                if (choice.equals("yes")) {
                    // THE FIX: Correctly add the quest to the player's list.
                    player.addQuest(quest);
                    return "Quest accepted: " + quest.name;
                } else {
                    return "You declined the quest.";
                }
            }
        }

        return "You have a pleasant chat with " + targetNpc.getName() + ".";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
