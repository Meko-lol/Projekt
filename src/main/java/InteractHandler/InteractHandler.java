package InteractHandler;

import Items.Item;
import java.util.Scanner;

public class InteractHandler { // Renamed from NodeHandler
    private Item receivedItem;
    private Scanner scanner = new Scanner(System.in);

    public void startInteraction(Node startingNode) {
        this.receivedItem = null;
        Node currentNode = startingNode;

        while (currentNode != null) {
            currentNode.interact();

            if (currentNode.getReward() != null) {
                this.receivedItem = currentNode.getReward();
                break;
            }

            String[] answers = currentNode.getAnswers();
            if (answers == null || answers.length == 0) {
                break;
            }

            for (int i = 0; i < answers.length; i++) {
            }

            int choice = getPlayerChoice(answers.length);

            currentNode = currentNode.getNextNode(choice);
        }

        if (this.receivedItem != null) {
        }
    }

    private int getPlayerChoice(int maxChoice) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice < maxChoice) {
                    return choice;
                } else {
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    public Item getReceivedItem() {
        return receivedItem;
    }
}
