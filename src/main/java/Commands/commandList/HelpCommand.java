package Commands.commandList;

import Commands.Command;

public class HelpCommand extends Command {
    @Override
    public String execute() {
        return "--- Available Commands ---\n" +
               "move [direction]   - Move north, south, east, or west.\n" +
               "look               - Look around your current location.\n" +
               "interact [npc]     - Talk to an NPC.\n" +
               "fight [enemy]      - Fight a hostile enemy.\n" +
               "quests             - View your active quests.\n" +
               "inventory          - Check your inventory.\n" +
               "pickup [item]      - Pick up an item from the ground.\n" +
               "drop [item]        - Drop an item from your inventory.\n" +
               "equip [item]       - Equip a weapon or armor.\n" +
               "use [item] on [target] - Use an item on an obstacle.\n" +
               "buy [item]         - Buy an item from a trader.\n" +
               "save               - Save your progress.\n" +
               "quit               - Exit the game.\n" +
               "--------------------------";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
