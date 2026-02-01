package Commands.commandList;

import Commands.Command;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class QuoteCommand extends Command {
    private static List<String> quotes;
    private static Random random = new Random();

    @Override
    public String execute() {
        // Load quotes only once for efficiency
        if (quotes == null) {
            try {
                quotes = Files.readAllLines(Paths.get("Source/quotes.txt"));
            } catch (Exception e) {
                return "Could not find any quotes.";
            }
        }

        if (quotes.isEmpty()) {
            return "No quotes available.";
        }

        return quotes.get(random.nextInt(quotes.size()));
    }

    @Override
    public boolean exit() {
        return false;
    }
}
