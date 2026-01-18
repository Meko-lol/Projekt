package Commands;

import Characters.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Commands.commandList.HelpCommand;
import Commands.commandList.HistoryCommand;
import Commands.commandList.QuitCommand;
import Commands.commandList.QuoteCommand;
import Commands.commandList.InteractCommand;
import Commands.commandList.PickUpCommand;
import Commands.commandList.GoToCommand;
import Commands.commandList.UseCommand;
import Commands.commandList.OpenBackpackCommand;
import Commands.commandList.DropCommand;
import Commands.commandList.OpenInventoryCommand;
import Commands.commandList.EquipItemCommand;
import Commands.commandList.RemoveBackpackCommand;
import Commands.commandList.FightCommand;
import Commands.commandList.BuyCommand;
import Commands.commandList.SellCommand;

public class CommandProcessor {
    private boolean shouldExit = false;
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private Player player;

    public static final String COMMAND_HISTORY_FILE = "command_history.txt";

    public CommandProcessor(Player player) {
        this.player = player;
        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("help", new HelpCommand());
        commands.put("history", new HistoryCommand());
        commands.put("quit", new QuitCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("interact", new InteractCommand());
        commands.put("pickup", new PickUpCommand());
        commands.put("goto", new GoToCommand());
        commands.put("use", new UseCommand());
        commands.put("open-backpack", new OpenBackpackCommand());
        commands.put("drop", new DropCommand());
        commands.put("open-inventory", new OpenInventoryCommand());
        commands.put("put-on-backpack", new EquipItemCommand());
        commands.put("remove-backpack", new RemoveBackpackCommand());
        commands.put("fight", new FightCommand());
        commands.put("buy", new BuyCommand());
        commands.put("sell", new SellCommand());
    }

    private void processInput() {
        String inputLine = scanner.nextLine().trim().toLowerCase();

        if (inputLine.isEmpty()) {
            return;
        }

        String[] parts = inputLine.split("\\s+");
        String commandName = parts[0];

        Command command = commands.get(commandName);

        if (command != null) {
            command.setContext(player, this, parts);
            command.execute();
            this.shouldExit = command.exit();
        } else {
        }
    }

    public void run() {
        do {
            processInput();
        } while (!shouldExit);
        scanner.close();
    }

}
