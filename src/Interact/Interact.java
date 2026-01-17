package Interact;

import java.util.List;

public class Interact {
    protected String prompt;
    protected List<Answer> answers;

    public Interact(String prompt, List<Answer> answers) {
        this.prompt = prompt;
        this.answers = answers;
    }

    public String getPrompt() { return null; }
    public List<Answer> getAnswers() { return null; }
}
