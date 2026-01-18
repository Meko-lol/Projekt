package Commands.commandList;

import Commands.Command;
import Commands.CommandProcessor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HistoryCommand extends Command {
    @Override
    public String execute() {
        try {
            return Files.lines(Paths.get(CommandProcessor.COMMAND_HISTORY_FILE))
                        .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Could not read command history.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
