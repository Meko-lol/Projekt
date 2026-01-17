package Interact;

import Items.Item;

public class Node {
    private String monologue;
    private String[] answers;
    private Node[] nextNodes;
    private Item reward;
    private int answerIndex;


    public Node(String monologue, String[] answers, Node[] nextNodes, Item reward) {
        this.monologue = monologue;
        this.answers = answers;
        this.nextNodes = nextNodes;
        this.reward = reward;
    }

    public Node(String monologue, String[] answers, Node[] nextNodes) {
        this(monologue, answers, nextNodes, null);
    }

    public Object interact() {
        if (reward != null) {
            return reward;
        } else {
            if (nextNodes != null && answerIndex >= 0 && answerIndex < nextNodes.length) {
                return nextNodes[answerIndex];
            }
            return null;
        }
    }

    public String getMonologue() {
        return monologue;
    }

    public String[] getAnswers() {
        return answers;
    }

    public Node getNextNode(int answerIndex) {
         return nextNodes[answerIndex];
    }

    public Item getReward() {
        return reward;
    }
}
