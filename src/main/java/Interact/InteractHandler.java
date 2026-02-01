package Interact;

import Items.Item;
import MyFileManager.MyFileManager; // Import MyFileManager
import java.util.Scanner;

public class InteractHandler {
    
    private transient Scanner scanner = new Scanner(System.in);

    public InteractHandler() {}

    /**
     * Starts and manages a dialogue tree.
     * @param startingNode The first Node of the conversation.
     * @param fileManager  The file manager, used to find subsequent nodes by name.
     */
    public void startInteraction(Node startingNode, MyFileManager fileManager) {
        Node currentNode = startingNode;

        while (currentNode != null) {
            // 1. Print the NPC's monologue
            System.out.println(currentNode.getMonologue());

            // 2. Check for a reward and end the conversation if found
            if (currentNode.getReward() != null) {
                Item reward = currentNode.getReward();
                System.out.println("You received: " + reward.getName());
                // TODO: Add the item to the player's inventory
                break;
            }

            // 3. If there are no answers, the conversation is over
            String[] answers = currentNode.getAnswers();
            if (answers == null || answers.length == 0) {
                break;
            }

            // 4. Display the player's choices
            for (int i = 0; i < answers.length; i++) {
                System.out.println((i + 1) + ". " + answers[i]);
            }

            // 5. Get the player's choice
            int choice = getPlayerChoice(answers.length);
            
            // 6. Find the name of the next node based on the player's choice
            String nextNodeName = currentNode.getNextNodes()[choice];
            
            // 7. Find the next node from the file manager's list
            currentNode = fileManager.getInteractByName(nextNodeName);
        }
    }

    private int getPlayerChoice(int maxChoice) {
        while (true) {
            System.out.print(">> ");
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input) - 1; // Convert string to int and adjust for 0-based index
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
