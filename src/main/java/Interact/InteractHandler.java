package Interact;

import Game.MyGame;
import Items.Item;
import Quest.Quest;
import Characters.NPCs.NPC;
import java.util.Scanner;

public class InteractHandler {
    
    private Scanner scanner = new Scanner(System.in);

    public void startInteraction(NPC npc, MyGame game) {
        String startNodeName = npc.getDialogueNodeName();
        if (startNodeName == null) {
            System.out.println(npc.getName() + " has nothing to say.");
            wait(2000);
            return;
        }

        Node currentNode = game.getDialogueNodeByName(startNodeName);

        if (currentNode == null) {
            System.out.println("Error: Dialogue node '" + startNodeName + "' not found.");
            wait(2000);
            return;
        }

        System.out.println("\n--- Conversation with " + npc.getName() + " ---");

        while (currentNode != null) {
            System.out.println(npc.getName() + ": \"" + currentNode.getMonologue() + "\"");

            if (currentNode.getGiveItem() != null) {
                Item item = currentNode.getGiveItem();
                System.out.println("You received: " + item.getName());
                game.player.getInventory().addItem(item);
            }

            if (currentNode.isOfferQuest()) {
                offerQuest(npc, game);
                break;
            }

            if (currentNode.getAnswers() == null || currentNode.getAnswers().length == 0) {
                break;
            }

            for (int i = 0; i < currentNode.getAnswers().length; i++) {
                System.out.println((i + 1) + ". " + currentNode.getAnswers()[i]);
            }

            int choice = getPlayerChoice(currentNode.getAnswers().length);
            String nextNodeName = currentNode.getNextNodes()[choice];
            
            if (nextNodeName == null) {
                currentNode = null;
            } else {
                currentNode = game.getDialogueNodeByName(nextNodeName);
            }
        }
        System.out.println("--- End of Conversation ---");
        
        // THE FIX: Wait 2 seconds before returning to the main loop
        wait(2000);
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
    
    private void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
