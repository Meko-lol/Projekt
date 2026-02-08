package ending.prison;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Prison {

    private Map<String, String> questions;

    public Prison() {
        this.questions = new LinkedHashMap<>();
        questions.put("I have cities, but no houses. I have mountains, but no trees. I have water, but no fish. What am I?", "map");
        questions.put("What has to be broken before you can use it?", "egg");
        questions.put("What is full of holes but still holds water?", "sponge");
        questions.put("What question can you never answer yes to?", "are you asleep yet");
        questions.put("What is always in front of you but can’t be seen?", "future");
    }

    /**
     * Attempts to break out of prison by answering riddles.
     * @return true if the escape was successful, false otherwise.
     */
    public boolean attemptToBreakOut() {
        System.out.println("\n--- PRISON ESCAPE ---");
        System.out.println("You are trapped! To escape, you must answer at least 3 of 5 riddles.");
        Scanner scanner = new Scanner(System.in);
        int correctAnswers = 0;

        for (Map.Entry<String, String> entry : questions.entrySet()) {
            System.out.println("\nRiddle: " + entry.getKey());
            System.out.print("Your Answer >> ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals(entry.getValue())) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.println("Wrong...");
            }
        }

        if (correctAnswers >= 3) {
            System.out.println("You have answered enough riddles correctly. The prison door creaks open!");
            return true;
        } else {
            System.out.println("You failed to answer enough riddles. You remain trapped.");
            return false;
        }
    }
}
