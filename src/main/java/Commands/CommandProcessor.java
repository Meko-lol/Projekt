package Commands;

import Characters.Player;
import Game.MyGame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Commands.commandList.*;

public class CommandProcessor {
    private boolean shouldExit = false;
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private Player player;
    private MyGame game;

    public static final String COMMAND_HISTORY_FILE = "command_history.txt";

    public CommandProcessor(Player player, MyGame game) {
        this.player = player;
        this.game = game;
        initializeCommands();
        resetCommandHistory();
    }

    private void initializeCommands() {
        commands.put("quests", new QuestsCommand());
        commands.put("cordinates", new GetCordinatesCommand());
        commands.put("location", new GetLocationInfoCommand());
        commands.put("help", new HelpCommand());
        commands.put("history", new HistoryCommand());
        commands.put("quit", new QuitCommand());
        commands.put("end", new EndCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("interact", new InteractCommand());
        commands.put("pickup", new PickUpCommand());
        commands.put("move", new GoToCommand());
        commands.put("use", new UseCommand());
        commands.put("drop", new DropCommand());
        commands.put("inventory", new InventoryCommand());
        commands.put("equip", new EquipItemCommand());
        commands.put("fight", new FightCommand());
        commands.put("buy", new BuyCommand());
        commands.put("sell", new SellCommand());
        commands.put("save", new SaveGameCommand());
    }

    private void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void processInput() {
        System.out.print(">> ");
        String inputLine = scanner.nextLine().trim().toLowerCase();
        clearTerminal();
        if (inputLine.isEmpty()) {
            System.out.println(game.getDashboard());
            return;
        }
        saveCommandToHistory(inputLine);
        String[] parts = inputLine.split("\\s+");
        String commandName = parts[0];
        Command command = commands.get(commandName);
        if (command != null) {
            command.setContext(player, game, parts);
            String result = command.execute();
            if (result != null && !result.isEmpty()) {
                System.out.println(result);
                System.out.println();
            }
            this.shouldExit = command.exit();
        } else {
            System.out.println("Unknown command. Type 'help' for a list of commands.\n");
        }
        System.out.println(game.getDashboard());
    }

    public void run() {
        clearTerminal();
        System.out.println(game.getDashboard());
        do {
            processInput();
        } while (!shouldExit);
        scanner.close();
    }

    private void saveCommandToHistory(String command) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMAND_HISTORY_FILE, true))) {
            writer.write(command);
            writer.newLine();
        } catch (IOException e) {}
    }

    private void resetCommandHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMAND_HISTORY_FILE, false))) {
        } catch (IOException e) {
        }
    }
}
