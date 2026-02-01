package Commands.commandList;

import Commands.Command;
import Commands.CommandProcessor; // Import CommandProcessor to access the constant
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HistoryCommand extends Command {
    @Override
    public String execute() {
        try {
            // Access the constant directly from the class where it is defined.
            List<String> history = Files.readAllLines(Paths.get(CommandProcessor.COMMAND_HISTORY_FILE));
            if (history.isEmpty()) {
                return "No commands in history.";
            }
            return "Command History:\n" + String.join("\n", history);
        } catch (Exception e) {
            return "Could not read command history.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
