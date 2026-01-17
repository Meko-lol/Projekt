package Interact;

public class Answer {
    protected String text;
    protected Interact nextInteraction;

    public Answer(String text, Interact nextInteraction) {
        this.text = text;
        this.nextInteraction = nextInteraction;
    }

    public String getText() { return null; }
    public Interact getNextInteraction() { return null; }
}
