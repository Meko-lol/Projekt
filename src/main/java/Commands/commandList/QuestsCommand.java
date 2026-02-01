package Commands.commandList;

import Commands.Command;
import Quest.Quest;
import java.util.List;

public class QuestsCommand extends Command {
    @Override
    public String execute() {
        // 1. Get the list of active quests from the player.
        List<Quest> quests = player.getActiveQuests();
        
        // 2. Check if the list is empty.
        if (quests == null || quests.isEmpty()) {
            return "You have no active quests.";
        }

        // 3. Build a formatted string with all the quest information.
        StringBuilder sb = new StringBuilder("--- Your Quests ---\n");
        for (Quest quest : quests) {
            sb.append("- ").append(quest.name).append(": ").append(quest.description);
            if (quest.isCompleted()) {
                sb.append(" (Completed)\n");
            } else {
                sb.append("\n");
            }
        }
        sb.append("-------------------");
        
        // 4. Return the final string to be printed by the CommandProcessor.
        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
