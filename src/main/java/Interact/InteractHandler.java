package Interact;

import Game.MyGame;
import Items.Item;
import Quest.Quest;
import Characters.NPCs.NPC;
import java.util.Scanner;

public class InteractHandler {
    
    private transient Scanner scanner = new Scanner(System.in);

    public void startInteraction(NPC npc, MyGame game) {
        // Find the starting node from the NPC's dialogue name
        Node currentNode = game.getDialogueNodeByName(npc.getDialogueNodeName());

        while (currentNode != null) {
            // 1. Print the NPC's monologue
            System.out.println("\n" + npc.getName() + ": \"" + currentNode.getMonologue() + "\"");

            // 2. Handle giving an item to the player
            if (currentNode.getGiveItem() != null) {
                Item item = currentNode.getGiveItem();
                System.out.println("You received: " + item.getName());
                game.player.getInventory().addItem(item);
            }

            // 3. Handle offering a quest
            if (currentNode.isOfferQuest()) {
                offerQuest(npc, game);
                break; // End conversation after quest offer
            }

            // 4. If there are no answers, the conversation ends here
            if (currentNode.getAnswers() == null || currentNode.getAnswers().length == 0) {
                break;
            }

            // 5. Display player's choices
            for (int i = 0; i < currentNode.getAnswers().length; i++) {
                System.out.println((i + 1) + ". " + currentNode.getAnswers()[i]);
            }

            // 6. Get player's choice and find the next node
            int choice = getPlayerChoice(currentNode.getAnswers().length);
            String nextNodeName = currentNode.getNextNodes()[choice];
            
            if (nextNodeName == null) {
                currentNode = null; // End of conversation branch
            } else {
                currentNode = game.getDialogueNodeByName(nextNodeName);
            }
        }
    }

    private void offerQuest(NPC npc, MyGame game) {
        Quest quest = npc.getQuestToGive();
        if (quest != null && !game.player.getActiveQuests().contains(quest)) {
            System.out.println("\n--- Quest Offer ---");
            System.out.println(npc.getName() + " wants you to complete the quest: '" + quest.name + "'");
            System.out.println("Description: " + quest.description);
            System.out.println("Reward: " + quest.reward.getName());
            System.out.print("Do you accept? (yes/no) >> ");
            
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) {
                quest.questGiverName = npc.getName();
                game.player.addQuest(quest);
                System.out.println("Quest accepted: " + quest.name);
            } else {
                System.out.println("You declined the quest.");
            }
        }
    }

    private int getPlayerChoice(int maxChoice) {
        while (true) {
            System.out.print("Your choice >> ");
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice < maxChoice) {
                    return choice;
                } else {
                    System.out.println("That is not a valid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
